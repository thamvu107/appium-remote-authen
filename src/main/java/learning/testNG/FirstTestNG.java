package learning.testNG;

import org.testng.annotations.*;

public class FirstTestNG extends BaseTestNG {

    @BeforeSuite
    public void beforeSuiteSetup() {
        System.out.println("Before Suite ");
    }

    @AfterSuite
    public void afterSuiteTestDown() {
        System.out.println("After Suite ");
    }

    @BeforeTest
    public void beforeTestSetup() {
        System.out.println("Before Test");
    }

    @AfterTest
    public void afterTestTearDown() {
        System.out.println("After Test");
    }

    @BeforeClass
    public void beforeClassSetup() {
        System.out.println("Before Class ");
    }

    @AfterClass
    public void afterClassTearDown() {
        System.out.println("After Class ");
    }

    @BeforeMethod
    public void setup() {
        System.out.println("\nBefore Method ------------");
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("After Method ------------");
    }

    @Test
    public void test1() {
        System.out.println("Test1 ");
    }

    @Test
    public void test2() {
        System.out.println("Test2 ");
    }

    @Test
    public void test3() {
        System.out.println("Test3 ");
    }

    @Test
    public void test4() {
        System.out.println("Test4 ");
    }

    @Test
    public void test5() {
        System.out.println("Test5 ");
    }

}
