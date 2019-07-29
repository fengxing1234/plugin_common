import 'package:flutter/services.dart';

class PluginStatusBar {
  static const MethodChannel _channel =
      const MethodChannel('com.example.plugin_common.PluginStatusBarPlugin');

  setStateBarColor(String color) async {
    final String result =
        await _channel.invokeMethod('setStatusBarColor', color);
    print('result = $result');
  }
}
