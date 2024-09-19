# Issue:

- **Issue 1:
  ** `An unknown server-side error occurred while processing the command. Could not proxy command to remote server. Original
  error: Error: socket hang up`

    - **Solution**:
        - Stop the appium server and execute this:
            - `adb uninstall io.appium.uiautomator2.server`
            - `adb uninstall io.appium.uiautomator2.server.test`
            - `adb uninstall io.appium.unlock`
            - `adb uninstall io.appium.settings`
        - install app: `adb -s udid install /path/to/your/app.apk`

        - `appium driver run uiautomator2 reset`
        - Solution: kill all system port
            - `kill -9 $(lsof -t -i :systemPort)`

-
- Issue: `bug: 'appium-uiautomator2-server-v5.12.1.apk' does not exist or is not accessible`

- **Issue 2:**  `System UI Not Responding`
    - **Solutions**:
        - Update emulator to increase RAM (3-4GB) + ( 1-2 GB RAM will be unstable and slow)
        - Clear Storages
        - Cold boot

- **Issue 3**: `Compatibility matrix of Appium Java Client and Selenium Client`
    - New versions of Selenium 4 libraries are published they are pulled transitively as Appium Java Client dependencies
      at the first project (re)build automatically.
    - In order to pin Selenium dependencies they should be declared in pom.xml in the following way:
        - ```<dependencies>
            <dependency>
                <groupId>io.appium</groupId>
                <artifactId>java-client</artifactId>
                <version>X.Y.Z</version>
                <exclusions>
                    <exclusion>
                        <groupId>org.seleniumhq.selenium</groupId>
                        <artifactId>selenium-api</artifactId>
                    </exclusion>
                    <exclusion>
                        <groupId>org.seleniumhq.selenium</groupId>
                        <artifactId>selenium-remote-driver</artifactId>
                    </exclusion>
                    <exclusion>
                        <groupId>org.seleniumhq.selenium</groupId>
                        <artifactId>selenium-support</artifactId>
                    </exclusion>
                </exclusions>
            </dependency>
            <dependency>
                <groupId>org.seleniumhq.selenium</groupId>
                <artifactId>selenium-api</artifactId>
                <version>A.B.C</version>
            </dependency>
            <dependency>
                <groupId>org.seleniumhq.selenium</groupId>
                <artifactId>selenium-remote-driver</artifactId>
                <version>A.B.C</version>
            </dependency>
            <dependency>
                <groupId>org.seleniumhq.selenium</groupId>
                <artifactId>selenium-support</artifactId>
                <version>A.B.C</version>
            </dependency>
      </dependencies>```
    - If the updating Selenium version is happened conflict => Then follow steps below to resolve:
        - Remove all packages under .m2>repository
        - `mvn clean package -DskipTests`
        - Run command line: `mvn clean install -U -DskipTests=true`
        - Reload maven project & Resolved Maven packages (Download packages)
        - Rebuild project

- **Issue 4**: `"Original error: Cannot read properties of undefined (reading '0')`"
    - **Solutions**:
        - Check caps are correct
        - Check time to connect app under test -> If time isn't enough connect then need to increase wait time ( app
          launch, server timeout ,...)
        - ...
- **Issue 5:** emulator crash: `emulator -list-advs` => `Storing crashdata in`
    - **Solution**:
        - https://developer.android.com/studio/emulator_archive
        - https://developer.android.com/studio/releases/emulator
- Issue 6: Gson already is a dependency in java-client ( java-client.pom) but it is only runtime scope. that is why the
  code using Gson will not compile
    - Solution: add dependency into myfile.pom with same version ( to void conflict version) and add scope to compile
- **Issue**: **SessionNotCreatedException** : Appium Settings app is not running after 30000ms
- My Solution:
    - ```try {
  driver = new AndroidDriver(serverURL, caps);
  break;
  } catch (SessionNotCreatedException ex) {
  // ERROR: first time start emulator then throw exception since timeout Appium setting is set only 30_000ms( caps)
  // Error: Appium Settings app is not running after 30000ms
  // throw new SessionNotCreatedException(ex.getMessage());
  // //Retrying to create driver
  if (ex.getMessage().contains("Appium Settings app is not running after 30000ms")) {
  // System.out.println("Appium Settings app is not running: " + ex.getMessage());
  System.out.println("Retry to create driver");
  driver = new AndroidDriver(serverURL, caps);
  break;
  }

            } catch (Exception e) {
              throw new RuntimeException(e);
            }```
