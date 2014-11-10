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
    private static CommunicationConnection connection;  //通讯联系

    /**
     * 链接通讯服务器
     *
     * @return true 链接成功 false 链接失败
     */
    public static synchronized boolean connectedCommunication() {
        try {
            getCommunicationConnection();  //获取链接
            if (!isConnectedCommunication()) {  //判断是否已经链接服务器
                connection.connect(); //链接服务器
            }
            logger.info("通讯链接成功");
            return true;
        } catch (XMPPException e) {
            e.printStackTrace();
            logger.info("通讯链接失败");
        }
        return false;
    }

    /**
     * 获取通讯链接
     *
     * @return
     * @throws XMPPException
     */
    public static CommunicationConnection getCommunicationConnection() {
        if (connection == null) {
            createConnection();
        }
        return connection;
    }

    /**
     * 创建通讯链接
     */
    private static void createConnection() {
        logger.info("创建通讯链接");
        connection = new CommunicationConnection();
    }

    /**
     * 关闭通讯链接
     */
    public static synchronized void closeCommunicationConnection() {
        if (isConnectedCommunication()) {
            connection.disconnect();
            connection = null;
        }
    }

    /**
     * 判断当前是否链接通讯服务
     * @return true 链接， false 断开
     */
    private static boolean isConnectedCommunication() {
        return connection != null && connection.isConnected();
    }

    /**
     * 登陆通讯服务器
     *
     * @param userName  用户名
     * @param password  密码
     * @return true 登陆成功 ,false 登陆失败
     */
    public static boolean loginCommunicationServer(String userName, String password) {
        if (isConnectedCommunication()) {
            try {
                connection.login(userName, password);
                Presence presence = new Presence(Presence.Type.available);  //设置登陆状态
                connection.sendPacket(presence);
                return true;
            } catch (XMPPException e) {
                e.printStackTrace();
                logger.error("登陆失败");
            }
        } else {
            logger.error("未通讯链接服务器");
        }
        return false;
    }
}
