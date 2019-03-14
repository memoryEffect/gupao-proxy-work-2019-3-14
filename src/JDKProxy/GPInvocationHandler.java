package JDKProxy;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2019/3/14.
 */
public interface GPInvocationHandler {
    public Object invoke(Object proxy,Method method,Object[] args) throws Throwable;
}
