- `npm root -g`
- `which appium`
- Installing Appium 2.0
    - Install with last version:
        - `npm install -g appium`
    - install appium with specific version
        - `npm install -g appium@2.5.1`
- Check appium version:
    - `appium -v`
- Update version:
    - `npm update -g appium`
- uninstall appium:
    - remove appium
        - `npm uninstall -g appium`
    - remove appium folder
        - `sudo rm -rf /usr/local/lib/node_modules/appium`

- Installing a driver:
    - `appium driver install <driver name>`
    - `appium driver install uiautomator2`

- View driver list:
    - `appium driver list`

- Updating a driver:
    - `appium driver update <driver name>`
    - `appium driver update xcuitest`

- Updating driver list:
    - `appium driver list --updates`:
        - by default Appium will not let you update a driver across a major version boundary, to keep you safe from
          breaking changes.
    - `appium driver update --unsafe`
        - --unsafe you are forcing the update of the Appium driver without strict safety checks.
    - `appium driver update <driver-name>`
        - This will NOT update the major version, in order to prevent breaking changes
    - ` appium driver update <driver-name> --unsafe`
        - Update a driver to the most recent version (may include breaking changes)

- Uninstall a driver:
    - `appium driver uninstall <driver name>`
    - `appium driver uninstall xcuitest`
    - `npm uninstall appium-uiautomator2-driver.`

- Appium plugins Installation:
    - `appium plugin install <plugin name>`
    - `appium plugin install execute-driver`
    - Appium
      - 

- Listing all the Appium plugins:
    - `appium plugin list`

- Updating an Appium plugin:
    - `appium plugin list --updates`
    - `appium plugin update <plugin name>`

- Uninstalling a plugin:
    - `appium plugin uninstall <plugin name>`

- Starting the Appium Server with specific Appium Driver:
    - `appium server --use-driver=xcuitest`
