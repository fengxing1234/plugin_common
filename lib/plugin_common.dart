import 'dart:async';

import 'package:flutter/services.dart';

class PluginCommon {
  static const MethodChannel _channel =
      const MethodChannel('plugin_common');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }
}
