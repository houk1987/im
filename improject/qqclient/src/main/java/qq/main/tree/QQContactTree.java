package qq.main.tree;


import com.component.rosterTree.ContactGroup;
import com.component.rosterTree.ContactItem;
import com.component.rosterTree.TreePane;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.packet.Presence;
import org.smackservice.RosterManager;
import qq.lunch.QQClient;
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
        List<ContactItem> friendsList = QQClient.getInstance().getFriendsManager().getAllFriends();  //获得所有的好友
        for (ContactItem contactItem : friendsList) {
            friends.addContactItem(contactItem);
        }
        friends.calOnlineNum(QQClient.getInstance().getFriendsManager().getOnlineNumber()); //
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
