package lister;

import main.MainFrame;
import mangager.PresenceManager;
import org.jivesoftware.smack.RosterListener;
import org.jivesoftware.smack.packet.Presence;
import java.util.Collection;

/**
 * Created by HK on 2014/10/6.
 */
public class YhRosterListener implements RosterListener {
    @Override
    public void entriesAdded(Collection<String> addresses) {

    }

    @Override
    public void entriesUpdated(Collection<String> addresses) {

    }

    @Override
    public void entriesDeleted(Collection<String> addresses) {

    }

    @Override
    public void presenceChanged(Presence presence) {
        String from = presence.getFrom().split("/")[0];
        int number = MainFrame.getInstance().getYhContactTree().getOnlineNumber();
        if(presence.isAvailable()){
            number++;
        }else{
            number--;
        }
        MainFrame.getInstance().getYhContactTree().getFriends().calOnlineNum(number);   //更新界面上在线数量

    }
}
