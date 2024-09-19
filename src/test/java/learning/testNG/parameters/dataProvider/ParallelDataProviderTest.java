package learning.testNG.parameters.dataProvider;

import org.testng.annotations.DataProvider;

public class ParallelDataProviderTest {
  @DataProvider(name = "testData", parallel = true)
  public Object[][] provideData() {
    return new Object[][] {
      {"user1", "password1"},
      {"user2", "password2"},
      {"user3", "password3"},
      {"user4", "password4"},
      {"user5", "password5"}

    };

  }
}
