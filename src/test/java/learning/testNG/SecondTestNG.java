package learning.testNG;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class SecondTestNG extends BaseTestNG {
    @BeforeGroups
    public void beforeGroupSetup() {
        System.out.println("Before Group ---------- ");
    }

    @AfterGroups
    public void afterGroupTearDown() {
        System.out.println("After Group ---------- ");
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
