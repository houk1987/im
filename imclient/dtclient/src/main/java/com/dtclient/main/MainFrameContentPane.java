package com.dtclient.main;

import com.dtclient.lanuch.DtClient;
import com.dtclient.main.tree.CustomTree;
import com.dtclient.main.tree.OrgTree;

import javax.swing.*;
import java.awt.*;

/**
 * Created by HK on 2014/9/9.
 */
public class MainFrameContentPane extends JPanel {
    private JScrollPane jScrollPane;  //�������
     OrgTree orgTree;  //��֯������
     CustomTree customTree; //�Զ�����

    /**
     *
     */
    public MainFrameContentPane() {
        setOpaque(false);
        setLayout(new BorderLayout());
        add(new AccountInfoPane(), BorderLayout.NORTH);
        JPanel centerPane = new JPanel(new BorderLayout());
        int role = DtClient.getInstance().getUserInfo().getRoleId();
        DtNavigationBar dtNavigationBar = new DtNavigationBar(this,role);
        centerPane.add(dtNavigationBar, BorderLayout.NORTH);
        this.jScrollPane = new JScrollPane();
        this.jScrollPane.setBorder(null);
        centerPane.add(jScrollPane, BorderLayout.CENTER);
        add(centerPane, BorderLayout.CENTER);
        switch (role) {
            case 1:  //ѵ��������Ա
                customTree = new CustomTree();
                customTree.loadDefaultGroup();
                changeView(customTree);
                break;
            case 2: //��ѵ��Ա
                customTree = new CustomTree();
                customTree.loadDtUserInfo();
                changeView(customTree);
                break;
            case 3:  //������Ա
                customTree = new CustomTree();
                customTree.loadDefaultGroup();
                orgTree = new OrgTree();
                orgTree.loadData();
                changeView(customTree);
                break;
            case 4: //������Ա
                customTree = new CustomTree();
                customTree.loadDefaultGroup();
                changeView(customTree);
                break;
        }
    }

    public void changeView(JComponent jComponent) {
        jScrollPane.setViewportView(jComponent);
    }

    public CustomTree getCustomTree() {
        return customTree;
    }
}
