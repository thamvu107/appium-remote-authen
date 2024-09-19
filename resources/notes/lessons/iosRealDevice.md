- Check reuirements:
- Common for both iOS simulator & Real Devices
    - Mac OS system
    - Xcode
    - XCode command Line tools
    - Appium
    - Appium doctor(option)
    - Appium iOS Drivers(XCUITest)
    - Appium inspector( optional)
    - iOS application file(.app(simulator)/.ipa( real device))
- Additional for Real Device:
    - libimobiledevice
    - WebdriverAgent
    - Apple Developer Account
    - iOS real device with cable
- WebDriverAgent:
    - WDA is essential for interacting with real iOS devices during automation. It acts as a bridge between Appium and
      the
      device, allows to perform actions tapping, swiping, inspecting elements ect.
    - With Appium 2, when you install the XCUITest driver using command appium driver install xcuitest , it includes the
      necessary components of WebDriverAgent.
    - Therefore, you don't need to get WDA separately.
    - However, there's one more step you need to take: setting up the WDA
    - Check real device
      configuration: [real device config](https://appium.github.io/appium-xcuitest-driver/latest/preparation/real-device-config/)

**Way 1:**

- Find webdriver agent:
    - `find . -name "appium-webdriveragent"`
- Open webdriver agent:
    - `open ./node_modules/appium-xcuitest-driver/node_modules/appium-webdriveragent`
- Select webdriver agent and open: `WebDriverAgent.xcproject`

**Way 2:** (XCUITest driver v4.13.0 or newer)

- Run command : `appium driver run xcuitest open-wda`


- appium driver run xcuitest <script-name>
    - <script-name>
      - `open-wda` : Opens the WebDriverAgent project in Xcode
      - `build-wda` : Builds the WebDriverAgent project using the first available iPhone simulator and the latest iOS supported by the current Xcode version

- build:
    - WDWRunner
    - WDWLib
    - InterationApp
- WDA product - Test

- Trust together:
    - Enable Developer Mode on device (
      phone) [Link](https://developer.apple.com/documentation/xcode/enabling-developer-mode-on-a-device)
    - Settings => General => Device Management on the device to trust the developer and allow the WebDriverAgentRunner
      app to be run
    - Finder Mac -> Trust phone
- [Appium setting | iOS Setup](https://www.youtube.com/watch?v=tvjfBbIuXRA)

- Setup Real iOS Device:
- Step 1: Connect your iOS device to your Mac using cable. make sure
  the device is unclocked and connected to the same Wifi network as MAc
- Step 2: on iOS device, turn on Developer Mode
- Step 3: Trust device.


- get id app:
    - `osascript -e 'id of app "/Users/tham/Code/appium-driver-improve/src/test/resources/apps/wdiodemoapp.app"'`
- ideviceinstaller
- get UDID:
    - `idevice_id -l`
- Get Device name:
    - `ideviceinfo -k DeviceName`
- Get Platform version:
    - `ideviceinfo -k ProductVersion`
- list all the installed apps
    - `ideviceinstaller -l`


- DevToolsSecurity -enable
  turn of
  xcodebuild failed with code 70
