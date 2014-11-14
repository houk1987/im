package qq.main.tree;

import com.ui.rosterTree.ContactGroup;
import com.ui.rosterTree.ContactItem;
import com.ui.rosterTree.TreePane;
import qq.lunch.QQClient;

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
        List<ContactItem> friendsList = QQClient.getInstance().getFriendsManager().getAllFriends();  //������еĺ���
        for (ContactItem contactItem : friendsList) {
            friends.addContactItem(contactItem);
        }
        friends.calOnlineNum(QQClient.getInstance().getFriendsManager().getOnlineNumber()); //r
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
