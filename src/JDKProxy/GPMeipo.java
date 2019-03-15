package JDKProxy;

import staticProxy.Person;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2019/3/15.
 */
public class GPMeipo implements  GPInvocationHandler {
    //被代理的对象，把引用给保存下来
    private Object target;
    public Object getInstance(Object person) throws Exception{
        this.target = person;
        Class<?> clazz = target.getClass();
        return GPProxy.newProxyInstance(new GPClassLoader(),clazz.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object obj = method.invoke(this.target,args);
        after();
        return obj;
    }
    private void before(){
        System.out.println("我是媒婆：我要给你找对象，现在已经拿到你的需求");
        System.out.println("开始物色");
    }
    private void after(){
        System.out.println("如果合适的话，就准备办事");
    }

}
