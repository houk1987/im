package com.qq.interceptor;

import com.comunication.interceptor.MessageHandleManager;
import com.comunication.interceptor.MessagePacketHandle;
import com.qq.chat.ChatWindow;
import com.qq.chat.ChatWindowManager;
import org.apache.log4j.Logger;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;

/**
 * Created by lenovo on 2014/10/31.
 */
public class ChatMessageHandler implements MessagePacketHandle{

    private Logger logger = Logger.getLogger(ChatMessageHandler.class);

    public ChatMessageHandler() {

    }

    @Override
    public void messageHandel(Packet packet) {
        if(packet instanceof Message){
            Message message = (Message)packet;
            Message.Type type = message.getType();  //��ȡ��Ϣ����
            if(type.equals(Message.Type.chat)){  //���˻Ự
                logger.info("���˻Ự��Ϣ����");
                logger.info(message.getFrom());
                String jid = message.getFrom().split("/")[0];
                logger.info("from:"+jid);
                ChatWindowManager.openChatWindow(jid);
                ChatWindow chatWindow = ChatWindowManager.getChatWindow(jid);
                chatWindow.loadAcceptMessage(message);
            }
        }
    }
}
