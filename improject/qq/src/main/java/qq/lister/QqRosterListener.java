package qq.lister;
import org.jivesoftware.smack.RosterListener;
import org.jivesoftware.smack.packet.Presence;
import qq.lunch.QQClient;
import qq.main.MainDialog;
import qq.main.tree.QQContactItem;
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
        final int number = QQClient.getInstance().getFriendsManager().updateOnlineNumber(presence);  //更新好友在线个数
        QQClient.getInstance().getMainDialog().getFriendsTree().getFriends().calOnlineNum(number);   //更新界面上在线数量
    }
}
