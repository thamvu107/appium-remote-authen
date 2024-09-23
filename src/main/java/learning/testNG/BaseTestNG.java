package learning.testNG;

import org.testng.annotations.*;

public abstract class BaseTestNG {
    @BeforeSuite
    public void beforeSuiteSetup() {
        System.out.println("Before Suite ---------- Base TestNG");
    }

    @AfterSuite
    public void afterSuiteTestDown() {
        System.out.println("After Suite ---------- Base TestNG");
    }

    @BeforeTest
    public void beforeTestSetup() {
        System.out.println("Before Test ---------- Base TestNG");
    }

    @AfterTest
    public void afterTestTearDown() {
        System.out.println("After Test ---------- Base TestNG");
    }

    @BeforeClass
    public void beforeClassSetup() {
        System.out.println("Before Class ---------- Base TestNG");
    }

    @AfterClass
    public void afterClassTearDown() {
        System.out.println("After Class ---------- Base TestNG");
    }

    @BeforeGroups
    public void beforeGroupSetup() {
        System.out.println("Before Group ---------- Base TestNG");
    }

    @AfterGroups
    public void afterGroupTearDown() {
        System.out.println("After Group ---------- Base TestNG");
    }


    @BeforeMethod
    public void setup() {
        System.out.println("\nBefore Method ----------- ---------- Base TestNG");
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("After Method ------------");
    }

}
