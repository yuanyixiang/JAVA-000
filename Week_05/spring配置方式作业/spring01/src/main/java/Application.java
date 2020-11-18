import demo1.A;
import demo2.B;
import demo3.C;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author rd-yyx
 * @version 1.0
 * @date 2020/11/13 3:00 下午
 */
public class Application {
    public static void main(String[] args) {
//        demo1();
//        demo2();
        demo3();
    }
    private static void demo1(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        A a = (A) applicationContext.getBean("a2");
        a.sayHello();
    }
    private static void demo2(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        B b = (B) applicationContext.getBean("factoryB");
        b.sayHello();
    }
    private static void demo3(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        C c = (C) applicationContext.getBean("C");
        c.sayHello();
    }
}
