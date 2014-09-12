package com.dtclient.main.presence;

import com.dtclient.lanuch.DtClient;
import com.imService.presence.*;
import org.jivesoftware.smack.packet.Presence;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by a on 2014/9/5.
 */
public class DtPresence {

    public enum YhPresenceType{
        ONLINE,
        AWAY,
        OFFLINE;
    }

    private static OnLine onLine = new OnLine("����",createImage("online.png"), DtClient.getInstance().getImConnection());
    private static Away away = new Away("�뿪",createImage("away.png"),DtClient.getInstance().getImConnection());
//    private static Busy busy = new Busy("æµ��",createImage("busy.png"),DtClient.getInstance().getImConnection());
    private static OffLine offLine = new OffLine("����",createImage("offline.png"),DtClient.getInstance().getImConnection());


    public static List<PresenceType> getPresenceTypeList() {
        List<PresenceType> presenceTypes = new ArrayList<>();
        presenceTypes.add(onLine);
        presenceTypes.add(away);
        presenceTypes.add(offLine);
        return presenceTypes;
    }

    private static ImageIcon createImage(String name){
        return new ImageIcon("res/status/"+name);
    }

    public static PresenceType getPresenceType(YhPresenceType yhPresenceType){
        PresenceType presenceType = null;
        switch (yhPresenceType){
            case ONLINE:
                presenceType = onLine;
                break;
            case AWAY:
                presenceType = away;
                break;
            case OFFLINE:
                presenceType = offLine;
                break;
        }
        return presenceType;
    }

    public static ImageIcon getPresenceTypeIcon(Presence presence){
        if(presence.isAvailable()){ //���п�
            return getPresenceTypeIcon(YhPresenceType.ONLINE);
        }else if(presence.isAvailable() && presence.getMode().equals(Presence.Mode.away)){  //æµ
            return getPresenceTypeIcon(YhPresenceType.AWAY);
        }else { //����
            return getPresenceTypeIcon(YhPresenceType.OFFLINE);
        }
    }

    public static ImageIcon getPresenceTypeIcon(YhPresenceType yhPresenceType){
        PresenceType presenceType  = getPresenceType(yhPresenceType);
        return presenceType.getPresenceTypeIcon();
    }
}
