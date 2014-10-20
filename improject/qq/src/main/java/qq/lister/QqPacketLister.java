package qq.lister;


import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import qq.friends.AcceptNewFriendDialog;

import qq.main.MainDialog;
import qq.main.tree.QQContactItem;
import qq.session.SessionFrame;


import javax.swing.*;

/**
 * Created by HK on 2014/10/6.
 */
public class QqPacketLister implements PacketListener {
    @Override
    public void processPacket(Packet packet) {
        if(packet instanceof Message){
           final Message message  =(Message)packet;
            String subject = message.getSubject();
            if("∫√”—…Í«Î".equals(subject)){
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        AcceptNewFriendDialog acceptNewContactDialog = new AcceptNewFriendDialog(message.getFrom());
                        acceptNewContactDialog.showNotifyWindow();
                    }
                });

            }else if(message.getType().equals(Message.Type.chat)){
                String from = message.getFrom().split("/")[0];
                QQContactItem qqContactItem = new QQContactItem();
                qqContactItem.setJid(from);
                SessionFrame sessionFrame = SessionFrame.CreateAndShowSessionFrame(qqContactItem);
                sessionFrame.insertMessageToDisplay(message);
            }else if(message.getType().equals(Message.Type.normal)){
                MainDialog.getInstance().getFriendsTree().addContactItem(message.getTo(), message.getTo().split("@")[0]);
            }
        }
    }
}
