package org.smackservice;

import com.resource.ConfigurationRes;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.XMPPConnection;

/**
 * Created by HK on 2014/10/1.
 */
public class SmackConnection extends XMPPConnection {
    private static SmackConnection smackConnection;
    private final static String HOST_NAME = ConfigurationRes.getHostName();
    private final static int PORT = ConfigurationRes.getPort();
    private final static String DOMAIN = ConfigurationRes.getDomain();

    public static SmackConnection getInstance() {
        if(smackConnection == null){
            ConnectionConfiguration connectionConfiguration = new ConnectionConfiguration(HOST_NAME,PORT,DOMAIN);
            smackConnection = new SmackConnection(connectionConfiguration);
        }
        return smackConnection;
    }

    private SmackConnection(ConnectionConfiguration config) {
        super(config);
    }






}
