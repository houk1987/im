package com.comunication.chat;

import com.comunication.connection.CommunicationConnection;
import com.comunication.connection.ConnectionManager;
import org.apache.log4j.Logger;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

import java.util.Properties;

/**
 * Created by hq on 2014/11/8.
 */
public class SingleChat implements ChatOperate {
    private Logger logger = Logger.getLogger(SingleChat.class);
    private CommunicationConnection connection = ConnectionManager.getConnection();

    @Override
    public Message sendChatMessage(String chatJid, String messageContent,Properties properties) throws XMPPException,NullPointerException {
        Message message = ChatMessageFactory.createSingleChatMessage(connection.getFullUserName(), chatJid, messageContent);
        sendChatMessage(message);
        return message;
    }

    @Override
    public Message sendChatMessage(Message message) throws XMPPException,NullPointerException {
        if (message != null) {
            Chat chat = createChat(message.getTo());
            if (chat == null) {
                logger.error("会话为空,发送失败");
                throw new NullPointerException("会话为空");
            }
            chat.sendMessage(message);
            return message;
        } else {
            logger.error("会话消息为空，发送失败");
            throw new ChatMessageIsNullException("会话消息为空");
        }
    }

    private Chat createChat(String chatJid) {
        Chat chat = connection.getChatManager().getThreadChat(chatJid);
        if (chat == null) {
            chat = connection.getChatManager().createChat(chatJid, null);
        }
        return chat;
    }
}
