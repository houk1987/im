package com.comunication.interceptor;

import org.jivesoftware.smack.packet.Packet;

/**
 * Created by lenovo on 2014/10/31.
 */
public interface MessagePacketHandle {
    void messageHandel(Packet packet); //处理消息操作
}
