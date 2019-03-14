package staticProxy;

import JDKProxy.GPClassLoader;
import JDKProxy.GPInvocationHandler;
import JDKProxy.GPProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2019/3/14.
 */
public class OrderServiceDynamicProxy implements InvocationHandler {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
    //获取被代理对象的引用
    private Object target;

    public Object getInstance(Object target){
        this.target=target;
        Class clazz =target.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before(args[0]);
        Object obj = method.invoke(target,args);
        after();
        return obj;
    }
    public void before(Object target){
        System.out.println("Proxy before method");
        try {
            Long time = (Long) target.getClass().getMethod("getCreateTime").invoke(target);
            Integer dbRouter =Integer.valueOf(sdf.format(new Date()));
            System.out.println("静态代理类自动分配到【DB_" + dbRouter + "】数据源处理数据。");
            DynamicDataSourceEntry.set(dbRouter);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    public void after(){
        System.out.println("Proxy after method.");
    }
}
