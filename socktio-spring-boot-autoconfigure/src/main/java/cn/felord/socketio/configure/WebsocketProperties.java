package cn.felord.socketio.configure;

import com.corundumstudio.socketio.AckMode;
import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.Transport;
import com.corundumstudio.socketio.protocol.JsonSupport;
import com.corundumstudio.socketio.store.MemoryStoreFactory;
import com.corundumstudio.socketio.store.StoreFactory;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @author felord.cn
 * @since 2020/9/11 22:29
 */
@Data
@ConfigurationProperties(prefix = "socketio")
public class WebsocketProperties {
    private String context = "/socket.io";

    private List<Transport> transports = Arrays.asList(Transport.WEBSOCKET, Transport.POLLING);

    private int bossThreads = 0; // 0 = current_processors_amount * 2
    private int workerThreads = 0; // 0 = current_processors_amount * 2
    private Boolean useLinuxNativeEpoll;
    private Boolean allowCustomRequests = false;

    private int upgradeTimeout = 10000;
    private int pingTimeout = 60000;
    private int pingInterval = 25000;
    private int firstDataTimeout = 5000;

    private int maxHttpContentLength = 65536;
    private int maxFramePayloadLength = 65536;

    private String packagePrefix;
    private String hostname;
    private int port = -1;

    private String sslProtocol = "TLSv1";

    private String keyStoreFormat = "JKS";
    private InputStream keyStore;
    private String keyStorePassword;

    private String trustStoreFormat = "JKS";
    private InputStream trustStore;
    private String trustStorePassword;

//    private String keyManagerFactoryAlgorithm = KeyManagerFactory.getDefaultAlgorithm();

    private Boolean preferDirectBuffer = true;

    private SocketConfig socketConfig = new SocketConfig();

    private StoreFactory storeFactory = new MemoryStoreFactory();

    private JsonSupport jsonSupport;

    private AuthorizationListener authorizationListener;

    private AckMode ackMode = AckMode.AUTO_SUCCESS_ONLY;

    private Boolean addVersionHeader = true;

    private String origin;

    private Boolean httpCompression = true;

    private Boolean websocketCompression = true;

    private Boolean randomSession = false;
}
