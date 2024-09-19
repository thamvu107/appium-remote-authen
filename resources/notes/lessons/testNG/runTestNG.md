- From IDE (Run test from Configuration file/ button run on IDE)
- Command Line Parameters:
    - `mvn test -DsuiteXmlFile=testNG.xml` (testNG.xml in direct root project)
    - ` mvn test -Dsurefire.suiteXmlFiles=src/test/resources/suites/ParameterSuite.xml` ( file not in direct on root
      project)
    - ` mvn test -Dsurefire.suiteXmlFiles=src/test/resources/suites/explore/ParameterSuite1.xml -Demail="tham.vu@go1.com" -Dpassword="12345678910"`

- System properties
- Running tests from within a test jar

- Lazy init / lazy loading
