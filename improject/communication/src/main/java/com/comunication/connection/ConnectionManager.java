package com.comunication.connection;

import com.comunication.interceptor.MessageInterceptor;
import org.apache.log4j.Logger;
import org.jivesoftware.smack.*;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.Message;
import com.comunication.interceptor.MessageListener;
import org.jivesoftware.smack.packet.Presence;

/**
 * Created by lenovo on 2014/10/31.
 */
public class ConnectionManager {
    private final static Logger logger = Logger.getLogger(ConnectionManager.class);
    private static CommunicationConnection connection;
    private static PacketListener messageListener =  new MessageListener();
    private static PacketInterceptor messageInterceptor = new MessageInterceptor();
    private static ConnectionListerImpl connectionListener = new ConnectionListerImpl();


    public static CommunicationConnection getConnection() {
        if(connection == null){
            createConnection();
        }
        return connection;
    }

    public static void createConnection(){
        connection = new CommunicationConnection();
        connected();
    }



    public static synchronized void connected(){
        if(!isConnected()){
            try {
                //链接服务器
                logger.info("链接服务器");
                connection.connect();
                //添加消息拦截器
                logger.info("添加消息拦截器");
                connection.addPacketInterceptor(messageInterceptor,new PacketTypeFilter(Message.class));
                connection.addPacketListener(messageListener,new PacketTypeFilter(Message.class));
                //添加状态拦截器
//                connection
                connection.addConnectionListener(connectionListener);
                logger.info("服务器链接成功");
            } catch (XMPPException e) {
                e.printStackTrace();
                logger.error("服务器链接失败");
            }
        }
    }

    public static synchronized void closeConnection(){
        if(isConnected()){
            connection.disconnect();
            connection = null;
        }
    }

    public static boolean login(String userName,String password){
        if(isConnected()){
            try {
                connection.login(userName,password);
                Presence presence = new Presence(Presence.Type.available);  //设置登陆状态
                connection.sendPacket(presence);
                return true;
            } catch (XMPPException e) {
                e.printStackTrace();
                logger.error("登陆失败");
            }
        }else{
            logger.error("未链接服务器，登陆失败");
        }
        return false;
    }

    public static boolean isConnected(){
        return connection !=null && connection.isConnected();
    }

    public void setConnectionListerImplHandle(ConnectionListerImplHandle connectionListerImplHandle){
        connectionListener.setConnectionListerImplHandle(connectionListerImplHandle);
    }
}
