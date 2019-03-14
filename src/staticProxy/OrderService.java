package staticProxy;

/**
 * Created by Administrator on 2019/3/14.
 */
public class OrderService implements IOrderService {
    private OrderDao orderDao;

    public OrderService(){
        orderDao =new OrderDao();
    }

    @Override
    public int createOrder(Order order) {
        System.out.println("orderService调用orderDao创建订单");
        return orderDao.insert(order);
    }
}
