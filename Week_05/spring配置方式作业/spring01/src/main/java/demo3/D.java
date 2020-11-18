package demo3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author rd-yyx
 * @version 1.0
 * @date 2020/11/13 3:47 下午
 */
@Component
public class D {
    @Value("yyx")
    private String name;

    public void sayHello(){
        System.out.println("Hello World "+ name);
    }
}
