# iOS Simulator with the Command Line

Xcode > Preferences > Location
Select Xcode path: `/Applications/Xcode.app/Contents/Developer`

- Check Xcode Installation:
    - `xcode-select --print-path`

- Check xcode version
    - `xcodebuild -version`
- Using simctl:
    - `xcrun simctl help`
- List simulators devices:
    - `xcrun simctl list devices`
    - `xcrun simctl list`
- Get simulator platform version
    - `xcrun simctl list runtimes`
- Create simulators:
    - from previous command then we get info
        - == Device Types ==
            - `iPhone 11 Pro Max (com.apple.CoreSimulator.SimDeviceType.iPhone-11-Pro-Max)`
        - == Runtimes ==
            - `iOS 13.4 (13.4.1 - 17E8260) - com.apple.CoreSimulator.SimRuntime.iOS-13-4`
    - `xcrun simctl create iPhone-11-Pro
      com.apple.CoreSimulator.SimDeviceType.iPhone-11-Pro
      com.apple.CoreSimulator.SimRuntime.iOS-13-4`-
- Open simulator:
    - `xcrun simctl boot "Device Name"`
- Open simulator:
- `open -a Simulator --args -CurentDeviceUDID {UUID}`
- Open app:
    - `open /Applications/Xcode.app/Contents/Developer/Applications/Simulator.app/`
- Install app
    - `xcrun simctl install <YOUR-DEVICE-ID> <PATH-TO-APPLICATION-BUNDLE>`
- Launch app on siumlator;
    - `xcrun simctl launch <YOUR-DEVICE-ID> <BUNDLE-ID-OF-APP-BUNDLE>`
- Shutdown simulator:
    - `xcrun simctl shutdown UUID`
- Erase Simulator:
    - `xcrun simctl erase UUID`
    - `xcrun simctl delete unavailable`
- Booting simulator:
    - `xcrun simctl boot UUID`
- Install app inside simulator
    - We use this command to install an app on a device.
        - `xcrun simctl install booted ./demo.app`
    - `xcrun simctl install <device id> <path to application .ipa or .app>`

- Uninstall app inside simulator
    - `$ xcrun simctl uninstall booted test.CLI`
- Launch app inside simulator
    - `xcrun simctl launch booted test.CLI`
- Terminator app inside simulator
    - `xcrun simctl terminate booted test.CLI`
- Add photo/Video to simulator
    - `xcrun simctl addmedia booted ~/Desktop/name.png`
- Screenshot of simulator
    - `xcrun simctl io booted screenshot screen.png`
- Video recording of simulator
    - `xcrun simctl io booted recordVideo name.mov`
- Collecting simulator logs
    - `xcrun sumctl spawn booted log stream - level=debug`
- Note version:
    - https://appium.github.io/appium-xcuitest-driver/latest/installation/requirements/
- List all installed apps on simulator:
    - `xcrun simctl listapps <device_udid>`
- **Changing of system settings**
- Turn on|off wifi:
    - `adb shell am broadcast -a io.appium.settings.wifi --es setstatus enable | disable`
- Turn on|off data:
    - `adb shell am broadcast -a io.appium.settings.data_connection --es setstatus enable | disable`
- Turn on|off bluetooth:
    - `$ adb shell am broadcast -a io.appium.settings.bluetooth --es setstatus enable | disable`
- get the device's UDID
    - `xcrun xctrace list devices`
