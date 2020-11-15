#import "FlutterNotificationPlugin.h"
#if __has_include(<flutter_notification_plugin/flutter_notification_plugin-Swift.h>)
#import <flutter_notification_plugin/flutter_notification_plugin-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "flutter_notification_plugin-Swift.h"
#endif

@implementation FlutterNotificationPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFlutterNotificationPlugin registerWithRegistrar:registrar];
}
@end
