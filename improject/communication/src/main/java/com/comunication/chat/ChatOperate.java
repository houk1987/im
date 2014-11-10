package com.comunication.chat;

import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

import java.util.Properties;

/**
 * Created by hq on 2014/11/8.
 */
public interface ChatOperate {

    Message sendChatMessage(String chatJid, String messageContent,Properties properties) throws Exception;

    Message sendChatMessage(Message message) throws Exception;
}
