package com.ui.tree;


import com.component.rosterTree.ContactGroup;
import com.component.rosterTree.ContactItem;
import com.component.rosterTree.TreePane;
import com.ui.resource.YhImageRes;
import mangager.PresenceManager;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPException;
import org.smackservice.RosterManager;
import org.smackservice.SmackConnection;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by a on 2014/9/5.
 */
public class YhContactTree extends TreePane {

    private ContactGroup recentContacts;
    private ContactGroup friends;
    private ContactGroup address;

    @Override
    protected void loadData() {
        recentContacts = new ContactGroup("最近聯繫人");
        friends = new ContactGroup("friends");
        address = new ContactGroup("通訊錄");
        //获得所有的好友
        final ImageIcon headIcon = YhImageRes.getImageIcon("tree/" + "headItem.png");
        final Color hoverColor = new Color(246, 243, 251);
        final Color pressedColor = new Color(197, 148, 193);
        List<RosterEntry> rosterEntryList = RosterManager.getRosters();
        for (RosterEntry rosterEntry : rosterEntryList) {
            ContactItem contactItem = new ContactItem();
            contactItem.setHeadIcon(headIcon);
            contactItem.setJid(rosterEntry.getUser());
            contactItem.setUserName(rosterEntry.getName());
            contactItem.setHoverColor(hoverColor);
            contactItem.setPressedColor(pressedColor);
            contactItem.setPresenceIcon(PresenceManager.getPresenceIcon(PresenceManager.getPresence(contactItem.getJid())));
            friends.addContactItem(contactItem);
        }
        mainPanel.add(recentContacts);
        mainPanel.add(friends);
        mainPanel.add(address);
    }


    public ContactGroup getFriends() {
        return friends;
    }

    public static void main(String[] args) {
        try {
            SmackConnection.getInstance().connect();
            SmackConnection.getInstance().login("test","1");
        } catch (XMPPException e) {
            e.printStackTrace();
        }

        JFrame jFrame = new JFrame();
        jFrame.setContentPane(new YhContactTree());
        jFrame.setSize(500, 500);
        jFrame.setVisible(true);
    }
}
