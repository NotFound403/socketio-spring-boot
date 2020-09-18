package cn.felord.socketio.server;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.util.Assert;

/**
 * @author felord.cn
 * @since 2020/9/11 22:45
 */
public class ConfigurableSocketIOServerFactory implements FactoryBean<SocketIOServer> {

    private Configuration configuration;

    @Override
    public SocketIOServer getObject() {
        Assert.notNull(configuration,"com.corundumstudio.socketio.Configuration must not be null");
        return new SocketIOServer(configuration);
    }

    @Override
    public Class<SocketIOServer> getObjectType() {
        return SocketIOServer.class;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
}
