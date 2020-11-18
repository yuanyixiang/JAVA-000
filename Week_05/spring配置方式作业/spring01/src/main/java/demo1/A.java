package demo1;

/**
 * @author rd-yyx
 * @version 1.0
 * @date 2020/11/13 3:00 下午
 */
public class A {
    private String name;
    public void sayHello(){
        System.out.println("Hello World "+ name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
