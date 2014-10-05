package com.ui.tree;




import com.component.rosterTree.ContactGroup;
import com.component.rosterTree.ContactItem;
import com.component.rosterTree.TreePane;
import com.ui.resource.YhImageRes;

import javax.swing.*;
import java.awt.*;

/**
 * Created by a on 2014/9/5.
 */
public class YhContactTree extends TreePane {

    private ContactGroup recentContacts;
    private ContactGroup friends;
    private ContactGroup address;

    @Override
    protected void loadData() {
        recentContacts  = new ContactGroup("最近聯繫人");
        friends = new ContactGroup("friends");
        address = new ContactGroup("通訊錄");
        //获得所有的好友

      //  recentContacts.setIcon(YhImageRes.getImageIcon("tree/"+"headItm.png"));

        ContactItem contactItem = new ContactItem();
        contactItem.setUserName("1");
        contactItem.setHeadIcon(YhImageRes.getImageIcon("tree/"+"headItem.png"));
        contactItem.setHoverColor(new Color(246,243,251));
        recentContacts.addContactItem(contactItem);
        recentContacts.addContactItem(contactItem);

        mainPanel.add(recentContacts);
       // mainPanel.add(friends);
       // mainPanel.add(friends);
       // mainPanel.add(address);
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setContentPane(new YhContactTree());
        jFrame.setSize(500,500);
        jFrame.setVisible(true);
    }
}
