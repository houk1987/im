package com.qq.chat;

import org.apache.log4j.Logger;
import org.jivesoftware.smack.packet.Message;

import java.util.HashMap;

/**
 * 会话窗口管理类
 * Created by hq on 2014/11/1.
 */
public class ChatWindowManager {

    private static HashMap<String, ChatWindow> chatWindowHashMap = new HashMap<>();  //记录已经打开的聊天窗口

    private static Logger logger = Logger.getLogger(ChatWindowManager.class);

    /**
     * 打开两人会话窗口
     * @param jid
     */
    public static void openChatWindow(String jid) {
        logger.info("开启两人会话");
        ChatWindow chatWindow = getChatWindow(jid);
        chatWindow.setChatType(Message.Type.chat);
        chatWindow.setVisible(true);
    }

    /**
     * 获取会话窗口
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
