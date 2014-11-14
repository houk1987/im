package session;
import com.ui.rosterTree.ContactItem;
import images.CommonImagesFactroy;
import org.jivesoftware.smack.packet.Message;
import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Created by a on 2014/8/22.
 */
public class SessionFrame extends JFrame {
    private MainPane mainPane;
    private static final HashMap<String,SessionFrame> sessionFrameHashMap = new HashMap<>();
    private ContactItem contact;

    /**
     *
     * @param contact
     */
    public static SessionFrame CreateAndShowSessionFrame(ContactItem contact){
        SessionFrame sessionFrame = sessionFrameHashMap.get(contact.getJid());
        if(sessionFrame == null){
            sessionFrame = new SessionFrame(contact);
            sessionFrameHashMap.put(contact.getJid(),sessionFrame);
        }else{
            sessionFrame.mainPane.updatePresence();
        }
        sessionFrame.setVisible(true);
        return sessionFrame;
    }

    private SessionFrame(ContactItem contact) throws HeadlessException {
        setSize(475, 600);
        this.contact = contact;
        mainPane = new MainPane(this);
        setContentPane(mainPane);
        setTitle(contact.getUserName());
        setIconImage(CommonImagesFactroy.createIm().getImage());
        setResizable(false);
    }

    public ContactItem getContact() {
        return contact;
    }

    public void insertMessageToDisplay(Message message){
        mainPane.insertMessage(message);
    }
}
