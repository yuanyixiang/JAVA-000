package demo2;

/**
 * @author rd-yyx
 * @version 1.0
 * @date 2020/11/13 3:12 下午
 */
public class B {
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
