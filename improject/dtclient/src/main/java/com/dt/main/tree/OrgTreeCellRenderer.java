package com.dt.main.tree;
import com.comunication.chat.GroupChatInfo;
import com.dt.vo.Unit;
import com.dt.vo.UserInfo;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

/**
 * ��֯�����������
 * Created by a on 2014/7/9.
 */
public class OrgTreeCellRenderer extends DefaultTreeCellRenderer {

    public OrgTreeCellRenderer() {
        this.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        setOpenIcon(new ImageIcon("resources/images/tree/expanded.gif"));
        setClosedIcon(new ImageIcon("resources/images/tree/collapsed.gif"));
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        if (value instanceof DefaultMutableTreeNode) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
            Object obj = (node).getUserObject();
            if(leaf && !node.isRoot())setIcon(null);
            if (obj instanceof Unit) {
                Unit unit = (Unit) obj;
                setText(unit.getUnitName());
            } else if (obj instanceof UserInfo) {
                UserInfo user = (UserInfo) obj;
                setText(user.getUsername()+"("+user.getId()+")");
                setIcon(UserHeadImageProvider.getHeadImageIcon(user.getSex()));
            }else if(obj instanceof GroupChatInfo){
                GroupChatInfo groupChatInfo = (GroupChatInfo) obj;
                setText(groupChatInfo.getName());
            }
        }
        return this;
    }
}
