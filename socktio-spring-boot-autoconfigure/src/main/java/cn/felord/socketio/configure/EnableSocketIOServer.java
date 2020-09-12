package cn.felord.socketio.configure;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author felord.cn
 * @since 2020/9/12 22:01
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(SocketIOConfiguration.class)
public @interface EnableSocketIOServer {
}
