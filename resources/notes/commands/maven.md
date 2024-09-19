Why we use Maven Framework:

- Dependency management
- Test execution
- Build Lifecycle Management ( validate, compile, package, verify. install, deploy)
- Support CI/CD
- Parallel Execution

-------------------------------

- Notes:
- Get the dependency Tree of a project
    - `mvn dependency:tree `
- Resolving Conflicts using Maven Dependency Tree Verbose Mode
    - `mvn dependency:tree -Dverbose`
    - Ex: `org.junit.jupiter:junit-jupiter-api:jar:5.1.0:test - omitted for conflict with 5.2.0)`
- Filtering the Maven Dependency Tree
    - `-Dincludes`
    - The syntax of the filtering pattern is: `[groupId]:[artifactId]:[type]:[version]`
    - Ex: `mvn dependency:tree -Dverbose -Dincludes=org.junit.jupiter:junit-jupiter-api`
- Saving Dependency Tree to a File
    - `mvn dependency:tree -DoutputFile=dependency-tree.txt`
- `<dependencyManagement> `:
    - Use the <dependencyManagement> section in your pom.xml to specify the version of a dependency that should be used
      throughout the project. This way, even if different versions of the dependency are specified elsewhere, the
      version
      specified in <dependencyManagement> will be used.
- Exclude the unwanted version.
    - ` <exclusions>
      <exclusion>
      <groupId>groupId</groupId>
      <artifactId>artifactId</artifactId>
      </exclusion>
      </exclusions>`

- Run specific test from a test class:
    - `mvn -Dtest=Testclassname#testmethodname test`
- To run all tests in class:
    - `mvn -Dtest=classname test`
- To run test that match a pattern:
    - `mvn -Dtest=Testclassname#testcreate* test`
- Run all test methods match pattern ‘testFoo*’ and ‘testBar*’ from a test class:
    - `mvn test -Dtest=Test1#testFoo*+testBar*`
- To run multiple methods in a testclass:
    - `mvn -Dtest=Testclassname#testone+testtwo test`
- To run Multiple method from multiple class :
    - `mvn test -Dtest=CLASS_NAME1#METHOD_NAME1,CLASS_NAME2#METHOD_NAME2`
- To run multiple test classes:
    - `mvn -Dtest=CLASS_NAME1,CLASS_NAME2`
- Maven: Exclude specific test(s):
    - Exclude one test class, by using the explanation mark (!)
        - `mvn test -Dtest=!LegacyTest`
    - Exclude one test method:
        - `mvn verify -Dtest=!LegacyTest#testFoo`
    - Exclude two test methods:
        - `mvn verify -Dtest=!LegacyTest#testFoo+testBar`
    - Exclude a package with a wildcard (*)
        - `mvn test -Dtest=!com.mycompany.app.Legacy*`
- **Run tests in specific xml file using command line:**
    - step1:Add compiler plugin and surefire plugin in pom.xml.
        - Adding compiler plugin. Source and Target node values represent Java version.
        - Adding Surefire Plugin. “${suiteXmlFile}” is the path for test suite xml file is to be executed, being passed
          from command line.
    - step2:To run test using maven, open Command Line
      change directory to project.
        - `mvn clean test -DsuiteXMLFile=parallelMethod`
- **Run profiles from maven commands:**
    - `mvn test -PRegression`
    - `mvn clean verify -P allure-report`
