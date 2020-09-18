package cn.felord.socketio.server;

import cn.felord.socketio.configure.EnableSocketIOServer;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

/**
 * @author felord.cn
 * @since 2020/9/13 21:00
 */
public class SocketNameSpaceBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {

    private ResourceLoader resourceLoader;


    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        String[] packagesToScan = getBasePackagesToScan(importingClassMetadata);
        SocketNameSpaceBeanDefinitionScanner scanner = new SocketNameSpaceBeanDefinitionScanner(registry, false);
        scanner.setResourceLoader(resourceLoader);
        scanner.scan(packagesToScan);

    }

    private String[] getBasePackagesToScan(AnnotationMetadata metadata) {
        String name = EnableSocketIOServer.class.getName();
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(name, true));
        Assert.notNull(attributes, () -> "No auto-configuration attributes found. Is " + metadata.getClassName()
                + " annotated with " + ClassUtils.getShortName(name) + "?");
        return attributes.getStringArray("basePackages");
    }


    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
