package tree;


import com.component.rosterTree.ContactGroup;
import com.component.rosterTree.TreePane;
import com.ui.MainFrame;
import resource.Yh;
import resource.YhImageRes;
import mangager.PresenceManager;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.packet.Presence;
import org.smackservice.RosterManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by a on 2014/9/5.
 */
public class YhContactTree extends TreePane {

    private YhContactGroup recentContacts;
    private YhContactGroup friends;
    private ContactGroup address;
    private static final Color hoverColor = new Color(246, 243, 251);
    private static final Color pressedColor = new Color(197, 148, 193);
    final static ImageIcon headIcon = YhImageRes.getImageIcon("tree/" + "headItem.png");
    @Override
    protected void loadData() {
        Font  font =new Font("宋体",Font.PLAIN,12);
        recentContacts = new YhContactGroup("最近聯繫人",new Color(119,36,111),font);
        friends = new YhContactGroup("friends",new Color(119,36,111),font);
        address = new YhContactGroup("通訊錄",new Color(119,36,111),font);
        List<RosterEntry> rosterEntryList = RosterManager.getRosters();
        for (RosterEntry rosterEntry : rosterEntryList) {
            if(rosterEntry.getUser().split("@")[0].equals(Yh.getLoginUser()))continue;
            YhContactItem yhContactItem = initYhContactItem(rosterEntry.getUser(),rosterEntry.getName());
            Presence presence = PresenceManager.getPresence(yhContactItem.getJid());
            friends.calOnlineNum(presence.getType().equals(Presence.Type.available)); //计算在线人数
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
        RosterEntry rosterEntry = RosterManager.getRosterEntry(jid);
        YhContactItem yhContactItem = null;
        if(rosterEntry==null){
            yhContactItem = MainFrame.getInstance().getYhContactTree().initYhContactItem(jid,userName);
        }else{
            yhContactItem = MainFrame.getInstance().getYhContactTree().initYhContactItem(rosterEntry.getUser(),rosterEntry.getName());
        }
        Presence presence = new Presence(Presence.Type.available);
        presence.setMode(Presence.Mode.available);
        yhContactItem.setPresenceIcon(PresenceManager.getPresenceIcon(presence));
        MainFrame.getInstance().getYhContactTree().getFriends().calOnlineNum(presence.getType().equals(Presence.Type.available)); //计算在线人数
        MainFrame.getInstance().getYhContactTree().getFriends().addContactItem(yhContactItem);
        MainFrame.getInstance().getYhContactTree().getFriends().updateUI();
    }
}
