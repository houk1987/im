package com.dt.chat.chat;

import com.comunication.chat.*;
import com.comunication.connection.ConnectionManager;
import com.dt.group.GroupManager;
import com.dt.main.DtClientWindow;
import com.dt.main.tree.CustomTree;
import com.dt.main.tree.SynDataService;
import com.dt.start.StartClient;
import com.dt.vo.UserInfo;
import com.ui.chat.ChatDisplayPane;
import com.ui.chat.ChatWritePanel;
import com.ui.pane.ExtendSplitPane;
import org.apache.log4j.Logger;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Properties;

/**
 * �Ự�����������
 * Created by hq on 2014/11/1.
 */
class ChatWindowContent extends JPanel implements ActionListener {

    private Logger logger = Logger.getLogger(ChatWindowContent.class);
    JSplitPane horizontal_SplitPane = new ExtendSplitPane(JSplitPane.HORIZONTAL_SPLIT); //����ˮƽ�ָ����
    private JSplitPane vertical_SpiltPane = new ExtendSplitPane(JSplitPane.VERTICAL_SPLIT);//�������·ָ����
    ChatDisplayPane chatDisplayPane;
    private ChatWritePanel chatWritePanel;
    private JButton sendButton;
    private JButton closeButton;
    private ChatWindow chatWindow;

    public ChatWindowContent(ChatWindow chatWindow) {
        this.chatWindow = chatWindow;
        setLayout(new BorderLayout());
        initChatPane();
    }

    /**
     * ��ʼ���Ự���
     */
    private void initChatPane() {
        chatDisplayPane = new ChatDisplayPane();
        chatDisplayPane.setTextPaneBgColor(Color.WHITE);
        chatWritePanel = new ChatWritePanel(this);
        chatWritePanel.setTextPaneBgColor(Color.WHITE);
        vertical_SpiltPane.add(chatDisplayPane, JSplitPane.TOP);
        JPanel bottomPane = new JPanel(new BorderLayout());
        bottomPane.setBackground(Color.WHITE);
        bottomPane.add(chatWritePanel,BorderLayout.CENTER);
        sendButton = createButton(sendButton, "����");
        closeButton = createButton(closeButton, "�ر�");
        JPanel buttonPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPane.setOpaque(false);
        buttonPane.add(sendButton);
        buttonPane.add(closeButton);
        bottomPane.add(buttonPane,BorderLayout.SOUTH);
        vertical_SpiltPane.add(bottomPane, JSplitPane.BOTTOM); //���ڷָ�����·�
        horizontal_SplitPane.setLeftComponent(vertical_SpiltPane);
        if (chatWindow.getChatOperate() instanceof SingleChat) {
            horizontal_SplitPane.setRightComponent(getUserInfoPane());
        } else {
            horizontal_SplitPane.setRightComponent(getGroupMemberListPane());
        }
        add(horizontal_SplitPane,BorderLayout.CENTER);
    }

    /**
     * @return ��Ա��Ϣ���
     */
    private JPanel getUserInfoPane() {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.white);
//        UserInfo user = SynDataService.getInstance().getUserInfo(chatWindow.getJid());
//        panel.add(createJLabel("��Ա��ϸ��Ϣ:", 10, 0, 100, 30));
//        panel.add(createJLabel("���:" + StringTools.nullConverToString(user.getId()), 10, 30, 100, 30));
//        panel.add(createJLabel("����:" + StringTools.nullConverToString(user.getUsername()), 10, 60, 100, 30));
//        panel.add(createJLabel("����:" + StringTools.nullConverToString(user.getAge()), 10, 90, 100, 30));
//        panel.add(createJLabel("��������:" + StringTools.nullConverToString(user.getBirthday()), 10, 120, 100, 30));
//        panel.add(createJLabel("����:" + StringTools.nullConverToString(String.valueOf(user.getRank())), 10, 150, 100, 30));
//        panel.add(createJLabel("ְλ:" + StringTools.nullConverToString(user.getWorkPosition()), 10, 180, 100, 30));
//        panel.add(createJLabel("�ֻ�����:" + StringTools.nullConverToString(user.getCellPhone()), 10, 210, 100, 30));
//        panel.add(createJLabel("�칫����:" + StringTools.nullConverToString(user.getWorkPhone()), 10, 240, 100, 30));
//        panel.add(createJLabel("��ɫ:" + StringTools.nullConverToString(user.getRoleName()), 10, 270, 100, 30));
        return panel;
    }

    /**
     * @return Ⱥ���Ա��Ϣ���
     */
    private JPanel getGroupMemberListPane() {
        JPanel panel = new JPanel(new BorderLayout());
        CustomTree tree = new CustomTree();
        tree.setRootNodeName("�������Ա");
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        jScrollPane.setViewportView(tree);
        panel.add(jScrollPane, BorderLayout.CENTER);
        List<UserInfo> userList = GroupManager.getUserInfo(chatWindow.getJid());
        if (userList != null && userList.size() > 0) {
            for (UserInfo userInfo : userList) {
                DefaultMutableTreeNode userNode = new DefaultMutableTreeNode(userInfo);
                tree.addNode(userNode);
            }
        }
        return panel;
    }

    private static JLabel createJLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        return label;
    }


    private JButton createButton(JButton button, String txt) {
        button = new JButton(txt);
        button.addActionListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(sendButton)) {
            try {
                ChatOperate chatOperate = chatWindow.getChatOperate();  //��ȡ���͵Ĳ�������(���˻Ự��Ⱥ��Ự)����
                Properties properties = new Properties();
                if(chatOperate instanceof  SingleChat){

                }else if(chatOperate instanceof GroupChat){
                    properties.setProperty("groupName",chatWindow.getGroupChatInfo().getName());
                }


                Message message = chatOperate.sendChatMessage(chatWindow.getJid(), chatWritePanel.getPlainText(),properties); //������Ϣ
                chatWritePanel.clear(); //�����Ϣ��
                if(message.getFrom() == null)message.setFrom(ConnectionManager.getConnection().getUserName());
                chatDisplayPane.insertMessage(ChatMessageParser.getContentHtml(message)); //��ʾ��Ϣ
            } catch (Exception e1) {
                if(e1 instanceof ChatMessageIsNullException){
                    JOptionPane.showMessageDialog(chatWindow, "��Ϣ����Ϊ�գ����ܷ���!");
                }
                e1.printStackTrace();
            }
        } else if (e.getSource().equals(closeButton)) {
            chatWindow.dispose();
        }
    }
}
