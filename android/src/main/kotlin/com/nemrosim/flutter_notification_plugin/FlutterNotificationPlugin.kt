package com.nemrosim.flutter_notification_plugin

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.NonNull
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

/** FlutterNotificationPlugin */
class FlutterNotificationPlugin : FlutterPlugin, MethodCallHandler {


    private lateinit var channel: MethodChannel
    private lateinit var context: Context

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "flutter_notification_plugin")
        channel.setMethodCallHandler(this)
        context = flutterPluginBinding.applicationContext
    }


    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        if (call.method == "getPlatformVersion") {
            result.success("Android ${android.os.Build.VERSION.RELEASE}")
        } else if (call.method == "showBasicNotification") {


            val iconName = call.argument<String>("smallIconName")
            val typeDef = call.argument<String>("smallIconDefType")

            val smallIcon = context.resources.getIdentifier(
                    iconName,
                    typeDef,
                    context.packageName
            )

            with(NotificationManagerCompat.from(context)) {
                // notificationId is a unique int for each notification that you must define


                // Create the NotificationChannel, but only on API 26+ because
                // the NotificationChannel class is new and not in the support library
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val service: String = Context.NOTIFICATION_SERVICE

                    val name = "Some name"
                    val descriptionText = "Desc"
                    val importance = NotificationManager.IMPORTANCE_DEFAULT
                    val channel = NotificationChannel("My_Id", name, importance).apply {
                        description = descriptionText
                    }

                    val notificationManager: NotificationManager =
                            context.getSystemService(service) as NotificationManager
                    notificationManager.createNotificationChannel(channel)
                }

                val builder = NotificationCompat.Builder(context, "My_Id")
                        .setSmallIcon(smallIcon)
                        .setContentTitle("Title")
                        .setContentText("Text")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

                val someNotificationId = 1;

                notify(someNotificationId, builder.build())
            }

        } else {
            result.notImplemented()
        }
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }
}
