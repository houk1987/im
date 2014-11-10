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
    private static CommunicationConnection connection;  //ͨѶ��ϵ

    /**
     * ����ͨѶ������
     *
     * @return true ���ӳɹ� false ����ʧ��
     */
    public static synchronized boolean connectedCommunication() {
        try {
            getCommunicationConnection();  //��ȡ����
            if (!isConnectedCommunication()) {  //�ж��Ƿ��Ѿ����ӷ�����
                connection.connect(); //���ӷ�����
            }
            logger.info("ͨѶ���ӳɹ�");
            return true;
        } catch (XMPPException e) {
            e.printStackTrace();
            logger.info("ͨѶ����ʧ��");
        }
        return false;
    }

    /**
     * ��ȡͨѶ����
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
     * ����ͨѶ����
     */
    private static void createConnection() {
        logger.info("����ͨѶ����");
        connection = new CommunicationConnection();
    }

    /**
     * �ر�ͨѶ����
     */
    public static synchronized void closeCommunicationConnection() {
        if (isConnectedCommunication()) {
            connection.disconnect();
            connection = null;
        }
    }

    /**
     * �жϵ�ǰ�Ƿ�����ͨѶ����
     * @return true ���ӣ� false �Ͽ�
     */
    private static boolean isConnectedCommunication() {
        return connection != null && connection.isConnected();
    }

    /**
     * ��½ͨѶ������
     *
     * @param userName  �û���
     * @param password  ����
     * @return true ��½�ɹ� ,false ��½ʧ��
     */
    public static boolean loginCommunicationServer(String userName, String password) {
        if (isConnectedCommunication()) {
            try {
                connection.login(userName, password);
                Presence presence = new Presence(Presence.Type.available);  //���õ�½״̬
                connection.sendPacket(presence);
                return true;
            } catch (XMPPException e) {
                e.printStackTrace();
                logger.error("��½ʧ��");
            }
        } else {
            logger.error("δͨѶ���ӷ�����");
        }
        return false;
    }
}
