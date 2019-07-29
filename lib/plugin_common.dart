import 'dart:async';

import 'package:flutter/services.dart';

class PluginCommon {
  static const MethodChannel _channel = const MethodChannel('plugin_common');

  toast(String msg) async {
    final String result = await _channel.invokeMethod('showToast', msg);
    print('result = $result');
  }
}
