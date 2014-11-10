package com.comunication.interceptor;

import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2014/10/31.
 */
public class MessageHandleManager {
    private static List<MessagePacketHandle> messagePacketHandles = new ArrayList<>();
//    private static List<MessageInterceptorHandle> messageInterceptorHandles = new ArrayList<>();


    public static void addMessagePackHandle(MessagePacketHandle messagePacketHandle){
        if(messagePacketHandle == null)return;
        messagePacketHandles.add(messagePacketHandle);
    }

    public static void removeMessagePackHandle(MessagePacketHandle messagePacketHandle){
        if(messagePacketHandle == null)return;
        messagePacketHandles.remove(messagePacketHandle);
    }

    public static List<MessagePacketHandle> getMessagePacketHandles() {
        return messagePacketHandles;
    }
}
