package com.qq.chat;

import com.comunication.chat.ChatManager;
import com.ui.tools.Tools;
import com.ui.window.PubFrame;
import org.jivesoftware.smack.packet.Message;

import javax.swing.*;
import java.awt.*;

/**
 * 会话窗口
 * Created by hq on 2014/11/1.
 */
public class ChatWindow extends PubFrame {

    private Message.Type ChatType;
    private ChatWindowContent chatWindowContent;
    private String jid;

    ChatWindow(String jid) throws HeadlessException {
        this.jid = jid;
        chatWindowContent = new ChatWindowContent(this);
        setContentPane(chatWindowContent);
        setSize(chatWindowContent.getSize());
        Tools.setWindowOnScreamCenter(this); //居中显示
        toFront();
        requestFocus();
    }

    public void loadAcceptMessage(Message message){
        String htmlContent = ChatManager.getContentHtml(message);
        chatWindowContent.chatDisplayPane.insertMessage(htmlContent);
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
    }

    public void setChatType(Message.Type chatType) {
        ChatType = chatType;
    }

    public Message.Type getChatType() {
        return ChatType;
    }

    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid;
    }
}
