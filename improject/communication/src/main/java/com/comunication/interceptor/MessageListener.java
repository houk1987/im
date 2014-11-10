package com.comunication.interceptor;

import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.packet.Packet;

/**
 * Created by lenovo on 2014/10/31.
 */
public class MessageListener implements PacketListener {
    @Override
    public void processPacket(Packet packet) {
        for(MessagePacketHandle messagePacketHandle : MessageHandleManager.getMessagePacketHandles()){
            messagePacketHandle.messageHandel(packet);
        }
    }
}
