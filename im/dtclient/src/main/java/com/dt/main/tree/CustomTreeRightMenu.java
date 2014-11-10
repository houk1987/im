package com.dt.main.tree;



import com.comunication.chat.GroupChatInfo;
import com.dt.group.GroupManager;
import com.dt.main.DtClientWindow;
import org.jivesoftware.smack.XMPPException;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 自定义树的右键菜单
 * Created by a on 2014/7/10.
 */
public class CustomTreeRightMenu extends JPopupMenu implements ActionListener{

    private JMenuItem createCustomGroupItem;
    private JMenuItem modifyCustomGroupItem;
    private JMenuItem delCustomGroupItem;
    private DefaultMutableTreeNode node;
    public CustomTreeRightMenu(DefaultMutableTreeNode node) {
        super();
        this.node = node;
        this.initCustomTreeRightMenuUi();
    }

    /**
     * 初始化
     */
    private void initCustomTreeRightMenuUi(){
        if(node.isRoot()){
            createCustomGroupItem = new JMenuItem("添加自定义组...");
            createCustomGroupItem.addActionListener(this);
            this.add(createCustomGroupItem);
        }else{
            modifyCustomGroupItem = new JMenuItem("修改");
            delCustomGroupItem = new JMenuItem("删除");
            modifyCustomGroupItem.addActionListener(this);
            delCustomGroupItem.addActionListener(this);
            this.add(modifyCustomGroupItem);
            this.add(delCustomGroupItem);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createCustomGroupItem){
            GroupManager.showGroupWindow(new GroupChatInfo(),"创建自定义组");
        }else if(e.getSource() == delCustomGroupItem){
            GroupChatInfo groupChatInfo = (GroupChatInfo)node.getUserObject();
            try {
                GroupManager.deleteGroup(groupChatInfo);
                JOptionPane.showMessageDialog(DtClientWindow.getInstance(), "群组删除成功!");
                DtClientWindow.getInstance().getCustomTree().deleteNode(node);
            } catch (XMPPException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(DtClientWindow.getInstance(), "群组删除失败!");
            }
        }else if(e.getSource() == modifyCustomGroupItem){
            GroupChatInfo groupChatInfo = (GroupChatInfo)node.getUserObject();
            GroupManager.showGroupWindow(groupChatInfo,"修改自定义组");
        }
    }
}
