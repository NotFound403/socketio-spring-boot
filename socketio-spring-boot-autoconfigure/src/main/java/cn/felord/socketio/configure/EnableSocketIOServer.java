package cn.felord.socketio.configure;

import cn.felord.socketio.server.SocketNameSpaceBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * The interface Enable socket io server.
 *
 * @author felord.cn
 * @since 2020 /9/12 22:01
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({SocketIOConfiguration.class, SocketNameSpaceBeanDefinitionRegistrar.class})
public @interface EnableSocketIOServer {

    /**
     * Base packages string [ ].
     *
     * @return the string [ ]
     */
    String[] basePackages();
}
