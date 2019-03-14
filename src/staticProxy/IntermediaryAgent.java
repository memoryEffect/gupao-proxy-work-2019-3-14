package staticProxy;

/**
 * Created by Administrator on 2019/3/14.
 * 中介
 */
public class IntermediaryAgent {
    private House house;

    public IntermediaryAgent(House house){
        this.house = house;
    }
    //拿到目标对象的引用
    public void findHouse(){
        System.out.println("看房子");
        this.house.findHoust();
        System.out.println("签合同");
    }
}
