package com.dt.main.tree;


import com.comunication.chat.GroupChat;
import com.comunication.chat.GroupChatInfo;
import com.comunication.connection.ConnectionManager;
import com.dt.chat.ChatWindowManager;
import com.dt.start.StartDtClient;
import com.dt.vo.UserInfo;
import com.ui.rosterTree.PubTree;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;


/**
 * CustomTree
 * Created by a on 2014/7/10.
 */
public class CustomTree extends PubTree implements MouseListener,ComponentListener {
    private CustomTreeRightMenu customTreeRightMenu;
    private DefaultTreeModel treeModel;
    private DefaultMutableTreeNode root = new DefaultMutableTreeNode("自定义组");
    private HashMap<String,DefaultMutableTreeNode> defaultMutableTreeNodes = new HashMap<>();

    /**
     * 构造
     */
    public CustomTree() {
        super();
        treeModel = (DefaultTreeModel) this.getModel();
        treeModel.setRoot(root);
        this.setCellRenderer(new OrgTreeCellRenderer());
        this.addMouseListener(this);
    }

    /**
     * 加载自定义群组信息
     */
    public void loadCustomTreeData() {
        try {
            List<GroupChatInfo> groupChatInfoList = GroupChat.getAllGroupChat(); //获取服务器上所有的会议室
            if (groupChatInfoList != null && groupChatInfoList.size() > 0) {
                for (GroupChatInfo groupChatInfo : groupChatInfoList) {
                    if(groupChatInfo.getOwnerId().split("@")[0].equals(StartDtClient.getUserInfo().getId())) {
                        DefaultMutableTreeNode roomNode = new DefaultMutableTreeNode(groupChatInfo);
                        addNode(roomNode);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void addNode(DefaultMutableTreeNode node) {
        root.add(node);
        treeModel.reload();
        Object object = node.getUserObject();
        if(object instanceof GroupChatInfo){
            GroupChatInfo groupChatInfo = (GroupChatInfo)object;
            defaultMutableTreeNodes.put(groupChatInfo.getJid(),node);
        }
    }

    public void updateGroupNode(GroupChatInfo groupChatInfo){
       DefaultMutableTreeNode node =  defaultMutableTreeNodes.get(groupChatInfo.getJid());
       node.setUserObject(groupChatInfo);
       updateUI();
    }

    public void deleteNode(DefaultMutableTreeNode node){
        root.remove(node);
        treeModel.reload();
        defaultMutableTreeNodes.remove(node);
    }

    public GroupChatInfo getGroupChatInfo(String jid){
        DefaultMutableTreeNode node = defaultMutableTreeNodes.get(jid);
        if(node != null){
            Object obj = node.getUserObject();
            if(obj instanceof GroupChatInfo){
                return (GroupChatInfo)obj;
            }
        }
        return null;
    }

    public void setRootNodeName(Object o) {
        root.setUserObject(o);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        TreePath path = this.getPathForLocation(e.getX(), e.getY());
        if (path != null) {
            Object component = path.getLastPathComponent();
            if (component instanceof DefaultMutableTreeNode) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) component;
                if (e.getButton() == 3 && this.isPathSelected(path)) {
                    this.customTreeRightMenu = new CustomTreeRightMenu(node);
                    this.customTreeRightMenu.show(this, e.getX() + 5, e.getY() + 5);
                } else if (e.getButton() == 1) {
                    this.setSelectionPath(path);
                    if (e.getClickCount() == 2) {
                        Object object = node.getUserObject();
                        if(object instanceof GroupChatInfo){
                            GroupChatInfo groupChatInfo = (GroupChatInfo)object;
                            ChatWindowManager.openGroupChatWindow(groupChatInfo.getJid());
                        }else if(object instanceof UserInfo){
                            UserInfo userInfo = (UserInfo)object;
                            if(!userInfo.getId().equals(StartDtClient.getUserInfo().getId())){
                                ChatWindowManager.openChatWindow(userInfo.getId());
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    @Override
    public void componentResized(ComponentEvent e) {
        
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
