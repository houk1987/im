package org.smackservice;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

/**
 * Created by 356 on 2014/10/8.
 */
public class ChatManager {

    private XMPPConnection xmppConnection;
    public ChatManager(XMPPConnection xmppConnection) {
        this.xmppConnection = xmppConnection;
    }

    public void sendChatMessage(Message message) throws XMPPException {
        final String jid = message.getTo();
        if(jid == null)return;
        Chat chat = xmppConnection.getChatManager().getThreadChat(jid);
        if(chat == null){
            chat = xmppConnection.getChatManager().createChat(jid,null);
        }
        message.setType(Message.Type.chat);
        chat.sendMessage(message);
    }


    public void sendGroupMessage(Message message ){

    }
}
