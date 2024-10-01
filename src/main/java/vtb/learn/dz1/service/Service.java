package vtb.learn.dz1.service;


import vtb.learn.dz1.annotation.AfterSuite;
import vtb.learn.dz1.annotation.BeforeSuite;
import vtb.learn.dz1.annotation.Test;

/**
 * @author VSKudryavtsev
 * @since 16.09.2024 20:20
 **/
public class Service {


    @BeforeSuite
    public static void test(String st){
        System.out.println(st);
    }

    @AfterSuite
    private static void test4(String l){
        System.out.println(l);
    }

    @Test(priority = 1)
    public void test1(String st){
        System.out.println(st.concat(" priority=1"));
    }

    @Test(priority = 3)
    private void test2(String st){
        System.out.println(st.concat(" priority=3"));
    }

    @Test(priority = 9)
    public static void test3(String st){
        System.out.println(st.concat(" priority=9"));
    }


}
