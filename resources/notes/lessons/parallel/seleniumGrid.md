[Selenium Grid 4](https://www.selenium.dev/documentation/grid/getting_started/)
[Selenium grid 4 components | Role of each selenium grid component | How Selenium grid works](https://www.youtube.com/watch?v=KPjiWsmRPfQ&list=PL6SXxvjnlkaQl0o6b_an7DwKhA-CBEbQH&index=2)
[How to setup Selenium Grid 4 in Standalone mode | Run automation tests in parallel using grid](https://www.youtube.com/watch?v=b9s5bhoXlyo&list=PL6SXxvjnlkaQl0o6b_an7DwKhA-CBEbQH&index=3)
[Selenium Grid 4 and Appium together in harmony](https://www.youtube.com/watch?v=3_aP2rsqZD0)

- Standalone:
    - Syntax:
        - `java -jar selenium-server-<version>.jar standalone`
    - A Grid of one, useful for local testing or small test suites
    - Common use cases for Standalone are:
        - Develop or debug tests using RemoteWebDriver locally
        - Running quick test suites before pushing code
        - Have a easy to setup Grid in a CI/CD tool (GitHub Actions, Jenkins, etc…)

- Hub/Node:
    - Traditional setup from Grid 3 is still present, enables a swift migration to Grid 4
    - Hub and Node is the most used role because it allows to:
        - Combine different machines in a single Grid
        - Machines with different operating systems and/or browser versions, for example
        - Have a single entry point to run WebDriver tests in different environments
        - Scaling capacity up or down without tearing down the Grid
    - A Hub is composed by the following components:
        - Router,
        - Distributor,
        - Session Map,
        - New Session Queue,
        - Event Bus.
        - Syntax:
            - `java -jar selenium-server-<version>.jar hub`
    - Node:
        - Syntax:
            - `java -jar selenium-server-<version>.jar node`
        - Same machine:
            - Node1:
                - `java -jar selenium-server-<version>.jar node --port 5555`
            - Node2:
                - `java -jar selenium-server-<version>.jar node --port 6666`
        - Node and Hub on different machines:
            - If the Hub is using the default ports, the --hub flag can be used to register the Node:
                - Syntax:
                    - `java -jar selenium-server-<version>.jar node --hub http://<hub-ip>:4444`
            - When the Hub is not using the default ports, the _--publish-events_ and _--subscribe-events_ flags are
              needed.
                - For example, if the Hub uses ports 8886, 8887, and 8888
                - Syntax:
                    - `java -jar selenium-server-<version>.jar hub --publish-events tcp://<hub-ip>:8886 --subscribe-events tcp://<hub-ip>:8887 --port 8888`
            - The Node needs to use those ports to register successfully
                - Syntax:
                    - `java -jar selenium-server-<version>.jar node --publish-events tcp://<hub-ip>:8886 --subscribe-events tcp://<hub-ip>:8887`


- Distributed:
    - Maximum flexibility that enables better performance for each component
