package cn.felord.socketio.server;

import com.corundumstudio.socketio.SocketIOServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.SmartLifecycle;
import org.springframework.util.Assert;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author felord.cn
 * @since 2020/9/12 23:07
 */
@Slf4j
public class SocketIOServerLifecycle implements SmartLifecycle {

    private final SocketIOServer socketIOServer;
    private final AtomicBoolean isRunning = new AtomicBoolean();

    public SocketIOServerLifecycle(SocketIOServer socketIOServer) {
        Assert.notNull(socketIOServer, "SocketIOServer is not defined");
        this.socketIOServer = socketIOServer;
    }

    @Override
    public void start() {
        socketIOServer.start();
        this.isRunning.compareAndSet(false, true);
        log.info(" SocketIOServer is started");
    }

    @Override
    public void stop() {
        socketIOServer.stop();
        this.isRunning.compareAndSet(true, false);
        log.info(" SocketIOServer is stop");
    }

    @Override
    public boolean isRunning() {
        return this.isRunning.get();
    }
}
