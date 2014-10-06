package com.component.rosterTree.renderer;

import com.component.rosterTree.ContactGroup;
import com.component.rosterTree.ContactItem;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by HK on 2014/10/5.
 */
public class ContactTableCellRenderer extends JPanel implements TableCellRenderer {
    private JLabel headIconLabel;
    private JLabel presenceIconLabel;
    private JLabel userNameLabel;
    public ContactTableCellRenderer() {
        setLayout(null);
        setBorder(null);
        headIconLabel = new JLabel();
        headIconLabel.setLocation(20,3);
        add(headIconLabel);

        presenceIconLabel = new JLabel();
        presenceIconLabel.setLocation(60,8);
        add(presenceIconLabel);


        userNameLabel = new JLabel();
        userNameLabel.setLocation(80,3);
        userNameLabel.setSize(100,23);
        add(userNameLabel);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table,
                                                   Object value, boolean isSelected, boolean hasFocus,
                                                   int row, int column) {

        ContactGroup.MyTable myTable = (ContactGroup.MyTable)table;
        if(value instanceof ContactItem && value !=null){
            ContactItem contactItem = (ContactItem)value;
            if(row == myTable.getMouseOnRowIndex()){
                setBackground(contactItem.getHoverColor());
            }else{
                setBackground(null);
            }
            headIconLabel.setIcon(contactItem.getHeadIcon());
            headIconLabel.setSize(contactItem.getHeadIcon().getIconWidth(),contactItem.getHeadIcon().getIconHeight());
            userNameLabel.setText(contactItem.getUserName());
            presenceIconLabel.setSize(contactItem.getPresenceIcon().getIconWidth(),contactItem.getPresenceIcon().getIconHeight());
            presenceIconLabel.setIcon(contactItem.getPresenceIcon());

            if(isSelected){
                setBackground(contactItem.getPressedColor());
            }
        }
        return this;
    }
}
