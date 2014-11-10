package qq.manager;


import com.resource.ImageUtils;
import org.jivesoftware.smack.packet.Presence;
import org.smackservice.RosterManager;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HK on 2014/10/6.
 */
public class PresenceManager {

    private static final List<Presence> PRESENCES = new ArrayList<Presence>();
    private static ImageUtils imageUtils = ImageUtils.getInstance("presence/");
    private static ImageIcon online = imageUtils.getImageIcon("online.png"); //����
    private static ImageIcon offline = imageUtils.getImageIcon("offline.png"); //����;
    private static ImageIcon busy = imageUtils.getImageIcon("busy.png"); //æµ
    private static ImageIcon away = imageUtils.getImageIcon("away.png"); //�뿪
    private static ImageIcon hide = imageUtils.getImageIcon("hide.png"); //����
    private static ImageIcon nobother = imageUtils.getImageIcon("nobother.png"); //����
    private RosterManager rosterManager;
    static{
        final Presence presence = new Presence(Presence.Type.available, "��������", 1, Presence.Mode.available);
        final Presence awayPresence = new Presence(Presence.Type.available, "�뿪", 0, Presence.Mode.away);
        final Presence dndPresence = new Presence(Presence.Type.available, "æµ", 0, Presence.Mode.dnd);
        final Presence nobotherPresence = new Presence(Presence.Type.available, "����", 0, Presence.Mode.xa);
        final Presence hidePresence = new Presence(Presence.Type.unavailable, "����", 0, Presence.Mode.available);
        final Presence offLinePresence = new Presence(Presence.Type.unavailable, "����", 0, Presence.Mode.available);
        PRESENCES.add(presence);
        PRESENCES.add(awayPresence);
        PRESENCES.add(dndPresence);
        PRESENCES.add(nobotherPresence);
        PRESENCES.add(hidePresence);
        PRESENCES.add(offLinePresence);
    }

    public PresenceManager(RosterManager rosterManager) {
        this.rosterManager = rosterManager;
    }

    /**
     * Returns the Presence Map.
     *
     * @return the Presence Map.
     */
    public static List<Presence> getPresences() {
        return PRESENCES;
    }

    public  Presence getPresence(String jid){
        if(jid!=null) {
            return rosterManager.getFriendPresence(jid);
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

    public static ImageIcon getAway() {
        return away;
    }

    public static ImageIcon getHide() {
        return hide;
    }

    public static ImageIcon getNobother() {
        return nobother;
    }

    public ImageIcon getPresenceIcon(Presence presence){
        if(presence.getType().equals(Presence.Type.available)){
            if(presence.getMode()==null|| presence.getMode().equals(Presence.Mode.available)){
                return getOnline();
            }else if(presence.getMode().equals(Presence.Mode.dnd)){
                return getBusy();
            }else if(presence.getMode().equals(Presence.Mode.away)){
                return getAway();
            }else if(presence.getMode().equals(Presence.Mode.xa)){
                return getNobother();
            }
        }else if(presence.getType().equals(Presence.Type.unavailable)){
               if(presence.getStatus().equals("����")){
                return getHide();
               }
        }
        return getOffline();
    }
}
