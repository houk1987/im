package com.dt.chat.chat;

import com.comunication.chat.ChatMessageParser;
import com.comunication.chat.ChatOperate;
import com.comunication.chat.GroupChat;
import com.ui.chat.ChatDisplayPane;
import org.jivesoftware.smack.packet.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created by lenovo on 2014/11/7.
 */
public class GroupMessageAcceptWindow extends JDialog {

    private static HashMap<String, GroupMessageAcceptWindow> hashMap = new HashMap<>();
    private ChatDisplayPane chatDisplayPane;
    private JButton readButton;  //已读按钮
    private Message message;

    public static void showGroupMessageAcceeptWindow(Message message) {
        GroupMessageAcceptWindow groupMessageAcceptWindow = hashMap.get(message.getFrom().split("/")[0]);
        if (groupMessageAcceptWindow == null) {
            groupMessageAcceptWindow = new GroupMessageAcceptWindow(message);
            hashMap.put(message.getFrom().split("/")[0], groupMessageAcceptWindow);
        } else {
            groupMessageAcceptWindow.chatDisplayPane.insertMessage(ChatMessageParser.getContentHtml(message));
        }
        groupMessageAcceptWindow.setLocationRelativeTo(null);
        groupMessageAcceptWindow.setVisible(true);
    }

    private GroupMessageAcceptWindow(final Message message) {
        setLayout(new BorderLayout());
        setSize(700, 500);
        setTitle(String.valueOf(message.getProperty("groupName")));
        chatDisplayPane = new ChatDisplayPane();
        add(chatDisplayPane, BorderLayout.CENTER);
        JPanel buttonPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        readButton = new JButton("已读");
        buttonPane.add(readButton);
        readButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ChatOperate chatOperate = new GroupChat();
                    Properties properties = new Properties();
                    properties.setProperty("GroupMessageType","read");
                    chatOperate.sendChatMessage(message.getFrom().split("/")[0], "已读",properties);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        add(buttonPane, BorderLayout.SOUTH);
        chatDisplayPane.insertMessage(ChatMessageParser.getContentHtml(message));
    }
}
