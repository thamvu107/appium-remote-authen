Command with device:

- [Refer document:]( https://developer.android.com/studio/run/emulator-commandline)

- Show devices list:
    - `emulator -list-avds`
- Start a device
    - `emulator @Pixel_5_API_34`
    - `emulator -avd <avd_name>`
- Wipe data:
    - `emulator @Pixel8_API_34 -wipe-data`
- Stop/Kill a avd
    - `adb -e emu kill`

adb commands:

- adb android debug bridge
- List connected devices
    - `adb devices`
- Connect/disconnect device:
    - `adb connect ***wifi.ip.address***:5555`
    - `adb disconnect ***wifi.ip.address***:5555`
- If a device is connected start the adb sever to be able to interact with the device
    - `adb start-server`
    - `adb kill-server`
- Install an app
    - `adb install [apk_path]`
    - `adb -s device-udid install [apk_path]`
- Run app:
    - `adb shell am start -n com.app.name/com.app.name.LaunchActivity`
    - `adb shell am start -n com.wdiodemoapp/com.wdiodemoapp.MainActivity`
- Uninstall an app
    - `adb uninstall [package_name]`
- Install appium: (Optional)
    - `adb -s emulator-5554 install absolute_path/settings_apk-debug.apk`
    - `adb -s emulator-5554 uninstall io.appium.settings`

How to get appPackage & appActivity:

- Get all active device:
    - `adb devices`
- Install the app with below command:
- ```adb install absolute/path/to/ap.extension```
- If you have more than one devices conttec, you need to specify the device you wanna install:
    - ```adb -s device-udid install absolute/path/to/ap.extension```
- Launch app on the device and bring activity in focus
- Launch terminal then run one of command:
    - `adb shell dumpsys window | grep -E mCurrentFocus``` (For older Android version)
    - ```adb shell "dumpsys activity activities |grep mResumedActivity"` (For >= Android 10 version)
    - `adb shell dumpsys window | find "mCurrentFocus"`
    - `adb shell dumpsys window | grep "mCurrentFocus"`
    - `adb shell dumpsys window | findstr "mCurrentFocus"`

How to Launch Emulator Automatically:

- Capability to use:
    - `adv`
    - `advLaunchTimeout`
- cold boot emulator:
    - `emulator -avd Pixel_4_API_30 -no-snapshot-load`
- `adb -s emulator-5554 shell getprop`
- `adb -s 192.168.1.15:5555 shell getprop ro.build.version.release`
