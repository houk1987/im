package tree;
import com.comunication.connection.ConnectionManager;
import com.comunication.roster.RosterManager;
import com.ui.rosterTree.ContactGroup;
import com.ui.rosterTree.TreePane;
import images.TreeImagesFactory;
import main.MainFrame;
import mangager.PresenceManager;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.packet.Presence;

import javax.swing.*;
import java.awt.*;
import java.util.List;


/**
 * Created by a on 2014/9/5.
 */
public class YhContactTree extends TreePane {
    private int onlineNumber;
    private RosterManager rosterManager;
    private YhContactGroup recentContacts;
    private YhContactGroup friends;
    private ContactGroup address;
    private static final Color hoverColor = new Color(246, 243, 251);
    private static final Color pressedColor = new Color(197, 148, 193);
    final static ImageIcon headIcon = TreeImagesFactory.createHeadItem();
    @Override
    protected void loadData() {
        Font font =new Font("宋体",Font.PLAIN,12);
        recentContacts = new YhContactGroup("最近聯繫人",new Color(119,36,111),font);
        friends = new YhContactGroup("friends",new Color(119,36,111),font);
        address = new YhContactGroup("通訊錄",new Color(119,36,111),font);
        rosterManager = new RosterManager(ConnectionManager.getConnection().getRoster());
        List<RosterEntry> rosterEntryList = rosterManager.getRosters();
        for (RosterEntry rosterEntry : rosterEntryList) {
           // if(rosterEntry.getUser().split("@")[0].equals(MainFrame.getInstance().getLoginUser()))continue;
            YhContactItem yhContactItem = initYhContactItem(rosterEntry.getUser(),rosterEntry.getName());
            Presence presence = PresenceManager.getPresence(yhContactItem.getJid());
            if(presence.getType().equals(Presence.Type.available)){
                onlineNumber++;
            }else if(onlineNumber>0){
                onlineNumber--;
            }
            friends.calOnlineNum(onlineNumber); //计算在线人数
            friends.addContactItem(yhContactItem);
        }
        mainPanel.add(recentContacts);
        mainPanel.add(friends);
        mainPanel.add(address);
    }

    @Override
    protected void setBackGround() {

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
        RosterEntry rosterEntry = rosterManager.getRosterEntry(jid);
        YhContactItem yhContactItem = null;
        if(rosterEntry==null){
            yhContactItem = MainFrame.getInstance().getYhContactTree().initYhContactItem(jid,userName);
        }else{
            yhContactItem = MainFrame.getInstance().getYhContactTree().initYhContactItem(rosterEntry.getUser(),rosterEntry.getName());
        }
        Presence presence = new Presence(Presence.Type.available);
        presence.setMode(Presence.Mode.available);
        yhContactItem.setPresenceIcon(PresenceManager.getPresenceIcon(presence));
        onlineNumber++;
        MainFrame.getInstance().getYhContactTree().getFriends().calOnlineNum(onlineNumber); //计算在线人数
        MainFrame.getInstance().getYhContactTree().getFriends().addContactItem(yhContactItem);
        MainFrame.getInstance().getYhContactTree().getFriends().updateUI();
    }

    public int getOnlineNumber() {
        return onlineNumber;
    }
}
