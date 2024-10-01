package vtb.learn.dz1.component;


import vtb.learn.dz1.annotation.AfterSuite;
import vtb.learn.dz1.annotation.BeforeSuite;
import vtb.learn.dz1.annotation.Test;
import vtb.learn.dz1.comparators.ComparatorMethod;
import vtb.learn.dz1.service.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;


/**
 * @author VSKudryavtsev
 * @since 16.09.2024 20:20
 **/
public class TestRunner {

    public static void runTests(Class<?> cl) {
        try {
            ComparatorMethod comparatorMethod = new ComparatorMethod();
            Service serviceInstance = new Service();
            Class<?> cls = Class.forName(cl.getName());
            var methods = cls.getDeclaredMethods();
            Arrays.stream(methods)
                    .forEach(method -> method.setAccessible(true));

            for (Method method : methods) {
                var isAnnotation = method.isAnnotationPresent(Test.class);
                if (isAnnotation) {
                    var priority = method.getAnnotation(Test.class).priority();
                    if (priority <= 0 || priority >= 10)
                        System.out.println("Ошибка валидации, priority должен быть в пределах от 0 до 10");
                }
            }

            var afterSuiteCountMethod = Arrays.stream(methods)
                    .filter(method -> method.isAnnotationPresent(AfterSuite.class))
                    .peek(method -> {
                                if (!Modifier.isStatic(method.getModifiers())) {
                                    System.out.println("Ошибка валидации, AfterSuiteдолжны быть только на статических методах");

                                }
                            }
                    )
                    .count();
            if (afterSuiteCountMethod > 1) {
                System.out.println("Аннотации AfterSuite больше одного");
                throw new RuntimeException("Аннотации AfterSuite больше одного");
            }


            var beforeSuiteCountMethod = Arrays.stream(methods)
                    .filter(method -> method.isAnnotationPresent(BeforeSuite.class))
                    .peek(method -> {
                                if (!Modifier.isStatic(method.getModifiers())) {
                                    System.out.println("Ошибка валидации, BeforeSuite должны быть только на статических методах");
                                    throw new RuntimeException("Ошибка валидации, BeforeSuite должны быть только на статических методах");
                                }
                            }
                    )
                    .count();
            if (beforeSuiteCountMethod > 1) {
                System.out.println("Аннотации BeforeSuite больше одного");
                throw new RuntimeException("Аннотации BeforeSuite больше одного");
            }

            Arrays.stream(methods)
                    .filter(method -> method.isAnnotationPresent(BeforeSuite.class))
                    .findFirst()
                    .orElseThrow()
                    .invoke(serviceInstance, "BeforeSuite над статическим методом");

            Arrays.stream(methods)
                    .filter(method -> method.isAnnotationPresent(Test.class))
                    .sorted(comparatorMethod)
                    .forEach(method -> {
                        try {
                            method.invoke(serviceInstance, "Test");
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            throw new RuntimeException(e);
                        }
                    });

            Arrays.stream(methods)
                    .filter(method -> method.isAnnotationPresent(AfterSuite.class))
                    .findFirst()
                    .orElseThrow()
                    .invoke(serviceInstance, "AfterSuite над статическим методом");

        } catch (ClassNotFoundException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }

    }

}
