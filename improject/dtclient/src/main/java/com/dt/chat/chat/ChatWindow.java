package com.dt.chat.chat;

import com.comunication.chat.*;
import org.jivesoftware.smack.packet.Message;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashMap;

/**
 * 会话窗口
 * Created by hq on 2014/11/1.
 */
public class ChatWindow extends JFrame {
    private ChatWindowContent chatWindowContent;
    private String jid;
    private GroupChatInfo groupChatInfo;
    private ChatOperate chatOperate;
    private static HashMap<String, ChatWindow> chatWindowHashMap = new HashMap<>();  //记录已经打开的聊天窗口

     ChatWindow(ChatOperate chatOperate,String jid) throws HeadlessException {
        this.setChatOperate(chatOperate);
        this.jid = jid;
        initChatWindow();
    }

    ChatWindow(ChatOperate chatOperate,GroupChatInfo groupChatInfo) throws HeadlessException {
        this.setChatOperate(chatOperate);
        this.setJid(groupChatInfo.getJid());
        this.groupChatInfo = groupChatInfo;
        initChatWindow();
    }

    private void initChatWindow(){
        chatWindowContent = new ChatWindowContent(this);
        setContentPane(chatWindowContent);
        setSize(700, 500);
        toFront(); //居中显示
        requestFocus();
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                chatWindowContent.horizontal_SplitPane.setDividerLocation(0.7);
            }
        });
    }

    public void loadAcceptMessage(Message message) {
        String htmlContent = ChatMessageParser.getContentHtml(message);
        chatWindowContent.chatDisplayPane.insertMessage(htmlContent);
    }

    public void setJid(String jid) {
        this.jid = jid;
    }

    public String getJid() {
        return jid;
    }

    public GroupChatInfo getGroupChatInfo() {
        return groupChatInfo;
    }

    public ChatOperate getChatOperate() {
        return chatOperate;
    }

    public void setChatOperate(ChatOperate chatOperate) {
        this.chatOperate = chatOperate;
    }
}
