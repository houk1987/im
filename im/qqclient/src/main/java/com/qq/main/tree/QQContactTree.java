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
        friends = new QQContactGroup("�ҵĺ���");
        recentContacts = new QQContactGroup("İ����");
        address = new QQContactGroup("������");
        FriendsManager friendsManager = FriendsManager.getInstance();
        List<ContactItem> friendsList = friendsManager.getAllFriends();  //������еĺ���
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
