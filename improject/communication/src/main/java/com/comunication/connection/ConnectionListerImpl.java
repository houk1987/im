package com.comunication.connection;

import org.jivesoftware.smack.ConnectionListener;


/**
 * Created by lenovo on 2014/10/31.
 */
public class ConnectionListerImpl implements ConnectionListener {

    private static ConnectionListerImplHandle connectionListerImplHandle;

    @Override
    public void connectionClosed() {

    }

    @Override
    public void connectionClosedOnError(Exception e) {
        if(e.getMessage().contains("conflict")){
            if(connectionListerImplHandle!=null){
                connectionListerImplHandle.repeatConnectionHandle();
            }
        }
    }

    @Override
    public void reconnectingIn(int i) {

    }

    @Override
    public void reconnectionSuccessful() {

    }

    @Override
    public void reconnectionFailed(Exception e) {

    }

    public static void setConnectionListerImplHandle(ConnectionListerImplHandle handle) {
        connectionListerImplHandle = handle;
    }
}
