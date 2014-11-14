package qq.presence;

import com.comunication.connection.ConnectionManager;
import org.jivesoftware.smack.packet.Presence;
import qq.lunch.QQClient;
import qq.manager.PresenceManager;

import javax.swing.*;
import javax.swing.plaf.basic.BasicMenuItemUI;
import java.awt.*;
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
        this.setBackground(Color.WHITE);

        presenceManager = QQClient.getInstance().getPresenceManager();
        List<Presence> presenceTypeList = presenceManager.getPresences();
        for(Presence e : presenceTypeList){
            PresenceItem menuItem = new PresenceItem(e);
            add(menuItem);
        }
    }

    class PresenceItem extends JMenuItem{
        PresenceItem(final Presence type) {
            this.setBackground(Color.WHITE);
            this.setText(type.getStatus());
            this.setBorder(null);
            this.setUI(new MyMenuItemUI(Color.BLUE,Color.BLACK));
            this.setIcon(presenceManager.getPresenceIcon(type));
            this.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ConnectionManager.getConnection().sendPacket(type);
                    presenceLabel.setIcon(presenceManager.getPresenceIcon(type));
                }
            });
        }
    }

    class MyMenuItemUI extends BasicMenuItemUI {          public MyMenuItemUI(Color bgColor,Color fgColor){             super.selectionBackground=bgColor;             super.selectionForeground=fgColor;         }     }
}
