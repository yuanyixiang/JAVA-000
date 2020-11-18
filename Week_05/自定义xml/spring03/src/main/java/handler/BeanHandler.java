package handler;

import Parser.yyxBeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author rd-yyx
 * @version 1.0
 * @date 2020/11/17 3:29 下午
 */
public class BeanHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("yyx",new yyxBeanDefinitionParser());
    }
}
