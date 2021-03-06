package mangager;

import com.comunication.connection.ConnectionManager;
import images.PresenceImagesFactory;
import org.jivesoftware.smack.packet.Presence;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HK on 2014/10/6.
 */
public class PresenceManager {

    private static final List<Presence> PRESENCES = new ArrayList<Presence>();
    private static ImageIcon online = PresenceImagesFactory.getInstance().createOnLine();
    private static ImageIcon offline = PresenceImagesFactory.getInstance().createOffLine();
    private static ImageIcon busy = PresenceImagesFactory.getInstance().createBusy();
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

    public static Presence getPresence(String jid){
        if(jid!=null) {
            ConnectionManager.getConnection().getRoster().reload();
            return ConnectionManager.getConnection().getRoster().getPresence(jid);
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
