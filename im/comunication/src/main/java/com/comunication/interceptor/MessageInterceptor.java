package com.comunication.interceptor;

import org.jivesoftware.smack.PacketInterceptor;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;

/**
 * Created by lenovo on 2014/10/31.
 */
public class MessageInterceptor implements PacketInterceptor {

    @Override
    public void interceptPacket(Packet packet) {
        if(packet instanceof Message){

        }
    }
}
