- 
- Prepare env for the jenkins executor:
    - Java JDK installed
    - Maven
      -  Go to **Manage jenkins** > **Tools** (http://localhost:8080/manage/configureTools/)
        - Add Maven name
        - Add Maven_Home
          - If Maven already installed then run command : `mvn -v` to get MAVEN_HOME
    - Allure report plugin
      - Go to **Plugin** search Allure.
      - Select allure plugin then apply
      - Go to Manage Jenkins > Tools
        - Add Allure command line: 
          - Name
          - Install automatically ( select version)
    - Parameter Trigger plugin
    - Conditional BuildStep Plugin
    - 
    
- download jenkins (LTS)
- Start Jenkins:`java -jar path/jenkins.war`
  - `java -jar jenkins/jenkins.war`

- Build one run every where
 - POM
   - fat-test
   - Update main entry
      - ``` <transformers>
                                   <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                                       <mainClass>testV2.MainTestV1</mainClass>
                                   </transformer>
                               </transformers>```
   - Command to build:
   - `mvn clean package -DskipTests`
   - `java -DplatformType=${platformType} -DmobileRunMode=${mobileRunMode} -Dgrid.username=${grid_username} -Dgrid.password=${grid_password} -jar ./target/appium-1.0-SNAPSHOT-fat-tests.jar`

