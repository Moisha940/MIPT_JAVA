import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static net.bytebuddy.matcher.ElementMatchers.*;

public class Utils {
    public static Object cache (Object inputObject) throws InstantiationException, IllegalAccessException, NoSuchMethodException {
        Class<?> clazz = inputObject.getClass();

        ByteBuddy byteBuddy = new ByteBuddy();
        DynamicType.Builder<?> builder = byteBuddy.subclass(clazz);

        Map<String, Method> cacheCollection = InputObjectMetaInformation.getCacheCollection(inputObject);
        Map<String, Method> setterCollection = InputObjectMetaInformation.getSetterCollection(inputObject);

        Map<String, Method> methodsWithAnnotations = new HashMap<>(cacheCollection);
        methodsWithAnnotations.putAll(setterCollection);

        MyInterceptor myInterceptor = new MyInterceptor(setterCollection, cacheCollection, inputObject);


        for (Method method : methodsWithAnnotations.values()) {
            builder = builder.method(named(method.getName())
                    .and(takesArguments(method.getParameterTypes()))
                    .and(returns(method.getReturnType())))
                    .intercept(MethodDelegation.to(myInterceptor));
        }

        return builder.make()
                .load(clazz.getClassLoader())
                .getLoaded()
                .newInstance();
    }
}