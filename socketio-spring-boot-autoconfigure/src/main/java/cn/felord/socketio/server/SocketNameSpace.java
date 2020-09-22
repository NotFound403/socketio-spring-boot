package cn.felord.socketio.server;

import java.lang.annotation.*;

/**
 * The interface Socket name space.
 *
 * @author felord.cn
 * @since 2020 /9/13 16:30
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SocketNameSpace {
    /**
     * Name space string [ ].
     *
     * @return the string [ ]
     */
    String value();
}
