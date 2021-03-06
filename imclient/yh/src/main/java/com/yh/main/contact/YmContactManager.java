package com.yh.main.contact;

import com.imService.connection.ImConnection;
import com.imService.contact.Contact;
import com.imService.contact.ContactManager;
import com.imService.presence.OnLine;
import com.yh.manager.LoginManager;
import com.yh.manager.YhManager;
import com.yh.presence.YhPresence;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by a on 2014/9/5.
 */
public class YmContactManager extends ContactManager {

    public YmContactManager(ImConnection imConnection) {
        super(imConnection);
    }

    /**
     * 胡群殴的所以的好友
     * @return
     */
    @Override
    public List<Contact> getAllContact() {
        List<Contact> contacts = new ArrayList<>();
        ImageIcon headIcon = new ImageIcon("res/main/headItem.png");
        //默认添加自己
        YhContact m = new YhContact(YhManager.getInstance().getLoginAccount(),YhManager.getInstance().getLoginAccount(),headIcon);
        Presence ownerpresence = YhManager.getInstance().getImConnection().getContactPresence(YhManager.getInstance().getLoginAccount());
        m.setPresenceType(new OnLine("",YhPresence.getPresenceTypeIcon(ownerpresence), YhManager.getInstance().getImConnection()));
        contacts.add(m);
        for(RosterEntry entry : rosterEntryList){
            YhContact yhContact = new YhContact(entry.getUser(),entry.getUser(),headIcon);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Presence presence = YhManager.getInstance().getImConnection().getContactPresence(entry.getUser());
            yhContact.setPresenceType(new OnLine("", YhPresence.getPresenceTypeIcon(presence), YhManager.getInstance().getImConnection()));
            contacts.add(yhContact);
        }
        return contacts;
    }

    public void  applyNewContact(String applyAccount){
        Message message = new Message();
        message.setFrom(YhManager.getInstance().getLoginAccount());
        message.setTo(applyAccount);
        message.setBody(YhManager.getInstance().getLoginAccount() + "申请您为好友!");
        message.setSubject("好友申請");
        //YhClient.getInstance().getSession(applyAccount, Message.Type.chat).sendMessage(message);
    }
}
