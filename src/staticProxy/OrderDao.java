package staticProxy;

/**
 * Created by Administrator on 2019/3/14.
 */
public class OrderDao {
    public int insert(Order order){
        System.out.println("OrderDao 创建成功");
        return 1;
    };
}
