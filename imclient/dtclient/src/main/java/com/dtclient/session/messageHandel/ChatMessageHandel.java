package com.dtclient.session.messageHandel;

import com.dtclient.session.SessionFrame;
import com.imService.message.MessageHandel;
import com.imService.message.SessionMessage;

import org.jivesoftware.smack.packet.Message;

/**
 * Created by a on 2014/9/5.
 */
public class ChatMessageHandel implements MessageHandel {

    public ChatMessageHandel() {

    }

    @Override
    public void handel(Message message) {
        if(Message.Type.chat.equals(message.getType())){  //�����2�˻Ự
            //�����촰��
            //SessionFrame sessionFrame = SessionFrame.openSessionFrame(message.getFrom(),message.getFrom(),Message.Type.chat);
          //  SessionMessage sessionMessage = new SessionMessage(message);
           // sessionFrame.insertMessageToDisplay(sessionMessage);
        }
    }
}
