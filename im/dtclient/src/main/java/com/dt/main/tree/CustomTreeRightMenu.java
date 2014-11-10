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
 * �Զ��������Ҽ��˵�
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
     * ��ʼ��
     */
    private void initCustomTreeRightMenuUi(){
        if(node.isRoot()){
            createCustomGroupItem = new JMenuItem("����Զ�����...");
            createCustomGroupItem.addActionListener(this);
            this.add(createCustomGroupItem);
        }else{
            modifyCustomGroupItem = new JMenuItem("�޸�");
            delCustomGroupItem = new JMenuItem("ɾ��");
            modifyCustomGroupItem.addActionListener(this);
            delCustomGroupItem.addActionListener(this);
            this.add(modifyCustomGroupItem);
            this.add(delCustomGroupItem);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createCustomGroupItem){
            GroupManager.showGroupWindow(new GroupChatInfo(),"�����Զ�����");
        }else if(e.getSource() == delCustomGroupItem){
            GroupChatInfo groupChatInfo = (GroupChatInfo)node.getUserObject();
            try {
                GroupManager.deleteGroup(groupChatInfo);
                JOptionPane.showMessageDialog(DtClientWindow.getInstance(), "Ⱥ��ɾ���ɹ�!");
                DtClientWindow.getInstance().getCustomTree().deleteNode(node);
            } catch (XMPPException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(DtClientWindow.getInstance(), "Ⱥ��ɾ��ʧ��!");
            }
        }else if(e.getSource() == modifyCustomGroupItem){
            GroupChatInfo groupChatInfo = (GroupChatInfo)node.getUserObject();
            GroupManager.showGroupWindow(groupChatInfo,"�޸��Զ�����");
        }
    }
}
