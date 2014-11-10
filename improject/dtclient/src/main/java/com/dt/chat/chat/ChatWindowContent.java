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
 * 会话窗口内容面板
 * Created by hq on 2014/11/1.
 */
class ChatWindowContent extends JPanel implements ActionListener {

    private Logger logger = Logger.getLogger(ChatWindowContent.class);
    JSplitPane horizontal_SplitPane = new ExtendSplitPane(JSplitPane.HORIZONTAL_SPLIT); //设置水平分割面板
    private JSplitPane vertical_SpiltPane = new ExtendSplitPane(JSplitPane.VERTICAL_SPLIT);//设置上下分割面板
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
     * 初始化会话面板
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
        sendButton = createButton(sendButton, "发送");
        closeButton = createButton(closeButton, "关闭");
        JPanel buttonPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPane.setOpaque(false);
        buttonPane.add(sendButton);
        buttonPane.add(closeButton);
        bottomPane.add(buttonPane,BorderLayout.SOUTH);
        vertical_SpiltPane.add(bottomPane, JSplitPane.BOTTOM); //放在分割面板下方
        horizontal_SplitPane.setLeftComponent(vertical_SpiltPane);
        if (chatWindow.getChatOperate() instanceof SingleChat) {
            horizontal_SplitPane.setRightComponent(getUserInfoPane());
        } else {
            horizontal_SplitPane.setRightComponent(getGroupMemberListPane());
        }
        add(horizontal_SplitPane,BorderLayout.CENTER);
    }

    /**
     * @return 人员信息面板
     */
    private JPanel getUserInfoPane() {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.white);
//        UserInfo user = SynDataService.getInstance().getUserInfo(chatWindow.getJid());
//        panel.add(createJLabel("人员详细信息:", 10, 0, 100, 30));
//        panel.add(createJLabel("编号:" + StringTools.nullConverToString(user.getId()), 10, 30, 100, 30));
//        panel.add(createJLabel("姓名:" + StringTools.nullConverToString(user.getUsername()), 10, 60, 100, 30));
//        panel.add(createJLabel("年龄:" + StringTools.nullConverToString(user.getAge()), 10, 90, 100, 30));
//        panel.add(createJLabel("出生年月:" + StringTools.nullConverToString(user.getBirthday()), 10, 120, 100, 30));
//        panel.add(createJLabel("军衔:" + StringTools.nullConverToString(String.valueOf(user.getRank())), 10, 150, 100, 30));
//        panel.add(createJLabel("职位:" + StringTools.nullConverToString(user.getWorkPosition()), 10, 180, 100, 30));
//        panel.add(createJLabel("手机号码:" + StringTools.nullConverToString(user.getCellPhone()), 10, 210, 100, 30));
//        panel.add(createJLabel("办公号码:" + StringTools.nullConverToString(user.getWorkPhone()), 10, 240, 100, 30));
//        panel.add(createJLabel("角色:" + StringTools.nullConverToString(user.getRoleName()), 10, 270, 100, 30));
        return panel;
    }

    /**
     * @return 群组成员信息面板
     */
    private JPanel getGroupMemberListPane() {
        JPanel panel = new JPanel(new BorderLayout());
        CustomTree tree = new CustomTree();
        tree.setRootNodeName("参与的人员");
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
                ChatOperate chatOperate = chatWindow.getChatOperate();  //获取发送的操作类型(两人会话，群组会话)操作
                Properties properties = new Properties();
                if(chatOperate instanceof  SingleChat){

                }else if(chatOperate instanceof GroupChat){
                    properties.setProperty("groupName",chatWindow.getGroupChatInfo().getName());
                }


                Message message = chatOperate.sendChatMessage(chatWindow.getJid(), chatWritePanel.getPlainText(),properties); //发送消息
                chatWritePanel.clear(); //清除消息框
                if(message.getFrom() == null)message.setFrom(ConnectionManager.getConnection().getUserName());
                chatDisplayPane.insertMessage(ChatMessageParser.getContentHtml(message)); //显示消息
            } catch (Exception e1) {
                if(e1 instanceof ChatMessageIsNullException){
                    JOptionPane.showMessageDialog(chatWindow, "消息内容为空，不能发送!");
                }
                e1.printStackTrace();
            }
        } else if (e.getSource().equals(closeButton)) {
            chatWindow.dispose();
        }
    }
}
