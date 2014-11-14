package com.dt.main.presence;


import com.comunication.roster.RosterManager;
import com.dt.images.PresenceImagesFactory;
import com.resource.ImageUtils;
import org.jivesoftware.smack.packet.Presence;


import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HK on 2014/10/6.
 */
public class PresenceManager {

    private static final List<Presence> PRESENCES = new ArrayList<Presence>();
    private static ImageIcon online = PresenceImagesFactory.getInstance().createOnLine(); //在线
    private static ImageIcon offline = PresenceImagesFactory.getInstance().createOffLine(); //离线;
    private RosterManager rosterManager;
    static{
        final Presence presence = new Presence(Presence.Type.available, "我在线上", 1, Presence.Mode.available);
        final Presence offLinePresence = new Presence(Presence.Type.unavailable, "离线", 0, Presence.Mode.available);
        PRESENCES.add(presence);
        PRESENCES.add(offLinePresence);
    }

    public PresenceManager() {

        //rosterManager = new RosterManager();
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

    public ImageIcon getPresenceIcon(Presence presence){
        if(presence.getType().equals(Presence.Type.available)){
            if(presence.getMode()==null|| presence.getMode().equals(Presence.Mode.available)){
                return getOnline();
            }
        }
        return getOffline();
    }
}
