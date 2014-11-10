package com.comunication.connection;

import org.jivesoftware.smack.ConnectionListener;


/**
 * Created by lenovo on 2014/10/31.
 */
public class ConnectionListerImpl implements ConnectionListener {

    private ConnectionListerImplHandle connectionListerImplHandle;

    @Override
    public void connectionClosed() {

    }

    @Override
    public void connectionClosedOnError(Exception e) {
        if(e.getMessage().contains("conflict")){
            System.out.println("openfire÷ÿ∏¥µ«¬º¡À :"+e);
            if(connectionListerImplHandle!=null){

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

    public void setConnectionListerImplHandle(ConnectionListerImplHandle connectionListerImplHandle) {
        this.connectionListerImplHandle = connectionListerImplHandle;
    }
}
