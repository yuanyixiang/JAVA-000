package Parser;

import entity.User;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * @author rd-yyx
 * @version 1.0
 * @date 2020/11/17 3:34 下午
 */
public final class yyxBeanDefinitionParser extends AbstractBeanDefinitionParser {

    @Override
    protected AbstractBeanDefinition parseInternal(Element element, ParserContext parserContext) {
        BeanDefinitionBuilder factory = BeanDefinitionBuilder.rootBeanDefinition(User.class);
        factory.addConstructorArgValue(element.getAttribute("name"));
        factory.addConstructorArgValue(element.getAttribute("age"));
        return factory.getBeanDefinition();
    }

}
