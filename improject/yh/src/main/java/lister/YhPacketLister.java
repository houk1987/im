package lister;

import com.ui.MainFrame;
import com.ui.notify.AcceptNewContactDialog;
import com.ui.resource.YhImageRes;
import com.ui.tree.YhContactItem;
import mangager.PresenceManager;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;
import org.smackservice.RosterManager;
import session.SessionFrame;

/**
 * Created by HK on 2014/10/6.
 */
public class YhPacketLister implements PacketListener {
    @Override
    public void processPacket(Packet packet) {
        if(packet instanceof Message){
            Message message  =(Message)packet;
            String subject = message.getSubject();
            if("∫√”—…Í’à".equals(subject)){
                AcceptNewContactDialog acceptNewContactDialog = new AcceptNewContactDialog(message.getFrom());
                acceptNewContactDialog.showNotifyWindow();
            }else if(message.getType().equals(Message.Type.chat)){
                String from = message.getFrom().split("/")[0];
                YhContactItem yhContactItem = new YhContactItem();
                yhContactItem.setJid(from);
                SessionFrame sessionFrame = SessionFrame.CreateAndShowSessionFrame(yhContactItem);
                sessionFrame.insertMessageToDisplay(message);
            }else if(message.getType().equals(Message.Type.normal)){
                MainFrame.getInstance().getYhContactTree().addContactItem(message.getTo(),message.getTo().split("@")[0]);
            }
        }
    }
}
