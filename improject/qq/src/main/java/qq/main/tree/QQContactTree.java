package qq.main.tree;


import com.component.rosterTree.ContactGroup;
import com.component.rosterTree.TreePane;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.packet.Presence;
import org.smackservice.RosterManager;
import qq.main.MainDialog;
import qq.manager.PresenceManager;

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

    public QQContactItem initQqContactItem(String jid, String userName){
        QQContactItem contactItem = new QQContactItem();
        contactItem.setJid(jid);
        contactItem.setUserName(userName);
        Presence presence = PresenceManager.getPresence(contactItem.getJid());
        contactItem.setPresenceIcon(PresenceManager.getPresenceIcon(presence));
        return contactItem;
    }

    public void addContactItem(String jid,String userName){
        RosterEntry rosterEntry = RosterManager.getRosterEntry(jid);
        QQContactItem yhContactItem = null;
        if(rosterEntry==null){
            yhContactItem = MainDialog.getInstance().getFriendsTree().initQqContactItem(jid, userName);
        }else{
            yhContactItem = MainDialog.getInstance().getFriendsTree().initQqContactItem(rosterEntry.getUser(), rosterEntry.getName());
        }
        Presence presence = new Presence(Presence.Type.available);
        presence.setMode(Presence.Mode.available);
        yhContactItem.setPresenceIcon(PresenceManager.getPresenceIcon(presence));
        MainDialog.getInstance().getFriendsTree().getFriends().calOnlineNum(presence.getType().equals(Presence.Type.available)); //计算在线人数
        MainDialog.getInstance().getFriendsTree().getFriends().addContactItem(yhContactItem);
        MainDialog.getInstance().getFriendsTree().getFriends().updateUI();
    }


    public ContactGroup getFriends() {
        return friends;
    }


    @Override
    protected void setBackGround() {
        mainPanel.setBackground(new Color(231,243,250));
    }
}
