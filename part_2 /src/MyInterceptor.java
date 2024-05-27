import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class MyInterceptor {
    private Object inputObject;
    private Map<String, Method> cacheCollection;
    private Map<String, Method> setterCollection;

    private Map<Method, Object> cacheOfMethods = new HashMap<>();

    public MyInterceptor(Map<String, Method> setterCollection, Map<String, Method> cacheCollection, Object inputObject)  {
        this.setterCollection = setterCollection;
        this.cacheCollection = cacheCollection;
        this.inputObject = inputObject;
    }

    @RuntimeType
    public Object intercept(@AllArguments Object[] allArguments,
                            @Origin Method method) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        if (cacheOfMethods.containsKey(method)) {
            // этот метод кэширован, возвращаем вызов этого метода на сохраненом объекте
            System.out.println("Этот ответ взят из кэша:");
            return method.invoke(cacheOfMethods.get(method), allArguments);
        }


        if (setterCollection.containsKey(method.getName())) {
            cacheOfMethods.clear();
        }

        if (cacheCollection.containsKey(method.getName())) {
            cacheOfMethods.put(method, inputObject);
        }

        if (method.getReturnType().equals(void.class)) {
            // перехваченный метод ничего не возвращает
            method.invoke(inputObject, allArguments);
            return null;
        } else {
            // перехваченный метод что-то возвращает
            return method.invoke(inputObject, allArguments);
        }
    }
}
