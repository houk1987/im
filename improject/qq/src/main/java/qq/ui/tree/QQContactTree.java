package qq.ui.tree;


import com.component.rosterTree.ContactGroup;
import com.component.rosterTree.TreePane;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Presence;
import org.smackservice.RosterManager;
import org.smackservice.SmackConnection;
import qq.manager.PresenceManager;
import qq.resource.Qq;
import qq.resource.QqImageRes;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by a on 2014/9/5.
 */
public class QQContactTree extends TreePane {

    private ContactGroup recentContacts;
    private ContactGroup friends;
    private ContactGroup address;

    @Override
    protected void loadData() {
        recentContacts = new ContactGroup("我的好友");
        friends = new ContactGroup("陌生人");
        address = new ContactGroup("黑名单");
        //获得所有的好友
        final ImageIcon headIcon = QqImageRes.getImageIcon("tree/" + "head.png");
        final Color hoverColor = new Color(246, 243, 251);
        final Color pressedColor = new Color(197, 148, 193);
        List<RosterEntry> rosterEntryList = RosterManager.getRosters();
        for (RosterEntry rosterEntry : rosterEntryList) {
            QQContactItem contactItem = new QQContactItem();
            contactItem.setHeadIcon(headIcon);
            contactItem.setJid(rosterEntry.getUser());
            contactItem.setUserName(rosterEntry.getName());
            contactItem.setHoverColor(hoverColor);
            contactItem.setPressedColor(pressedColor);
            Presence presence = PresenceManager.getPresence(contactItem.getJid());
            contactItem.setPresenceIcon(PresenceManager.getPresenceIcon(presence));
            friends.calOnlineNum(presence.getType().equals(Presence.Type.available)); //计算在线人数
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
            SmackConnection.getInstance().login("1","1");
        } catch (XMPPException e) {
            e.printStackTrace();
        }

        JFrame jFrame = new JFrame();
        jFrame.setContentPane(new QQContactTree());
        jFrame.setSize(500, 500);
        jFrame.setVisible(true);
    }
}
