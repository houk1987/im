package qq.session;


import org.jivesoftware.smack.packet.Message;
import qq.main.tree.QQContactItem;
import qq.ui.window.PubFrame;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;


/**
 * Created by a on 2014/8/22.
 */
public class SessionFrame extends PubFrame {
    private SessionFrameContentPane sessionFrameContentPane;
    private static final HashMap<String, SessionFrame> sessionFrameHashMap = new HashMap<>();
    private QQContactItem contact;

    /**
     *
     * @param contact
     */
    public static SessionFrame CreateAndShowSessionFrame(QQContactItem contact){
        SessionFrame sessionFrame = sessionFrameHashMap.get(contact.getJid());
        if(sessionFrame == null){
            sessionFrame = new SessionFrame(contact);
            sessionFrameHashMap.put(contact.getJid(),sessionFrame);
        }else{
           // sessionFrame.mainPane.updatePresence();
        }
        sessionFrame.setLocationRelativeTo(null);
        sessionFrame.setVisible(true);
        return sessionFrame;
    }

    private SessionFrame(QQContactItem contact) throws HeadlessException {
        this.contact = contact;
        sessionFrameContentPane = new SessionFrameContentPane(this);
        setContentPane(sessionFrameContentPane);
        setTitle(contact.getUserName());
        setResizable(false);
        setSize(sessionFrameContentPane.getWidth(), sessionFrameContentPane.getHeight());
    }

    public QQContactItem getContact() {
        return contact;
    }

    public void insertMessageToDisplay(Message message){
        sessionFrameContentPane.insertMessage(message);
    }

    @Override
    public void setVisible(boolean b) {
        if(getExtendedState() == Frame.ICONIFIED){
            setExtendedState(JFrame.NORMAL);
        }
        super.setVisible(b);
    }
}
