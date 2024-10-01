package vtb.learn.dz1;


import vtb.learn.dz1.component.TestRunner;
import vtb.learn.dz1.service.Service;

public class Main {
    public static void main(String[] args) {
        TestRunner.runTests(Service.class);
    }
}