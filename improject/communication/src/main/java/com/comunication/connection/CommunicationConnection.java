package com.comunication.connection;

import org.apache.log4j.Logger;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;

/**
 * Created by hq on 2014/11/8.
 */
public class CommunicationConnection extends XMPPConnection{
    private Logger logger = Logger.getLogger(CommunicationConnection.class);
    private final static String SERVER_HOST=  "192.168.1.111";
    private final static int SERVER_PORT=  5222;
    private final static String SERVER_NAME=  "30.com";
    private static ConnectionConfiguration config;

    CommunicationConnection() {
        super(initConnectionConfiguration());
    }

    private static ConnectionConfiguration initConnectionConfiguration(){
        config = new ConnectionConfiguration(SERVER_HOST, SERVER_PORT, SERVER_NAME);
        config.setSendPresence(false);                //����������
        config.setRosterLoadedAtLogin(false);         //��ȡ������
        config.setReconnectionAllowed(true);          //����
        config.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);    //��ȫ����
        config.setSASLAuthenticationEnabled(false);
        config.setCompressionEnabled(true);           //ѹ��
        return config;
    }

    public String getFullUserName() {
        return getUser().split("/")[0];
    }

    public String getUserName() {
        return getFullUserName().split("@")[0];
    }


}
