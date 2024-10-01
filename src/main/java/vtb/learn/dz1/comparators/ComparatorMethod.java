package vtb.learn.dz1.comparators;



import vtb.learn.dz1.annotation.Test;

import java.lang.reflect.Method;
import java.util.Comparator;

/**
 * Компаратор для Test.class
 *
 * @author VSKudryavtsev
 * @since 17.09.2024 0:48
 **/
public class ComparatorMethod implements Comparator<Method> {


    @Override
    public int compare(Method o1, Method o2) {
        return Integer.compare(o2.getAnnotation(Test.class).priority(), o1.getAnnotation(Test.class).priority());
    }

}
