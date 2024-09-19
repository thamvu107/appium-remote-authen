[Reference](https://www.linkedin.com/pulse/selenium-parallel-testing-using-java-threadlocal-testng-shargo/)

- writing just parallel = "tests" & thread-count ="2" in your testng.xml file won't cut it. That is because Selenium
  WebDriver is not thread-safe by default.

- In a non-multithreaded environment, WebDriver reference static to make it thread-safe. But the problem occurs when we
  try to achieve parallel execution of our tests within the framework.
  Every thread you create to parallelize your tests tries to overwrite the WebDriver reference since there could be only
  one instance of static WebDriver reference.

- To overcome this problem, we can take help from the ThreadLocal class of Java.
- [TestNG-Parallel-execution](https://portal.perforce.com/s/article/TestNG-Parallel-execution)
- [AppiumDriverTest](https://github.com/PerfectoCode/Community-Samples/blob/master/Appium/Java/AppiumDriverTest.java)
- [How to run multiple TestNG XML Files simultaneously sequentially & in parallel using Maven pom.xml](https://www.youtube.com/watch?v=WkdVhy_Qx5o)
