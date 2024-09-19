- Parallel: ( parallel = true)
    - Tests
    - Classes
    - Methods
    - Instances
    - Default thread-count =5 (  parallel attribute)
- There are basically four designs for running multiple Appium sessions at a time:
    - Option 1: Running multiple Appium servers, and sending one session to each server
    - Option 2: Running one Appium server, and sending multiple sessions to it
    - Option 3: Running one or more Appium servers behind the Selenium Grid Hub, and sending all sessions to the Grid
      Hub
    - Option 4: Leveraging a cloud provider (which itself is running many Appium servers, most likely behind some single
      gateway)
- Approach 1: Use Separate Instances of Appium for Separate Devices at the same time. (running multiple servers)
    - Client Side
        - Java + Maven + Surefire plugin to configure ( setup pom.xml)
            - ```<plugin>
          <groupId>org.apache.maven.plugins</groupId>
          <artifactId>maven-surefire-plugin</artifactId>
          <configuration>
          <parallel>methods</parallel>
          <threadCount>4</threadCount>
          </configuration>
          </plugin>```
    - Server Side:
        - Start different Appium servers listening on different ports
        - Then each test thread would need to adjust the URL of the Appium server to include the appropriate port,
        - ```appium -p 10000  # server 1
          appium -p 10001  # server 2```

    - Pro:
        - Execution time is reduced.
    - Con:
        - Multiple Maven commands / Multiple Tags are required.

- Approach 2: use the existing Appium instance with a Different system ports.
    - Client Side
    - Server Side:
        - In some cases, an Appium driver might need to use a port or a socket, and it would not be good for another
          session using the same driver to compete for communication on the same port or socket. There are thus a
          handful of capabilities that need to be included to make sure no system resource conflicts exist between your
          different test sessions
    - Android Parallel Testing Capabilities:
        - **udid**: if you don't include this capability, the driver will attempt to use the first device in the list
          returned by ADB. This could result in multiple sessions targeting the same device, which is not a desirable
          situation. Thus, it's essential to use the udid capability, even if you're using emulators for testing (in
          which case the emulator looks like emulator-55xx).
        - **systemPort**: to communicate to the UiAutomator2 process, Appium utilizes an HTTP connection which opens up
          a port on the host system as well as on the device. The port on the host system must be reserved for a single
          session, which means that if you're running multiple sessions on the same host, you'll need to specify
          different ports here (for example 8200 for one test thread and 8201 for another).
        - **chromeDriverPort**: in the same way, if you're doing webview or Chrome testing, Appium needs to run a unique
          instance of Chromedriver on a unique port. Use this capability to ensure no port clashes for these kinds of
          tests.
    - iOS Parallel Testing Capabilities:
        - **udid**: we need to make sure we specify a particular device id to ensure we don't try to run a session on
          the
          same device.
        - **deviceName and platformVersion**: if you specify a unique combination of device name and platform version,
          Appium will be able to find a unique simulator that matches your requirement, and in this case, you don't need
          to specify the udid.
        - **wdaLocalPort**: the iOS XCUITest driver uses a specific port to communicate with WebDriverAgent running on
          the iOS device. It's good to make sure these are unique for each test thread.
    - Pro: (Better than Approach 1)
        - Execution time is reduced.
        - Only one Appium is used in this approach, as long as different SystemPort is passed during execution,
          automation works smoothly.
    - Con:
        - Multiple Maven commands / Multiple Tags are required.
- Approach 3: Use Maven SureFire Plugin and Selenium Grid
