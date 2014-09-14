package com.yh.presence;

import com.imService.presence.*;
import com.yh.manager.YhManager;
import org.jivesoftware.smack.packet.Presence;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by a on 2014/9/5.
 */
public class YhPresence  {

    public enum YhPresenceType{
        ONLINE,
        BUSY,
        HIDE;
    }

    private static OnLine onLine = new OnLine("���ڿ�",createImage("online.png"), YhManager.getInstance().getImConnection());
    private static Busy busy = new Busy("æµ��",createImage("busy.png"),YhManager.getInstance().getImConnection());
    private static Hide hide = new Hide("������������",createImage("offline.png"),YhManager.getInstance().getImConnection());

    public static List<PresenceType> getPresenceTypeList() {
        List<PresenceType> presenceTypes = new ArrayList<>();
        presenceTypes.add(onLine);
        presenceTypes.add(busy);
        presenceTypes.add(hide);
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
            case BUSY:
                presenceType = busy;
                break;
            case HIDE:
                presenceType = hide;
                break;
        }
        return presenceType;
    }

    public static ImageIcon getPresenceTypeIcon(Presence presence){
        if(presence.isAvailable()){ //���п�
            return getPresenceTypeIcon(YhPresenceType.ONLINE);
        }else if(presence.isAvailable() && presence.getMode().equals(Presence.Mode.dnd)){  //æµ
            return getPresenceTypeIcon(YhPresenceType.BUSY);
        }else { //����
            return getPresenceTypeIcon(YhPresenceType.HIDE);
        }
    }

    public static ImageIcon getPresenceTypeIcon(YhPresenceType yhPresenceType){
        PresenceType presenceType  = getPresenceType(yhPresenceType);
        return presenceType.getPresenceTypeIcon();
    }
}
