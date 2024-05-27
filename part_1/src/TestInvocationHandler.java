import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.*;

public class TestInvocationHandler implements InvocationHandler {

    private Object inputObject;
    private Map<Method, Object> cacheOfMethods = new HashMap<>();

    public TestInvocationHandler(Object inputObject) {
        this.inputObject = inputObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // смотрим есть ли наш метод в кэшМапе
        if (cacheOfMethods.containsKey(method)) {
            // этот метод кэширован, возвращаем вызов этого метода на сохраненом объекте
            System.out.println("Этот ответ взят из кэша:");
            return method.invoke(cacheOfMethods.get(method), args);
        }

        // возвращается метод класса,
        // у корторого была замеченна одна из ключевых аннотаций
        // эта функция нужна, по скольку у насы перехватывает вызов функции у интерфейса,
        // у которого нет аннотаций. Ищем этот метод в классе и смотрим на его аннотацию
        Method annotationOfMethod =  methodFromObject(inputObject, method);

        if (annotationOfMethod != null && annotationOfMethod.isAnnotationPresent(Cache.class)) {
            // у метода есть аннотация cache
            // добавим объект в кэш
            cacheOfMethods.put(method, inputObject);
        } else if (annotationOfMethod != null && annotationOfMethod.isAnnotationPresent(Setter.class)) {
            // у метода есть аннотация setter
            // тогда надо очистить кэш
            cacheOfMethods.clear();
        }


        // мы должны что-то вернуть
        if (method.getReturnType().equals(void.class)) {
            // перехваченный метод ничего не возвращает
            method.invoke(inputObject, args);
            return null;
        } else {
            // перехваченный метод что-то возвращает
            return method.invoke(inputObject, args);
        }
    }

    private Method methodFromObject(Object obj, Method method) {
        // лист методов объекта
        ArrayList<Method> objectMethods = new ArrayList<>(List.of(obj.getClass().getDeclaredMethods()));

        for (Method m : objectMethods) {
            // смотрим, чтобы метод класса совпадал с перехваченным методом
            if (m.getName().equals(method.getName())) {
                if (m.isAnnotationPresent(Cache.class) || m.isAnnotationPresent(Setter.class) ) {
                    return m;
                }
            }
        }
        return null;
    }
}
