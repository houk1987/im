package com.comunication.chat;

import org.apache.log4j.Logger;
import org.jivesoftware.smack.packet.Message;

import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by hq on 2014/11/8.
 */
public class ChatMessageFactory {
    private static Logger logger = Logger.getLogger(ChatMessageFactory.class);

    public static Message createSingleChatMessage(String from, String to, String content) {
        return createBasicChatMessage(from, to, content);
    }

    public static Message createGroupChatMessage(String from, String to, String content,Properties properties) {
        Message message = createBasicChatMessage(from, to, content);
        if (message != null) {
            message.setType(Message.Type.groupchat);
            if(properties!=null) {
                Enumeration<?> e = properties.propertyNames();
                while (e.hasMoreElements()) {
                    String key = String.valueOf(e.nextElement());
                    message.setProperty(key,properties.getProperty(String.valueOf(key)));
                }
            }
        }
        return message;
    }

    /**
     * 通知在线的群组成员加入群组消息
     * @param from
     * @param to 群成员ID
     * @param groupChatJid 群号
     * @return 加入群的消息
     */
    public static Message createJoinGroupChatMessage(String from,String to,String groupChatJid){
        Message message = createBasicChatMessage(from, to, groupChatJid);
        if (message != null) {
            message.setType(Message.Type.normal);
            message.setProperty("type","joinGroupChat");
            message.setProperty("groupChatJid",groupChatJid);
        }
        return message;
    }

    private static Message createBasicChatMessage(String from, String to, String content) {
        if (to == null) {
            logger.error("会话chatJid为空");
            return null;
        }

        if (content == null || content.length() == 0) {
            logger.error("会话消息为空");
            return null;
        }
        Message message = new Message();
        message.setFrom(from);
        message.setTo(to);
        message.setBody(content);
        addMessageSendTime(message);
        return message;
    }


    private static void addMessageSendTime(Message message) {
        if (message == null) return;
        message.setProperty("sendTime", new Timestamp(System.currentTimeMillis()));
    }
}
