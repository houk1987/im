package lister;

import com.ui.tree.YhContactItem;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import session.SessionFrame;

/**
 * Created by HK on 2014/10/6.
 */
public class YhPacketLister implements PacketListener {
    @Override
    public void processPacket(Packet packet) {
        if(packet instanceof Message){
            Message message  =(Message)packet;
            String from = message.getFrom().split("/")[0];
            YhContactItem yhContactItem = new YhContactItem();
            yhContactItem.setJid(from);
            SessionFrame sessionFrame = SessionFrame.CreateAndShowSessionFrame(yhContactItem);
            sessionFrame.insertMessageToDisplay(message);
        }
    }
}
