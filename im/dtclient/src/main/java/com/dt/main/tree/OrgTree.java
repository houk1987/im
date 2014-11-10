package com.dt.main.tree;
import com.dt.chat.ChatWindowManager;
import com.dt.vo.Unit;
import com.dt.vo.UserInfo;
import com.ui.rosterTree.PubTree;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class OrgTree extends PubTree {
    private DefaultTreeModel treeModel;

    public OrgTree() {
        super();
        treeModel = (DefaultTreeModel) this.getModel();
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TreePath path = getPathForLocation(e.getX(), e.getY());
                if (null != path) {
                    if (e.getButton() == 1 && e.getClickCount() == 2) {
                        Object object = path.getLastPathComponent();
                        if (object instanceof DefaultMutableTreeNode) {
                            DefaultMutableTreeNode selectNode = (DefaultMutableTreeNode) object;
                            if (selectNode.getUserObject() instanceof UserInfo) {
                                try {
                                    UserInfo userInfo = (UserInfo) selectNode.getUserObject();
                                    ChatWindowManager.openChatWindow(userInfo.getId());
                                } catch (Exception e1) {
                                    JOptionPane.showMessageDialog(null, "�򿪻Ự����ʧ��!", "��ʾ", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                    }
                }
            }
        });
        setCellRenderer(new OrgTreeCellRenderer());
    }

    public void loadData() {
        final List<UserInfo> userList = SynDataService.getInstance().synUsers();
        final List<Unit> unitList = SynDataService.getInstance().synUnits();
        if (unitList != null && unitList.size() > 0) {
            for (Unit unit : unitList) {
                DefaultMutableTreeNode unitNode = new DefaultMutableTreeNode(unit);
                if (unit.getParentUnitId() == null || "".equals(unit.getParentUnitId())) { //û�и��ڵ㲿�ţ�����Ϊ�����ڵ�
                    treeModel.setRoot(unitNode);
                }
               loadChildUnitNode(unit,unitNode,unitList,userList); //���ظĲ����µ��ӽڵ�
               loadUnitUserNode(userList,unit,unitNode); //���ظò����µ���Ա�ڵ�
            }
        }
    }

    private void loadUnitUserNode(List<UserInfo> userList, Unit unit, DefaultMutableTreeNode unitNode) {
        if (userList != null && userList.size() > 0) {
            for (UserInfo user : userList) {
                if (unit.getUnitId().equals(user.getUnitId())) {
                    DefaultMutableTreeNode userNode = new DefaultMutableTreeNode(user);
                    unitNode.add(userNode);
                }
            }
        }
    }

    private void loadChildUnitNode(Unit parentUnit,DefaultMutableTreeNode parentNode,List<Unit> unitList,List<UserInfo> userList) {
        if (unitList != null && unitList.size() > 0) {
            for (Unit unit : unitList) {
                if(parentUnit.getUnitId().equals(unit.getParentUnitId())){
                    DefaultMutableTreeNode childUnitNode = new DefaultMutableTreeNode(unit);
                    loadChildUnitNode(unit,childUnitNode,unitList,userList);//�������ظ��ӽڵ��µĽڵ�
                    loadUnitUserNode(userList,unit,childUnitNode); //���ظò����µ���Ա�ڵ�
                    parentNode.add(childUnitNode); //��ӵ����ڵ���
                }
            }
        }
    }
}
