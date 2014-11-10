package com.qq.chat;

import org.apache.log4j.Logger;
import org.jivesoftware.smack.packet.Message;

import java.util.HashMap;

/**
 * �Ự���ڹ�����
 * Created by hq on 2014/11/1.
 */
public class ChatWindowManager {

    private static HashMap<String, ChatWindow> chatWindowHashMap = new HashMap<>();  //��¼�Ѿ��򿪵����촰��

    private static Logger logger = Logger.getLogger(ChatWindowManager.class);

    /**
     * �����˻Ự����
     * @param jid
     */
    public static void openChatWindow(String jid) {
        logger.info("�������˻Ự");
        ChatWindow chatWindow = getChatWindow(jid);
        chatWindow.setChatType(Message.Type.chat);
        chatWindow.setVisible(true);
    }

    /**
     * ��ȡ�Ự����
     * @param jid
     * @return
     */
    public static ChatWindow getChatWindow(String jid) {
        if (jid == null) return null;
        ChatWindow chatWindow = chatWindowHashMap.get(jid);
        if (chatWindow == null) {
            chatWindow = new ChatWindow(jid);
            chatWindowHashMap.put(jid, chatWindow);
        }
        return chatWindow;
    }
}
