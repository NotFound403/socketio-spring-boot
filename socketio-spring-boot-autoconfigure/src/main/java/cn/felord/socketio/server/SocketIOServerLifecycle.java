package cn.felord.socketio.server;

import com.corundumstudio.socketio.SocketIOServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.SmartLifecycle;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author felord.cn
 * @since 2020/9/12 23:07
 */
@Slf4j
public class SocketIOServerLifecycle implements SmartLifecycle, ApplicationContextAware {

    private final SocketIOServer socketIOServer;
    private final AtomicBoolean isRunning = new AtomicBoolean();
    private   ApplicationContext applicationContext;

    public SocketIOServerLifecycle(SocketIOServer socketIOServer) {
        Assert.notNull(socketIOServer, "SocketIOServer is not defined");
        this.socketIOServer = socketIOServer;
    }

    @Override
    public void start() {

        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(SocketNameSpace.class);

          beansWithAnnotation.forEach((k,bean)->{
              SocketNameSpace annotation = bean.getClass().getAnnotation(SocketNameSpace.class);
              String s = annotation.nameSpace();
              socketIOServer.addNamespace(s).addListeners(bean);
          });

        socketIOServer.start();
        this.isRunning.compareAndSet(false, true);
        log.info("SocketIO Server is started");
    }

    @Override
    public void stop() {
        socketIOServer.stop();
        this.isRunning.compareAndSet(true, false);
        log.info("SocketIO Server is stopped");
    }

    @Override
    public boolean isRunning() {
        return this.isRunning.get();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext= applicationContext;
    }
}
