package com.comunication.connection;

import com.resource.ConfigurationRes;
import org.apache.log4j.Logger;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import sun.security.krb5.Config;

/**
 * Created by hq on 2014/11/8.
 */
public class CommunicationConnection extends XMPPConnection{
    private Logger logger = Logger.getLogger(CommunicationConnection.class);
    private final static String SERVER_HOST= ConfigurationRes.getHostName();
    private final static int SERVER_PORT=  ConfigurationRes.getPort();
    private final static String SERVER_NAME= ConfigurationRes.getDomain();
    private static ConnectionConfiguration config;

    CommunicationConnection() {
        super(initConnectionConfiguration());
    }

    private static ConnectionConfiguration initConnectionConfiguration(){
        config = new ConnectionConfiguration(SERVER_HOST, SERVER_PORT, SERVER_NAME);
      //  config.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);    //安全连接
        config.setSASLAuthenticationEnabled(false);
        return config;
    }

    public String getFullUserName() {
        return getUser().split("/")[0];
    }

    public String getUserName() {
        return getFullUserName().split("@")[0];
    }


}
