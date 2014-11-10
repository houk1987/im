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
 * �Ự�����������
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
    private JButton closeWindowButton; //�رմ��ڵİ�ť
    private JButton minWindowButton; //��С�����ڰ�ť
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
        //����м�Ự��Ϣ��ʾ����ʾ��������
        this.addChatDisplayPane();
        this.addChatWriterPane();

        addUserHeadIconLabel();  //������Ͻǵ�ͷ��ͼƬ
        addUserNameLabel();
    }

    /**
     * ��Ӵ��ڹرհ�ť
     */
    private void addCloseWindowButton() {
        closeWindowButton = button.createButton(SessionFrameButtonFactory.CLOSE_WINDOW);
        closeWindowButton.setLocation(this.getWidth() - closeWindowButton.getWidth(), 0);
        addButton(closeWindowButton);
    }

    /**
     * ��Ӵ�����С����ť
     */
    private void addMinWindowButton() {
        minWindowButton = button.createButton(SessionFrameButtonFactory.MIN_WINDOW);
        minWindowButton.setLocation(this.getWidth() - closeWindowButton.getWidth() - minWindowButton.getWidth() - minWindowButton.getWidth(), 0);
        addButton(minWindowButton);
    }

    /**
     * ��ӷ��Ͱ�ť
     */
    private void addSendMessageButton() {
        sendButton = button.createButton(SessionFrameButtonFactory.SEND);
        sendButton.setLocation(348, 474);
        addButton(sendButton);
    }

    /**
     * ��ӹرհ�ť
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
     * ��ӵ����
     * ������Ӽ���
     *
     * @param jButton
     */
    private void addButton(JButton jButton) {
        add(jButton);
        jButton.addActionListener(this);  //����¼�����
    }

    /**
     * ����û�ͷ��ϯ״̬
     */
    private void addUserHeadIconLabel() {
        animation=new HeadPicture("resources/images/common/headItem1.png");
        animation.setLocation(6, 1);
        add(animation);
    }

    /**
     * ����û�������ǩ
     */
    private void addUserNameLabel() {
        final Font font = new Font("΢���ź�",Font.PLAIN,17);
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
             if(chatWindow.getChatType().equals(Message.Type.chat)) {    //�������˻Ự��Ϣ
                 logger.info("�������˻Ự��Ϣ");
                 Chat chat = ChatManager.createChat(jid); //�������˻Ự
                 chat.sendMessage(ChatManager.getMessage(chatWritePanel.getPlainText(),jid)); //������Ϣ
                 logger.info("�������˻Ự��Ϣ�ɹ�");
                 chatWritePanel.clear();//��������ı�
             }
             } catch (XMPPException e1) {
                 e1.printStackTrace();
             }
         }else if(e.getSource().equals(closeButton) || e.getSource().equals(closeWindowButton)){
             chatWindow.dispose();
             chatWritePanel.clear();//��������ı�
         }else if(e.getSource().equals(minWindowButton)){
             chatWindow.setExtendedState(JFrame.ICONIFIED);
         }
    }
}
