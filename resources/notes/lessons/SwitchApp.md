- SWITCH to another app | Handle multi app on same device

- Put the current app under background till we call it back
  androidDriver.runAppInBackground(Duration.ofSeconds(-1));
-
- Run command adb shell "dumpsys activity activities | grep -E mResumedActivity" to get app package parameter

- Switch to the another app to do something
  ` androidDriver.activateApp("com.android.settings");`

- Switch back to the app under test to continue the follow
  androidDriver.activateApp("com.wdiodemoapp");