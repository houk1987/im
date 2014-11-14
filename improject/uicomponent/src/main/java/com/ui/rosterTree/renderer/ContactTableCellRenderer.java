package com.ui.rosterTree.renderer;

import com.ui.rosterTree.ContactGroup;
import com.ui.rosterTree.ContactItem;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Created by HK on 2014/10/5.
 */
public class ContactTableCellRenderer extends JPanel implements TableCellRenderer {
    private JLabel headIconLabel;
    private JLabel presenceIconLabel;
    private JLabel userNameLabel;
    public enum ContactTableCellRendererType{
        yh,qq
    }

    public ContactTableCellRenderer(ContactTableCellRendererType type) {
        setLayout(null);
        setBorder(null);
        headIconLabel = new JLabel();
        add(headIconLabel);

        userNameLabel = new JLabel();
        userNameLabel.setFont(new Font("Î¢ÈíÑÅºÚ",Font.PLAIN,12));
        userNameLabel.setSize(100,23);
        add(userNameLabel);
        if(type.equals(ContactTableCellRenderer.ContactTableCellRendererType.yh)){
            presenceIconLabel = new JLabel();
            presenceIconLabel.setLocation(60,8);
            add(presenceIconLabel);
            userNameLabel.setLocation(80,3);
            headIconLabel.setLocation(20,3);
        }

        if(type.equals(ContactTableCellRenderer.ContactTableCellRendererType.qq)){
            userNameLabel.setLocation(30,10);
            headIconLabel.setLocation(10,10);
        }

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
            if(contactItem.getHeadIcon()!=null){
                headIconLabel.setSize(contactItem.getHeadIcon().getIconWidth(),contactItem.getHeadIcon().getIconHeight());
            }
            userNameLabel.setText(contactItem.getUserName());
            if(presenceIconLabel!=null){
                presenceIconLabel.setSize(contactItem.getPresenceIcon().getIconWidth(),contactItem.getPresenceIcon().getIconHeight());
                presenceIconLabel.setIcon(contactItem.getPresenceIcon());
            }
            if(isSelected){
                setBackground(contactItem.getPressedColor());
            }
        }
        return this;
    }
}
