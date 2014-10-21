package org.smackservice;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

/**
 * Created by lenovo on 2014/10/21.
 */
public class ConnectionManager {

    public XMPPConnection createXmppConnection(String host,int port,String domain){
        ConnectionConfiguration connectionConfiguration = new ConnectionConfiguration(host,port,domain);
        return createXmppConnection(connectionConfiguration);
    }

    public XMPPConnection createXmppConnection(ConnectionConfiguration connectionConfiguration){
        connectionConfiguration.setSASLAuthenticationEnabled(false);  //πÿ±’ SASL —È÷§
        XMPPConnection xmppConnection = new XMPPConnection(connectionConfiguration);
        return xmppConnection;
    }

    public void openXMPPException(XMPPConnection xmppConnection) throws XMPPException {
        if(xmppConnection!=null && !xmppConnection.isConnected()){
            xmppConnection.connect();
        }
    }

    public void login(XMPPConnection xmppConnection,String userName,String password) throws XMPPException {
        if(xmppConnection!=null && xmppConnection.isConnected()){
            xmppConnection.login(userName,password);
        }
    }

    public void closeXMPPConnection(XMPPConnection xmppConnection){
        if(xmppConnection !=null && xmppConnection.isConnected()){
            xmppConnection.disconnect();
        }
    }
}
