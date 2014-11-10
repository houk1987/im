package com.comunication.interceptor;

import org.jivesoftware.smack.packet.Packet;

/**
 * Created by lenovo on 2014/10/31.
 */
public interface MessageInterceptorHandle {
    void messageInterceptHandel(Packet packet);   //拦截修改发送到服务器的操作
}
