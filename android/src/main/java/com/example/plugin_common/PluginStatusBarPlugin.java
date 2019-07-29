package com.example.plugin_common;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;

public class PluginStatusBarPlugin implements MethodChannel.MethodCallHandler {

    private static final String TAG = PluginCommonPlugin.class.getSimpleName();

    public static final String CHANNEL_NAME = "com.example.plugin_common.PluginStatusBarPlugin";

    public static final String METHOD_SET_STATUS_BAR_COLOR = "setStatusBarColor";


    private Activity mActivity;

    private PluginStatusBarPlugin(Activity activity) {
        this.mActivity = activity;
    }

    public static void registerWith(PluginRegistry.Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), CHANNEL_NAME);
        channel.setMethodCallHandler(new PluginStatusBarPlugin(registrar.activity()));
    }

    @Override
    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
        Log.d(TAG, "Method = " + call.method + ",  arguments =  " + call.arguments());
        switch (call.method) {
            case METHOD_SET_STATUS_BAR_COLOR:
                setStatusBarColor(call, result);
                break;
            default:
                Log.d(TAG, "onMethodCall: notImplemented");
                result.notImplemented();
                break;
        }
    }

    private void setStatusBarColor(MethodCall call, MethodChannel.Result result) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // api大于21设置状态栏透明
            int color = Color.parseColor((String) call.arguments);
            mActivity.getWindow().setStatusBarColor(color);
            result.success("设置成功");
        } else {
            result.error("版本过低，无法设置状态栏颜色", call.method, call.arguments);
        }
    }
}
