package com.qq.main.tree;
import com.comunication.roster.RosterManager;
import com.qq.friends.FriendsManager;
import com.ui.rosterTree.ContactGroup;
import com.ui.rosterTree.ContactItem;
import com.ui.rosterTree.TreePane;

import javax.swing.text.html.parser.Entity;
import java.awt.*;
import java.util.List;

/**
 * Created by a on 2014/9/5.
 */
public class QQContactTree extends TreePane {

    private QQContactGroup recentContacts;
    private QQContactGroup friends;
    private QQContactGroup address;

    @Override
    protected void loadData() {
        friends = new QQContactGroup("我的好友");
        recentContacts = new QQContactGroup("陌生人");
        address = new QQContactGroup("黑名单");
        FriendsManager friendsManager = FriendsManager.getInstance();
        List<ContactItem> friendsList = friendsManager.getAllFriends();  //获得所有的好友
        for (ContactItem contactItem : friendsList) {
            friends.addContactItem(contactItem);
        }
        friends.calOnlineNum(friendsManager.getOnlineNumber());
        mainPanel.add(friends);
        mainPanel.add(recentContacts);
        mainPanel.add(address);
    }

   public ContactGroup getFriends() {
        return friends;
   }

    @Override
    protected void setBackGround() {
        mainPanel.setBackground(new Color(231,243,250));
    }
}
