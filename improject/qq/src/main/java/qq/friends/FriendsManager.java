package qq.friends;

import com.component.rosterTree.ContactItem;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.packet.Presence;
import org.smackservice.RosterManager;
import qq.lunch.QQClient;
import qq.main.tree.QQContactItem;
import qq.manager.PresenceManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lenovo on 2014/10/21.
 */
public class FriendsManager {

    private int onlineNumber;
    private RosterManager rosterManager;
    private  List<ContactItem> contactItemList;
    private HashMap<String,QQContactItem> itemHashMap ;

    public FriendsManager(RosterManager rosterManager) {
        this.rosterManager = rosterManager;
    }

    /**
     * ��ȡ���еĺ���
     * @return  ���غ����б�
     */
    public List<ContactItem> getAllFriends(){
        List<RosterEntry> rosterEntryList = rosterManager.getRosters();  //��ȡ�������ϵĺ����б�
        contactItemList = new ArrayList<>();  //���Ѽ���
        itemHashMap = new HashMap<>();
        PresenceManager presenceManager = QQClient.getInstance().getPresenceManager();
        for (RosterEntry rosterEntry : rosterEntryList) {
            QQContactItem contactItem = new QQContactItem(rosterEntry.getUser(),rosterEntry.getName());  //��������
            Presence presence = presenceManager.getPresence(contactItem.getJid());  //��ȡ״̬
            if(presence.getType().equals(Presence.Type.available))onlineNumber++;  //������������
            contactItemList.add(contactItem);  //��Ӻ���
            itemHashMap.put(contactItem.getJid(),contactItem);
        }
        return contactItemList;
    }

    /**
     * ��ȡ��������
     * @param userName  �����˺�
     * @return
     */
    public QQContactItem getFriend(String userName){
        QQContactItem contactItem = itemHashMap.get(userName);
        if(contactItem == null){
            RosterEntry rosterEntry = rosterManager.getRosterEntry(userName);
            if(rosterEntry !=null) {
                contactItem = new QQContactItem(rosterEntry.getUser(), rosterEntry.getName());
            }else{
                contactItem = new QQContactItem(userName, userName.split("@")[0]);
            }
        }
        return contactItem;

    }

    /**
     * ��Ӻ���
     * @param userName  �����˺�
     */
    public void sendFriendApply(String userName){
        rosterManager.sendFriendApply(QQClient.getInstance().getLoginUserName(),userName);  //���������������
    }

    public QQContactItem addNewContactItem(String userName){
        QQContactItem qqContactItem=   getFriend(userName);
        itemHashMap.put(qqContactItem.getJid(),qqContactItem);
        contactItemList.add(qqContactItem);
        PresenceManager presenceManager = QQClient.getInstance().getPresenceManager();
        Presence presence = presenceManager.getPresence(qqContactItem.getJid());  //��ȡ״̬
        if(presence.getType().equals(Presence.Type.available))onlineNumber++;  //������������
        return qqContactItem;
    }


    public void delFriend(String userName){

    }

    /**
     * ������������
     */
    public int updateOnlineNumber(Presence presence){
        String from = presence.getFrom().split("/")[0];  //״̬�仯���˺�
        if(presence.getType().equals(Presence.Type.available)){
            onlineNumber++;
        }else {
            onlineNumber--;
        }
        return getOnlineNumber();
    }

    public int getOnlineNumber() {
        return onlineNumber;
    }
}
