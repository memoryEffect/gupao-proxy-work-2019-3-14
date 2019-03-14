package JDKProxy;

import staticProxy.IOrderService;
import staticProxy.Order;
import staticProxy.OrderService;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

/**
 * Created by Administrator on 2019/3/14.
 */
public class JDKProxyTest {
    public static void main(String[] args) {
        Order order = new Order();
        IOrderService iOrderService = (IOrderService) new GPOrderServiceDynamicProxy().getInstance(new OrderService());
        iOrderService.createOrder(order);

        //通过反编译工具可以查看源代码
        byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0",new Class[]{IOrderService.class});
        try {
            FileOutputStream fos =new FileOutputStream("E://$Proxt0.class");
            fos.write(bytes);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
