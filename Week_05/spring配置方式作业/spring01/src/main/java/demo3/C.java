package demo3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author rd-yyx
 * @version 1.0
 * @date 2020/11/13 3:46 下午
 */
@Component("C")
public class C {
    @Autowired
    private D d;

    public void sayHello(){
        d.sayHello();
    }

}
