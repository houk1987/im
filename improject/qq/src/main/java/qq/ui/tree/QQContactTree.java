package qq.ui.tree;


import com.component.rosterTree.ContactGroup;
import com.component.rosterTree.TreePane;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Presence;
import org.smackservice.RosterManager;
import org.smackservice.SmackConnection;
import qq.manager.PresenceManager;

import javax.swing.*;
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
        //获得所有的好友
        List<RosterEntry> rosterEntryList = RosterManager.getRosters();
        for (RosterEntry rosterEntry : rosterEntryList) {
            QQContactItem contactItem = new QQContactItem(rosterEntry.getUser(),rosterEntry.getName());
            Presence presence = PresenceManager.getPresence(contactItem.getJid());
            friends.calOnlineNum(presence.getType().equals(Presence.Type.available)); //计算在线人数
            friends.addContactItem(contactItem);
        }
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
