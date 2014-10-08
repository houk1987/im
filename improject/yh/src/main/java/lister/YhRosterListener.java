package lister;

import com.component.rosterTree.ContactItem;
import com.ui.MainFrame;
import com.ui.resource.Yh;
import com.ui.resource.YhImageRes;
import com.ui.tree.YhContactItem;
import mangager.PresenceManager;
import org.jivesoftware.smack.RosterListener;
import org.jivesoftware.smack.packet.Presence;
import session.SessionFrame;

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
        if(presence.getType().equals(Presence.Type.available)){
            MainFrame.getInstance().getYhContactTree().getFriends().updateContactItem(from, PresenceManager.getOnline(),true);
        }else{
            MainFrame.getInstance().getYhContactTree().getFriends().updateContactItem(from, PresenceManager.getOffline(),false);
        }
    }
}
