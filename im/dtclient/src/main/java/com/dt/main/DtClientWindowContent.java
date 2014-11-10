package com.dt.main;

import com.dt.main.tree.CustomTree;
import com.dt.main.tree.OrgTree;
import com.ui.pane.ExtendPane;
import com.ui.rosterTree.PubTree;

import javax.swing.*;
import java.awt.*;

/**
 * Created by lenovo on 2014/11/3.
 */
public class DtClientWindowContent extends ExtendPane {
    private AccountInfoPane accountInfoPane;
    private JScrollPane jScrollPane;  //滚动面板
    private OrgTree orgTree;
    private CustomTree customTree;
    public DtClientWindowContent() {
        super(new BorderLayout(), null);
        this.setSize(350,700);
        addAccountInfoPane();
        addJScrollPane();
        addOrgTree();
        customTree = new CustomTree();
        customTree.loadCustomTreeData();
    }

    private void addAccountInfoPane(){
        accountInfoPane = new AccountInfoPane(this);
        add(accountInfoPane,BorderLayout.NORTH);
    }

    /**
     * 添加滚动条面板
     */
    private void addJScrollPane(){
        this.jScrollPane = new JScrollPane();
        this.jScrollPane.setBorder(null);
        jScrollPane.setBounds(5,82,325,this.getHeight()-125);
        add(jScrollPane,BorderLayout.CENTER);
    }

    /**
     * 添加组织机构树
     */
    private void addOrgTree(){
        orgTree = new OrgTree();
        orgTree.loadData();
        jScrollPane.setViewportView(orgTree);
    }

    public void setTreeView(PubTree pubTree){
        jScrollPane.setViewportView(pubTree);
    }

    public OrgTree getOrgTree() {
        return orgTree;
    }

    public CustomTree getCustomTree() {
        return customTree;
    }


}
