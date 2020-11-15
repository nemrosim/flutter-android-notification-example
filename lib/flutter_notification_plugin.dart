import 'dart:async';

import 'package:flutter/cupertino.dart';
import 'package:flutter/services.dart';

class FlutterNotificationPlugin {
  static const MethodChannel _channel =
      const MethodChannel('flutter_notification_plugin');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<String> showBasicNotification({
    @required String smallIconName,
    @required String smallIconDefType,
  }) async {
    await _channel.invokeMethod('showBasicNotification', {
      "smallIconName": smallIconName,
      "smallIconDefType": smallIconDefType,
    });
    return "Done";
  }
}
