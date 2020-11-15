import 'package:flutter/material.dart';

import 'package:flutter_notification_plugin/flutter_notification_plugin.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: FlatButton(
            onPressed: () async {
              FlutterNotificationPlugin.showBasicNotification(
                smallIconName: "ic_launcher",
                smallIconDefType: "mipmap",
              );
            },
            child: Text(
              'Show notification',
              style: TextStyle(fontWeight: FontWeight.bold, fontSize: 22),
            ),
          ),
        ),
      ),
    );
  }
}
