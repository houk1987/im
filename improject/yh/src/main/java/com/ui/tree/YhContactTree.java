package com.ui.tree;


import com.component.rosterTree.ContactGroup;
import com.component.rosterTree.ContactItem;
import com.component.rosterTree.TreePane;
import com.ui.MainFrame;
import com.ui.resource.YhImageRes;
import mangager.PresenceManager;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Presence;
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
    private static final Color hoverColor = new Color(246, 243, 251);
    private static final Color pressedColor = new Color(197, 148, 193);
    final static ImageIcon headIcon = YhImageRes.getImageIcon("tree/" + "headItem.png");
    @Override
    protected void loadData() {
        recentContacts = new ContactGroup("最近聯繫人");
        friends = new ContactGroup("friends");
        address = new ContactGroup("通訊錄");
        //获得所有的好友


        List<RosterEntry> rosterEntryList = RosterManager.getRosters();
        for (RosterEntry rosterEntry : rosterEntryList) {
            YhContactItem yhContactItem = initYhContactItem(rosterEntry.getUser(),rosterEntry.getName());
            Presence presence = PresenceManager.getPresence(yhContactItem.getJid());
            friends.calOnlineNum(presence.getType().equals(Presence.Type.available)); //计算在线人数
            friends.addContactItem(yhContactItem);
        }
        mainPanel.add(recentContacts);
        mainPanel.add(friends);
        mainPanel.add(address);
    }

    public YhContactItem initYhContactItem(String jid,String userName){
        YhContactItem contactItem = new YhContactItem();
        contactItem.setHeadIcon(headIcon);
        contactItem.setJid(jid);
        contactItem.setUserName(userName);
        contactItem.setHoverColor(hoverColor);
        contactItem.setPressedColor(pressedColor);
        Presence presence = PresenceManager.getPresence(contactItem.getJid());
        contactItem.setPresenceIcon(PresenceManager.getPresenceIcon(presence));
        return contactItem;
    }


    public ContactGroup getFriends() {
        return friends;
    }

    public void addContactItem(String jid,String userName){
        RosterEntry rosterEntry = RosterManager.getRosterEntry(jid);
        YhContactItem yhContactItem = null;
        if(rosterEntry==null){
            yhContactItem = MainFrame.getInstance().getYhContactTree().initYhContactItem(jid,userName);
        }else{
            yhContactItem = MainFrame.getInstance().getYhContactTree().initYhContactItem(rosterEntry.getUser(),rosterEntry.getName());
        }
        Presence presence = PresenceManager.getPresence(yhContactItem.getJid());
        MainFrame.getInstance().getYhContactTree().getFriends().calOnlineNum(presence.getType().equals(Presence.Type.available)); //计算在线人数
        MainFrame.getInstance().getYhContactTree().getFriends().addContactItem(yhContactItem);
        MainFrame.getInstance().getYhContactTree().getFriends().updateUI();
    }


}
