package qq.manager;


import com.component.ImageUtils;
import org.jivesoftware.smack.packet.Presence;
import org.smackservice.SmackConnection;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HK on 2014/10/6.
 */
public class PresenceManager {

    private static final List<Presence> PRESENCES = new ArrayList<Presence>();
    private static ImageUtils imageUtils = ImageUtils.getInstance("presence");
    private static ImageIcon online = imageUtils.getImageIcon("online.png");
    private static ImageIcon offline = imageUtils.getImageIcon("offline.png");
    private static ImageIcon busy = imageUtils.getImageIcon("busy.png");
    static{
        final Presence presence = new Presence(Presence.Type.available, "我有空", 1, Presence.Mode.available);
        final Presence dndPresence = new Presence(Presence.Type.available, "忙碌中", 0, Presence.Mode.dnd);
        final Presence hidePresence = new Presence(Presence.Type.unavailable, "對所有人隱藏", 0, Presence.Mode.available);
        PRESENCES.add(presence);
        PRESENCES.add(dndPresence);
        PRESENCES.add(hidePresence);
    }

    /**
     * Returns the Presence Map.
     *
     * @return the Presence Map.
     */
    public static List<Presence> getPresences() {
        return PRESENCES;
    }

    public static void changePresence(Presence presence){
        SmackConnection.getInstance().sendPacket(presence);
    }

    public static Presence getPresence(String jid){
        if(jid!=null) {
            return SmackConnection.getInstance().getRoster().getPresence(jid);
        }
        return null;
    }


    public static ImageIcon getOnline() {
        return online;
    }

    public static ImageIcon getOffline() {
        return offline;
    }

    public static ImageIcon getBusy() {
        return busy;
    }

    public static ImageIcon getPresenceIcon(Presence presence){
        if(presence.getType().equals(Presence.Type.available)){
            if(presence.getMode()==null|| presence.getMode().equals(Presence.Mode.available)){
                return getOnline();
            }else if(presence.getMode().equals(Presence.Mode.dnd)){
                return getBusy();
            }
        }
        return getOffline();
    }
}
