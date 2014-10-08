package session;

import com.component.rosterTree.ContactItem;
import org.jivesoftware.smack.packet.Message;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * Created by a on 2014/8/22.
 */
public class SessionFrame extends JFrame {
    private MainPane mainPane;
    private Message.Type type;
    private static final HashMap<String,SessionFrame> sessionFrameHashMap = new HashMap<>();
    private ContactItem contact;
    /**
     *
     * @param contact
     */
    public static SessionFrame CreateAndShowSessionFrame(ContactItem contact){
        SessionFrame sessionFrame = sessionFrameHashMap.get(contact.getJid());
        if(sessionFrame == null){
            sessionFrame = new SessionFrame(contact, Message.Type.chat);
            sessionFrameHashMap.put(contact.getJid(),sessionFrame);
        }
        sessionFrame.setVisible(true);
        return sessionFrame;
    }

    private SessionFrame(ContactItem contact,Message.Type type) throws HeadlessException {
        setSize(475, 500);
        this.type = type;
        this.contact = contact;
        mainPane = new MainPane(this);
        setContentPane(mainPane);
        setTitle(contact.getUserName());
        setResizable(false);
    }

    public ContactItem getContact() {
        return contact;
    }

    //    public Message.Type getTypes() {
//        return type;
////    }


//
//    public void insertMessageToDisplay(SessionMessage sessionMessage){
//        String html = new ChatMessageContentHtml(sessionMessage).getContentHtml();
//        mainPane.chatDisplayPane.insertMessage(html);
//    }
}
