package cl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author rd-yyx
 * @version 1.0
 * @date 2020/10/18 11:10 下午
 */
public class MyCLoader extends ClassLoader {
    public static void main(String[] args) {
        try {
            Class clazz = new MyCLoader().findClass("Hello");
            Method helloMthod = clazz.getMethod("hello");
            helloMthod.invoke(clazz.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //加载文件
        File file = null;
        byte[] fileByte = null;
        try {
            URL url = MyCLoader.class.getClassLoader().getResource("cl/Hello.xlass");
            file = new File(url.toURI().getPath());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        //将文件读入并转为字节数组
        try(
                FileInputStream fis = new FileInputStream(file);
        ){
             fileByte = ftoB(fis);
             fileByte = deCode(fileByte);
        } catch (Exception e){
            e.printStackTrace();
        }

        return defineClass(name,fileByte,0,fileByte.length);
    }

    public byte[] ftoB(FileInputStream fis){
        byte[] b = new byte[1024];
        byte[] fileByte = null;
        int len = -1;
        try(ByteArrayOutputStream bos = new ByteArrayOutputStream()){
            while((len = fis.read(b)) != -1){
                bos.write(b,0,len);
            }
            fileByte = bos.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
        }
        return fileByte;
    }

    public byte[] deCode(byte[] fileByte){
        for(int i = 0;i < fileByte.length;i++){
            fileByte[i] = (byte)(255 - fileByte[i]);
        }
        return fileByte;
    }
}
