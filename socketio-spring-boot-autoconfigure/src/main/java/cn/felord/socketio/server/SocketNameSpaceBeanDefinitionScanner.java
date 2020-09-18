package cn.felord.socketio.server;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

/**
 * @author felord.cn
 * @since 2020/9/13 21:04
 */
public class SocketNameSpaceBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {


    public SocketNameSpaceBeanDefinitionScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters) {
        super(registry, useDefaultFilters);
        addIncludeFilter(new AnnotationTypeFilter(SocketNameSpace.class));
    }

}
