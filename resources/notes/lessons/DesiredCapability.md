Desired Capability:

- Keys and values encoded in a JSON object, sent by Appium Client to the server when a new driver session is required
- Types:
    - General Capability ( Command for all drivers)
    - Android only [UiAutomator2](https://github.com/appium/appium-uiautomator2-driver?tab=readme-ov-file#capabilities)
    - iOS only [XCUITest] (https://appium.github.io/appium-xcuitest-driver/4.16/capabilities/)

- Android Desired Capabilities
    - Without APK (Minimum)
        - Using device name
          ```json
              "deviceName": "Nexus_9_API_28"
              "platformName": "Android"
              "appPackage":"{appPackage}"
              "appActivity":"{appActivity}"
          ```
        - Using adv
          ```json{
            "appium:appPackage": "com.wdiodemoapp",
            "appium:appActivity": "com.wdiodemoapp.MainActivity",
            "appium:automationName": "uiautomator2",
            "platformName": "android",
            "appium:deviceName": "Pixel 5 API 34",
            "avd": "Pixel_5_API_34",
            "appium:platformVersion": "14",
            "appium:setting[ignoreUNimportantViews]": true
            }
          ```

- iOs Desired Capabilities:
    - With app on simulator
        - Using app path:
    ```json
      {
        "platformName": "iOS",
        "appium:options": {
        "automationName": "XCUITest",
        "platformVersion": "17.4",
        "deviceName": "iPhone 15",
        "app": "AppPath",
        "setting[ignoreUNimportantViews]": true
        }
      }
  ```
      - Using udid

    ```json
    {
       "platformName": "iOS",
        "automationName": "XCUITest",
        "udid": "UUID",
        "bundleId": "org.reactjs.native.example.wdiodemoapp",
       "setting[ignoreUNimportantViews]": true
     }
    ```

    - Without app on real device

      ```json
       "deviceName": "iPhone-8"
          "bundleId": "{bundleId}"
          "udid": "{udid}"
          "platformVersion": "12.1.4"
          "xcodeOrgId": "{xcodeOrgId}"
          "xcodeSigningId": "iPhone Developer"
          "platformName": "IOS"
          "automationName": "XCuiTest"`
      ```
