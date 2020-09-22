package cn.felord.socketio.configure;

import cn.felord.socketio.server.ConfigurableSocketIOServerFactory;
import cn.felord.socketio.server.SocketIOServerLifecycle;
import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import com.corundumstudio.socketio.handler.SuccessAuthorizationListener;
import com.corundumstudio.socketio.store.MemoryStoreFactory;
import com.corundumstudio.socketio.store.StoreFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.context.SmartLifecycle;
import org.springframework.context.annotation.Bean;

/**
 * The type Socket io configuration.
 *
 * @author felord.cn
 * @since 2020 /9/11 22:55
 */
@Slf4j
@org.springframework.context.annotation.Configuration
@EnableConfigurationProperties({WebsocketProperties.class})
public class SocketIOConfiguration {

    /**
     * Authorization listener authorization listener.
     *
     * @return the authorization listener
     */
    @ConditionalOnMissingBean(AuthorizationListener.class)
    @Bean
    public AuthorizationListener authorizationListener() {
        return new SuccessAuthorizationListener();
    }

    /**
     * Store factory store factory.
     *
     * @return the store factory
     */
    @ConditionalOnMissingBean(StoreFactory.class)
    @Bean
    public StoreFactory storeFactory() {
        return new MemoryStoreFactory();
    }


    /**
     * Init socket io configuration configuration.
     *
     * @param websocketProperties   the websocket properties
     * @param authorizationListener the authorization listener
     * @param storeFactory          the store factory
     * @return the configuration
     */
    @Bean
    @Qualifier("socketIOConfiguration")
    public Configuration initSocketIOConfiguration(WebsocketProperties websocketProperties, AuthorizationListener authorizationListener, StoreFactory storeFactory) {

        Configuration configuration = new Configuration();

        PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
        propertyMapper.from(websocketProperties.getContext()).to(configuration::setContext);
        propertyMapper.from(websocketProperties.getBossThreads()).to(configuration::setBossThreads);
        propertyMapper.from(websocketProperties.getWorkerThreads()).to(configuration::setWorkerThreads);
        propertyMapper.from(websocketProperties.getUseLinuxNativeEpoll()).to(configuration::setUseLinuxNativeEpoll);
        propertyMapper.from(websocketProperties.getAllowCustomRequests()).to(configuration::setAllowCustomRequests);
        propertyMapper.from(websocketProperties.getUpgradeTimeout()).to(configuration::setUpgradeTimeout);
        propertyMapper.from(websocketProperties.getPingTimeout()).to(configuration::setPingTimeout);
        propertyMapper.from(websocketProperties.getPingInterval()).to(configuration::setPingInterval);
        propertyMapper.from(websocketProperties.getFirstDataTimeout()).to(configuration::setFirstDataTimeout);
        propertyMapper.from(websocketProperties.getMaxHttpContentLength()).to(configuration::setMaxHttpContentLength);
        propertyMapper.from(websocketProperties.getMaxFramePayloadLength()).to(configuration::setMaxFramePayloadLength);
        propertyMapper.from(websocketProperties.getPackagePrefix()).to(configuration::setPackagePrefix);
        propertyMapper.from(websocketProperties.getHostname()).to(configuration::setHostname);
        propertyMapper.from(websocketProperties.getPort()).to(configuration::setPort);
        propertyMapper.from(websocketProperties.getSslProtocol()).to(configuration::setSSLProtocol);
//        propertyMapper.from(websocketProperties.getKeyStoreFormat()).to(configuration::setKeyStoreFormat);
//        propertyMapper.from(websocketProperties.getKeyStore()).to(configuration::setKeyStore);

        propertyMapper.from(websocketProperties.getKeyStorePassword()).to(configuration::setKeyStorePassword);
        propertyMapper.from(websocketProperties.getTrustStoreFormat()).to(configuration::setTrustStoreFormat);
//        propertyMapper.from(websocketProperties.getTrustStore()).to(configuration::setTrustStore);
        propertyMapper.from(websocketProperties.getPreferDirectBuffer()).to(configuration::setPreferDirectBuffer);
//        propertyMapper.from(websocketProperties.getSocketConfig()).to(configuration::setSocketConfig);
//        propertyMapper.from(websocketProperties.getStoreFactory()).to(configuration::setStoreFactory);
        propertyMapper.from(websocketProperties.getJsonSupport()).to(configuration::setJsonSupport);
        propertyMapper.from(websocketProperties.getAckMode()).to(configuration::setAckMode);
        propertyMapper.from(websocketProperties.getAddVersionHeader()).to(configuration::setAddVersionHeader);
        propertyMapper.from(websocketProperties.getOrigin()).to(configuration::setOrigin);
        propertyMapper.from(websocketProperties.getHttpCompression()).to(configuration::setHttpCompression);
        propertyMapper.from(websocketProperties.getWebsocketCompression()).to(configuration::setWebsocketCompression);
        propertyMapper.from(websocketProperties.getRandomSession()).to(configuration::setRandomSession);

        configuration.setAuthorizationListener(authorizationListener);
        configuration.setStoreFactory(storeFactory);

        return configuration;
    }


    /**
     * Socket io server configurable socket io server factory.
     *
     * @param configuration the configuration
     * @return the configurable socket io server factory
     */
    @Bean
    public ConfigurableSocketIOServerFactory socketIOServer(Configuration configuration) {
        ConfigurableSocketIOServerFactory configurableSocketIOServerFactory = new ConfigurableSocketIOServerFactory();

        configurableSocketIOServerFactory.setConfiguration(configuration);
        return configurableSocketIOServerFactory;
    }

    /**
     * Socket io server lifecycle smart lifecycle.
     *
     * @param socketIOServer the socket io server
     * @return the smart lifecycle
     */
    @Bean
    public SmartLifecycle socketIOServerLifecycle(SocketIOServer socketIOServer) {
        return new SocketIOServerLifecycle(socketIOServer);
    }

    /**
     * Spring annotation scanner spring annotation scanner.
     *
     * @param socketIOServer the socket io server
     * @return the spring annotation scanner
     */
    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketIOServer) {
        return new SpringAnnotationScanner(socketIOServer);
    }
}
