package JDKProxy;

import staticProxy.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2019/3/14.
 */
public class GPOrderTest {
    public static void main(String[] args) {
        /*Order order = new Order();

        try {
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy/MM/dd");
        Date date =sdf.parse("2019/3/14");
        order.setCreateTime(date.getTime());

        IOrderService iOrderService = new OrderServiceStaticProxy(new OrderService());
        iOrderService.createOrder(order);
        } catch (ParseException e) {
            e.printStackTrace();
        }*/

//        Order order = new Order();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//        Date date = null;
//        try {
//            date = sdf.parse("2019/3/14");
//            order.setCreateTime(date.getTime());
//            IOrderService iOrderService= (IOrderService) new GPOrderServiceDynamicProxy().getInstance(new OrderService());
//            iOrderService.createOrder(order);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        try {
            Person obj = (Person)new GPMeipo().getInstance(new Customer());
            System.out.println(obj.getClass());
            obj.findLove();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
