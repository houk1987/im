package com.dt.chat.chat;

import com.comunication.chat.GroupChat;
import com.comunication.chat.GroupChatInfo;
import com.comunication.chat.SingleChat;
import com.dt.chat.*;
import org.apache.log4j.Logger;
import org.jivesoftware.smack.packet.Message;

import java.util.HashMap;

/**
 * �Ự���ڹ�����
 * Created by hq on 2014/11/1.
 */
public class ChatWindowManager {

    private static HashMap<String, ChatWindow> chatWindowHashMap = new HashMap<>();  //��¼�Ѿ��򿪵����촰��

    /**
     * �����˻Ự����
     *
     * @param jid
     */
    public static void openSingleChatWindow(String jid) {
        ChatWindow chatWindow = chatWindowHashMap.get(jid);
        if(chatWindow == null){
            chatWindow = new ChatWindow(new SingleChat(),jid);
            chatWindowHashMap.put(jid, chatWindow);
        }
        chatWindow.setVisible(true);
    }

    /**
     * ��Ⱥ��Ự����
     *
     * @param groupChatInfo
     */
    public static ChatWindow openGroupChatWindow(GroupChatInfo groupChatInfo) {
        ChatWindow chatWindow = chatWindowHashMap.get(groupChatInfo.getJid());
        if(chatWindow == null){
            chatWindow = new ChatWindow(new GroupChat(),groupChatInfo);
            chatWindowHashMap.put(groupChatInfo.getJid(), chatWindow);
        }
        chatWindow.setVisible(true);
        return chatWindow;
    }




}
