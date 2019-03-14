package staticProxy;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * Created by Administrator on 2019/3/14.
 */
public class OrderServiceStaticProxy implements IOrderService{
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

    private IOrderService iOrderService;

    public OrderServiceStaticProxy(IOrderService iOrderService){
        this.iOrderService =iOrderService;
    }
    @Override
    public int createOrder(Order order) {
        before();
        Long time = order.getCreateTime();
        Integer dbRouter = Integer.valueOf(sdf.format(new Date()));
        System.out.println("静态代理类自动分配到[DB_"+dbRouter+"]数据源处理数据。");
        DynamicDataSourceEntry.set(dbRouter);
        iOrderService.createOrder(order);
        after();
        return 0;
    }

    private void before(){
        System.out.println("Proxy before method.");
    }
    private void after(){
        System.out.println("Proxy after method.");
    }

}
