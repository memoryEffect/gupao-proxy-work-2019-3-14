package staticProxy;

/**
 * Created by Administrator on 2019/3/14.
 * 动态切换数据源
 */
public class DynamicDataSourceEntry {
    //默认数据源
    public final static String DEFAULT_SOURCE = null;

    //线程单例
    private static final ThreadLocal<String> thread =new ThreadLocal<String>();

    private DynamicDataSourceEntry(){}

    //清空数据源
    public static  void clear(){
        thread.remove();
    }

    //获取当前正在使用的数据源名字

    public static  void get(){
        thread.get();
    }

    //还原当前切面的数据源
    public static  void restore(){
        thread.set(DEFAULT_SOURCE);
    }

    //设置已知的数据源名字
    public static  void set(String source){
        thread.set(source);
    }

    //根据年份动态设置数据源
    public static  void set(int year){
        thread.set("DB_"+year);
    }
}
