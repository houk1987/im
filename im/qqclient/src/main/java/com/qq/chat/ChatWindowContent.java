package com.qq.chat;

import com.comunication.chat.ChatManager;
import com.comunication.roster.RosterManager;
import com.qq.button.Button;
import com.qq.button.SessionFrameButtonFactory;
import com.qq.friends.FriendsManager;
import com.qq.main.tree.QQContactItem;
import com.ui.chat.ChatDisplayPane;
import com.ui.chat.ChatWritePanel;
import com.ui.headPicture.HeadPicture;
import com.ui.image.ImageUtils;
import com.ui.jlabel.JLabelFactory;
import com.ui.pane.ExtendPane;
import org.apache.log4j.Logger;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 会话窗口内容面板
 * Created by hq on 2014/11/1.
 */
class ChatWindowContent extends ExtendPane implements ActionListener{

    private Logger logger = Logger.getLogger(ChatWindowContent.class);
    private JLabel userNameLabel;
    private HeadPicture animation;
    ChatDisplayPane chatDisplayPane;
    private ChatWritePanel chatWritePanel;
    private JButton sendButton;
    private JButton closeButton;
    private JButton closeWindowButton; //关闭窗口的按钮
    private JButton minWindowButton; //最小化窗口按钮
    private ChatWindow chatWindow;
    private com.qq.button.Button button;
    private String jid;
    public ChatWindowContent(ChatWindow chatWindow) {
        super(null, ImageUtils.getInstance("resources/images/session/").getImageIcon("sessionFrameBg.png"));
        this.chatWindow = chatWindow;
        this.jid = chatWindow.getJid();
        button = new Button(new SessionFrameButtonFactory());
        addCloseWindowButton();
        addMinWindowButton();
        addCloseSessionButton();
        addSendMessageButton();
        //添加中间会话消息显示，显示输入的面板
        this.addChatDisplayPane();
        this.addChatWriterPane();

        addUserHeadIconLabel();  //添加右上角的头像图片
        addUserNameLabel();
    }

    /**
     * 添加窗口关闭按钮
     */
    private void addCloseWindowButton() {
        closeWindowButton = button.createButton(SessionFrameButtonFactory.CLOSE_WINDOW);
        closeWindowButton.setLocation(this.getWidth() - closeWindowButton.getWidth(), 0);
        addButton(closeWindowButton);
    }

    /**
     * 添加窗口最小化按钮
     */
    private void addMinWindowButton() {
        minWindowButton = button.createButton(SessionFrameButtonFactory.MIN_WINDOW);
        minWindowButton.setLocation(this.getWidth() - closeWindowButton.getWidth() - minWindowButton.getWidth() - minWindowButton.getWidth(), 0);
        addButton(minWindowButton);
    }

    /**
     * 添加发送按钮
     */
    private void addSendMessageButton() {
        sendButton = button.createButton(SessionFrameButtonFactory.SEND);
        sendButton.setLocation(348, 474);
        addButton(sendButton);
    }

    /**
     * 添加关闭按钮
     */
    private void addCloseSessionButton() {
        closeButton = button.createButton(SessionFrameButtonFactory.ClOSE_SESSION_FRAME);
        closeButton.setLocation(274, 474);
        addButton(closeButton);
    }

    private void addChatDisplayPane() {
        chatDisplayPane = new ChatDisplayPane();
        chatDisplayPane.setBounds(1, 81, 443, 287);
        add(chatDisplayPane);
    }

    private void addChatWriterPane() {
        chatWritePanel = new ChatWritePanel(this);
        chatWritePanel.setBounds(3, 400, 443, 70);
        add(chatWritePanel);
    }

    /**
     * 添加到面板
     * 并且添加监听
     *
     * @param jButton
     */
    private void addButton(JButton jButton) {
        add(jButton);
        jButton.addActionListener(this);  //添加事件监听
    }

    /**
     * 添加用户头像席状态
     */
    private void addUserHeadIconLabel() {
        animation=new HeadPicture("resources/images/common/headItem1.png");
        animation.setLocation(6, 1);
        add(animation);
    }

    /**
     * 添加用户姓名标签
     */
    private void addUserNameLabel() {
        final Font font = new Font("微软雅黑",Font.PLAIN,17);
        FriendsManager friendsManager = FriendsManager.getInstance();
        QQContactItem qqContactItem = friendsManager.getFriend(jid);
        userNameLabel = JLabelFactory.createJLabel(qqContactItem.getUserName(), font, Color.BLACK);
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBounds(55, 3, 300, 21);
        panel.setBackground(new Color(234, 236, 248));
        panel.add(userNameLabel,BorderLayout.CENTER);
        add(panel);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
         if(e.getSource().equals(sendButton)){
             try {
             if(chatWindow.getChatType().equals(Message.Type.chat)) {    //发送两人会话消息
                 logger.info("发送两人会话消息");
                 Chat chat = ChatManager.createChat(jid); //创建两人会话
                 chat.sendMessage(ChatManager.getMessage(chatWritePanel.getPlainText(),jid)); //发送消息
                 logger.info("发送两人会话消息成功");
                 chatWritePanel.clear();//清空输入文本
             }
             } catch (XMPPException e1) {
                 e1.printStackTrace();
             }
         }else if(e.getSource().equals(closeButton) || e.getSource().equals(closeWindowButton)){
             chatWindow.dispose();
             chatWritePanel.clear();//清空输入文本
         }else if(e.getSource().equals(minWindowButton)){
             chatWindow.setExtendedState(JFrame.ICONIFIED);
         }
    }
}
