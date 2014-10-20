package qq.lister;
import org.jivesoftware.smack.RosterListener;
import org.jivesoftware.smack.packet.Presence;
import qq.main.MainDialog;
import qq.manager.PresenceManager;

import java.util.Collection;

/**
 * Created by HK on 2014/10/6.
 */
public class QqRosterListener implements RosterListener {
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
            MainDialog.getInstance().getFriendsTree().getFriends().updateContactItem(from, PresenceManager.getOnline(),true);
        }else{
            MainDialog.getInstance().getFriendsTree().getFriends().updateContactItem(from, PresenceManager.getOffline(),false);
        }
    }
}
