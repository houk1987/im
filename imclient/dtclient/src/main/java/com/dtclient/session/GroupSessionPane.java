package com.dtclient.session;

import com.dtclient.main.tree.CustomTree;
import com.dtclient.vo.UserInfo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by lenovo on 2014/9/11.
 */
public class GroupSessionPane extends JPanel {
    private JSplitPane splitPane;
    SessionDisplayPane sessionDisplayPane;
    private SessionFrame sessionFrame;

    GroupSessionPane(SessionFrame sessionFrame) {
        this.sessionFrame = sessionFrame;
        setLayout(new BorderLayout());
        initComponent();
    }

    private void initComponent() {
        splitPane = new JSplitPane();
        splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);//���÷ָ��ߵķ�ʽΪ���ҷָ�
        splitPane.setDividerSize(1);//���÷ָ��߿�ȵĴ�С����pixelΪ���㵥λ��
        splitPane.setEnabled(false); //���÷ָ��߲����ƶ�
        splitPane.setResizeWeight(0.1);
        splitPane.setOpaque(false);
        splitPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        sessionDisplayPane = new SessionDisplayPane(sessionFrame);
        splitPane.setLeftComponent(sessionDisplayPane);
        splitPane.setRightComponent(groupMemberListPane());
        this.add(splitPane, BorderLayout.CENTER);
    }

    public JPanel groupMemberListPane() {
        JPanel panel = new JPanel(new BorderLayout());
        CustomTree tree = new CustomTree();
        tree.setRootNodeName("�������Ա");
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        jScrollPane.setViewportView(tree);
        panel.add(jScrollPane, BorderLayout.CENTER);
        java.util.List<UserInfo> userList = sessionFrame.friendRooms.getUserInfoList();
        tree.loadCustomTreeData(userList);
        return panel;
    }


    public JSplitPane getSplitPane() {
        return splitPane;
    }
}
