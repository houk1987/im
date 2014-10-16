package qq.ui.session;


import org.jivesoftware.smack.packet.Message;
import qq.main.tree.QQContactItem;
import qq.ui.window.PubFrame;

import java.awt.*;
import java.util.HashMap;


/**
 * Created by a on 2014/8/22.
 */
public class SessionFrame extends PubFrame {
    private SessionMainPane mainPane;
    private static final HashMap<String, SessionFrame> sessionFrameHashMap = new HashMap<>();
    private QQContactItem contact;

    public static void main(String[] args) {
        QQContactItem qqContactItem = new QQContactItem("","");
        SessionFrame.CreateAndShowSessionFrame(qqContactItem);
    }


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
        sessionFrame.setVisible(true);
        return sessionFrame;
    }

    private SessionFrame(QQContactItem contact) throws HeadlessException {
        this.contact = contact;
        mainPane = new SessionMainPane(this);
        setContentPane(mainPane);
        setTitle(contact.getUserName());
        //setIconImage(YhImageRes.getImageIcon("im.png").getImage());
        setSize(582, 505);
        setResizable(false);
    }

    public QQContactItem getContact() {
        return contact;
    }

    public void insertMessageToDisplay(Message message){
        mainPane.insertMessage(message);
    }
}
