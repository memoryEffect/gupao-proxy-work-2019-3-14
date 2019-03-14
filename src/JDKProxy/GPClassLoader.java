package JDKProxy;

import com.sun.org.apache.bcel.internal.util.ClassPath;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;

/**
 * Created by Administrator on 2019/3/14.
 */
public class GPClassLoader extends  ClassLoader {
    private File classPathFile;
    public GPClassLoader(){
        String classPath = GPClassLoader.class.getResource("").getPath();
        this.classPathFile= new File(classPath);
    }

    protected  Class<?> findClass(String name) throws ClassNotFoundException{
        String className = GPClassLoader.class.getPackage().getName() +"." +name;
        if(classPathFile!=null){
            File classFile = new File(classPathFile,name.replaceAll("\\.","/")+".class");
            if(classFile.exists()){
                FileInputStream in = null;
                ByteArrayOutputStream out = null;
                try {
                    in = new FileInputStream(classFile);
                    out = new ByteArrayOutputStream();
                    byte [] buff = new byte[1024];
                    int len;
                    while ((len = in.read(buff)) != -1){
                        out.write(buff,0,len);
                    }
                    return defineClass(className,out.toByteArray(),0,out.size());
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if(null != in){

                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if(out != null){
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                }

            }
            }
        }
        return null;
    }
}
