package qq.presence;

import org.jivesoftware.smack.packet.Presence;
import org.smackservice.SmackConnection;
import qq.lunch.QQClient;
import qq.manager.PresenceManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by lenovo on 2014/10/21.
 */
public class PresenceMenu extends JPopupMenu {
    private PresenceManager presenceManager;
    private JLabel presenceLabel;
    public PresenceMenu(JLabel presenceLabel) {
        this.presenceLabel = presenceLabel;
        presenceManager = QQClient.getInstance().getPresenceManager();
        List<Presence> presenceTypeList = presenceManager.getPresences();
        for(Presence e : presenceTypeList){
            PresenceItem menuItem = new PresenceItem(e);
            add(menuItem);
        }
    }

    class PresenceItem extends JMenuItem{
        PresenceItem(final Presence type) {
            this.setText(type.getStatus());
            this.setIcon(presenceManager.getPresenceIcon(type));
            this.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    QQClient.getInstance().changeClientPresence(type);
                    presenceLabel.setIcon(presenceManager.getPresenceIcon(type));
                }
            });
        }
    }
}
