package com.dt.interceptor;

import com.comunication.chat.GroupChatInfo;
import com.comunication.chat.GroupChatManager;
import com.comunication.interceptor.MessageHandleManager;
import com.comunication.interceptor.MessagePacketHandle;
import com.dt.chat.chat.ChatWindow;
import com.dt.chat.chat.ChatWindowManager;
import com.dt.chat.chat.GroupMessageAcceptWindow;
import com.dt.main.DtClientWindow;
import com.dt.start.StartClient;
import org.apache.log4j.Logger;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;

/**
 * Created by lenovo on 2014/10/31.
 */
public class ChatMessageHandler implements MessagePacketHandle {

    private Logger logger = Logger.getLogger(ChatMessageHandler.class);

    public ChatMessageHandler() {
        MessageHandleManager.addMessagePackHandle(this);
    }

    @Override
    public void messageHandel(Packet packet) {
        if (packet instanceof Message) {
            Message message = (Message) packet;
            Message.Type type = message.getType();  //获取消息类型
            if (type.equals(Message.Type.chat)) {  //两人会话
                logger.info("两人会话消息处理");
                System.out.println(message.getBody());
            } else if (type.equals(Message.Type.groupchat)) { //群组会话
                logger.info("群组会话消息处理");
                String groupMessageType = String.valueOf(message.getProperty("GroupMessageType"));
                System.out.println(message.getFrom());
                String jid = message.getFrom().split("/")[0];
                GroupChatInfo groupChatInfo = DtClientWindow.getInstance().getCustomTree().getGroupChatInfo(jid);
                if(groupChatInfo == null){
                    GroupMessageAcceptWindow.showGroupMessageAcceeptWindow(message);
                }else{
                    ChatWindow chatWindow = ChatWindowManager.openGroupChatWindow(groupChatInfo);
                    chatWindow.loadAcceptMessage(message);
                }

            }
        }
    }
}
