package com.qq.friends;
import com.comunication.roster.RosterManager;
import com.qq.main.tree.QQContactItem;
import com.ui.rosterTree.ContactItem;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.packet.Presence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lenovo on 2014/10/21.
 */
public class FriendsManager {
    private final static FriendsManager friendsManager = new FriendsManager();
    private int onlineNumber;
    private RosterManager rosterManager = new RosterManager();
    private List<ContactItem> contactItemList;
    private HashMap<String,QQContactItem> itemHashMap ;

    public static FriendsManager getInstance() {
        return friendsManager;
    }

    private FriendsManager() {

    }

    /**
     * 获取所有的好友
     * @return  返回好友列表
     */
    public List<ContactItem> getAllFriends(){
        List<RosterEntry> rosterEntryList = rosterManager.getRosters();  //获取花名册上的好友列表
        contactItemList = new ArrayList<>();  //好友集合
        itemHashMap = new HashMap<>();
        //PresenceManager presenceManager = QQClient.getInstance().getPresenceManager();
        for (RosterEntry rosterEntry : rosterEntryList) {
            QQContactItem contactItem = new QQContactItem(rosterEntry.getUser(),rosterEntry.getName());  //创建好友
           // Presence presence = presenceManager.getPresence(contactItem.getJid());  //获取状态
            //if(presence.getType().equals(Presence.Type.available))onlineNumber++;  //计算在线人数
            contactItemList.add(contactItem);  //添加好友
            itemHashMap.put(contactItem.getJid(),contactItem);
        }
        return contactItemList;
    }

    /**
     * 获取单个好友
     * @param userName  好友账号
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

    public boolean isFriend(String userName){
        RosterEntry rosterEntry = rosterManager.getRosterEntry(userName);
        return rosterEntry!=null;
    }

    /**
     * 添加好友
     * @param userName  好友账号
     */
    public void sendFriendApply(String userName){
       // rosterManager.sendFriendApply(QQClient.getInstance().getLoginUserName(),userName);  //发送申请好友请求
    }

    public QQContactItem addNewContactItem(String userName){
        QQContactItem qqContactItem=   getFriend(userName);
        itemHashMap.put(qqContactItem.getJid(),qqContactItem);
        contactItemList.add(qqContactItem);
        //PresenceManager presenceManager = QQClient.getInstance().getPresenceManager();
       // Presence presence = presenceManager.getPresence(qqContactItem.getJid());  //获取状态
      //  if(presence.getType().equals(Presence.Type.available))onlineNumber++;  //计算在线人数
        return qqContactItem;
    }


    public void delFriend(String userName) throws Exception {
        rosterManager.delRoster(userName);
    }

    public String getName(String userName){
       return rosterManager.getRosterEntry(userName).getName();
    }

    /**
     * 更新在线人数
     */
    public int updateOnlineNumber(Presence presence){
        String from = presence.getFrom().split("/")[0];  //状态变化的账号
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
