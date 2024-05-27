import java.lang.reflect.Proxy;


public class Utils {
    private Object s1;

    public Utils(Object obj) {
        this.s1 = obj;
    }

    // возвращаем интерфейс, который имплементится в класс
    public static Able cache (Object s1) {
        return (Able) Proxy.newProxyInstance(s1.getClass().getClassLoader(),
                s1.getClass().getInterfaces(),
                new TestInvocationHandler(s1)
        );
    }
}
