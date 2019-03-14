package JDKProxy;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2019/3/14.
 */
public class GPProxy {
    public static final  String ln  ="\r\n";
    public static  Object newProxyInstance(GPClassLoader classLoader,Class[] intefaces,GPInvocationHandler h){
            //1.动态生成源代码.java文件
            String src = generateSrc(intefaces);

            //2.java文件输出磁盘
        String filePath =GPProxy.class.getResource("").getPath();
        System.out.println(filePath);
        File  file = new File("E://$Proxy0.java");
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(src);
            fw.flush();
            fw.close();
          //3.把生成的.java文件编译成.class文件
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manager = compiler.getStandardFileManager(null,null,null);
            Iterable iterable = manager.getJavaFileObjects(file);

            JavaCompiler.CompilationTask task = compiler.getTask(null,manager,null,null,null,iterable);
            task.call();
            manager.close();

            //4.编译生成的.class文件加载到jvm中来

            Class proxyClass=classLoader.findClass("$Proxy0");
            Constructor c= proxyClass.getConstructor(GPInvocationHandler.class);
            file.delete();
            //5.返回字节码重组以后新的代理对象
            return c.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String generateSrc(Class<?>[] interfaces){
        StringBuffer sb = new StringBuffer();
        sb.append("package JDKProxy;"+ln);
        sb.append("import staticProxy.IOrderService;" + ln);
        sb.append("import java.lang.reflect.Method;" + ln);
        System.out.println(interfaces[0].getName());
            sb.append("public class $Proxy0 implements " + interfaces[0].getName() + "{" + ln);
            sb.append("GPInvocationHandler h;" + ln);
            sb.append("public $Proxy0(GPInvocationHandler h) {" + ln);
            sb.append("this.h=h;");
            sb.append("}" + ln);
            for(Method m :interfaces[0].getMethods()){
                sb.append("public" + m.getReturnType().getName()+" "+m.getName() +"(){" + ln );
                sb.append("try{" + ln);
                sb.append("Method m = " + interfaces[0].getName() + ".class.getMethod(\"" + m.getName() + "\",new Class[]{});" + ln);
                sb.append("this.h.invoke(this,m,null);" + ln);
                sb.append("}catch(Throwable e){" + ln);
                sb.append("e.printStackTrace();" + ln);
                sb.append("}");
                sb.append("}");
            }
        sb.append("}" + ln);
        return sb.toString();
    }
}
