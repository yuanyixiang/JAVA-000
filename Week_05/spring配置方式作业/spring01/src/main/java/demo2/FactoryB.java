package demo2;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author rd-yyx
 * @version 1.0
 * @date 2020/11/13 3:13 下午
 */
public class FactoryB implements FactoryBean<B> {
    @Override
    public B getObject() throws Exception {
        B b = new B();
        b.setName("yyx");
        return b;
    }

    @Override
    public Class<?> getObjectType() {
        return B.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
