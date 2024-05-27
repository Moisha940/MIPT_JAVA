import Annotations.Cache;
import Annotations.Setter;

import java.lang.reflect.Method;
import java.util.*;

public class InputObjectMetaInformation {
    /*
        Возвращет методы помеченные аннотацией Annotations.Cache. Ищет методы по всей иерархию класса.
        Если какой-то метод помеченный аннотацией был переопределен, но не помечен аннотацией то, такой
        метож не учитывается.
    */
    public static Map<String, Method> getCacheCollection(Object inputObject) {
        List<Class<?>> classes = getHierarchyOfInputObject(inputObject);

        Map<String, Method> cacheMethods = new HashMap<>();

        for (int i = classes.size() - 1; i >= 0; i--) {
            Method[] methods = classes.get(i).getDeclaredMethods();
            for (Method method : methods) {
                if (cacheMethods.containsKey(method.getName())) {
                    if (!method.isAnnotationPresent(Cache.class)) {
                        cacheMethods.remove(method.getName());
                    }
                } else {
                    if (method.isAnnotationPresent(Cache.class)) {
                        cacheMethods.put(method.getName(), method);
                    }
                }
            }
        }
        return cacheMethods;
    }

    /*
        Возвращет методы помеченные аннотацией Annotations.Setter. Ищет методы по всей иерархию класса.
        Если какой-то метод помеченный аннотацией был переопределен, но не помечен аннотацией то, такой
        метож не учитывается.
    */
    public static Map<String, Method> getSetterCollection(Object inputObject) {
        List<Class<?>> classes = getHierarchyOfInputObject(inputObject);

        Map<String, Method> setterMethods = new HashMap<>();

        for (int i = classes.size() - 1; i >= 0; i--) {
            Method[] methods = classes.get(i).getDeclaredMethods();
            for (Method method : methods) {
                if (setterMethods.containsKey(method.getName())) {
                    if (!method.isAnnotationPresent(Setter.class)) {
                        setterMethods.remove(method.getName());
                    }
                } else {
                    if (method.isAnnotationPresent(Setter.class)) {
                        setterMethods.put(method.getName(), method);
                    }
                }
            }
        }
        return setterMethods;
    }

    // возвращет иерархию класса
    public static List<Class<?>> getHierarchyOfInputObject(Object inputObjectn) {
        List<Class<?>> classes = new ArrayList<>();
        Class<?> currentClass = inputObjectn.getClass();
        while (currentClass != Object.class) {
            classes.add(currentClass);
            currentClass = currentClass.getSuperclass();
        }
        return classes;
    }
}
