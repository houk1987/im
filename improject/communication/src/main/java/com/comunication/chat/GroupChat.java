package com.comunication.chat;

import com.comunication.connection.CommunicationConnection;
import com.comunication.connection.ConnectionManager;
import org.apache.log4j.Logger;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smackx.muc.MultiUserChat;

import java.util.Properties;

/**
 * Created by hq on 2014/11/8.
 */
public class GroupChat implements ChatOperate{
    private Logger logger = Logger.getLogger(ChatOperate.class);
    private CommunicationConnection connection = ConnectionManager.getConnection();

    @Override
    public Message sendChatMessage(String chatJid, String messageContent,Properties properties) throws XMPPException {
        Message message = ChatMessageFactory.createGroupChatMessage(null,chatJid,messageContent,properties);
        sendChatMessage(message);
        return message;
    }

    @Override
    public Message sendChatMessage(Message message) throws XMPPException {
        if (message != null) {
            MultiUserChat multiUserChat = GroupChatManager.joinGroupChatByGroupId(message.getTo());
            GroupChatManager.joinGroupChatByGroupId(message.getTo());
            if (multiUserChat == null) {
                logger.error("Ⱥ��ỰΪ��,����ʧ��");
                return null;
            }
            multiUserChat.sendMessage(message);
            return message;
        } else {
            logger.error("Ⱥ��Ự��ϢΪ�գ�����ʧ��");
            throw new ChatMessageIsNullException("�ỰΪ��");
        }
    }
}
