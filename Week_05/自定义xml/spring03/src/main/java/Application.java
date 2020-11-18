import entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author rd-yyx
 * @version 1.0
 * @date 2020/11/17 3:44 下午
 */
public class Application {
    public static void main(String[] args) {
        ApplicationContext beans=new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = (User) beans.getBean("yBean",User.class);
        System.out.println(user.toString());
    }
}
