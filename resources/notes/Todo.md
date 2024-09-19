- TODO list:
- Retry failed test: https://testng.org/rerunning_failed_tests.html
- Refactor POM reference POM.md file.
- Separate framework and test
- exDriver - exAndroidDriver, exIOSDriver - methods ( lesson 21)
- Log
- Report
- multi apps
- authen data dynamic
- explore stream in java
- map
- lambada expression
- Close driver before run test ( in case before running happen exception then driver not close)
  // Wait until the component displayed on the page
  // In case the component is not on screen(for Android) need to swipe the screen
  // TODO: Explore this logic
  waitUtils.explicitWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(componentSel));
