package staticProxy;

/**
 * Created by Administrator on 2019/3/15.
 */
public class Father {
    private Person person;
    //没办法扩展
    public Father(Person person){
        this.person = person;
    }
    //目标对象的引用给拿到
    public void findLove(){
        System.out.println("父母物色对象");
        this.person.findLove();
        System.out.println("双方同意交往，确立关系");
    }
}
