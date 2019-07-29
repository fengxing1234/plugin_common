package com.example.plugin_common;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * PluginCommonPlugin
 */
public class PluginCommonPlugin implements MethodCallHandler {

    private static final String TAG = PluginCommonPlugin.class.getSimpleName();

    public static final String METHOD_TOAST = "showToast";

    private Activity mActivity;

    private PluginCommonPlugin(Activity activity) {
        this.mActivity = activity;
    }

    /**
     * Plugin registration.
     */
    public static void registerWith(Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "plugin_common");
        channel.setMethodCallHandler(new PluginCommonPlugin(registrar.activity()));
//        final MethodChannel statusBarChannel = new MethodChannel(registrar.messenger(), "plugin_status_bar");
//        statusBarChannel.setMethodCallHandler(new PluginStatusBarPlugin(registrar.activity()));
        PluginStatusBarPlugin.registerWith(registrar);
    }

    @Override
    public void onMethodCall(MethodCall call, Result result) {
        Log.d(TAG, "Method = " + call.method + ",  arguments =  " + call.arguments());
        switch (call.method) {
            case METHOD_TOAST:
                showToast(call, result);
                break;
            default:
                Log.d(TAG, "onMethodCall: notImplemented");
                result.notImplemented();
                break;
        }
    }

    private void showToast(MethodCall call, Result result) {
        String msg = call.arguments();
        if (mActivity == null) {
            result.error("mActivity == Null", call.method, call.arguments);
            return;
        }
        Toast.makeText(mActivity.getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
}
