package session;

import com.component.jlabel.JLabelFactory;
import com.component.rosterTree.ContactItem;
import com.component.session.ChatDisplayPane;
import com.ui.MainFrame;
import com.ui.button.YhButtonFactory;
import com.ui.resource.YhImageRes;
import mangager.PresenceManager;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.smackservice.ChatManager;
import org.smackservice.SmackConnection;
import session.message.BasicHtml;
import sun.applet.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by a on 2014/8/20.
 */
public class MainPane extends JPanel {
    ChatWritePanel chatWritePane;
    ChatDisplayPane chatDisplayPane;
    private SessionFrame sessionFrame;
    private JLabel statusIcon;
    private JLabel chatLabel;
    private JButton jButton;
    private ContactItem contact;
    private ImageIcon toolBarBgImageIcon = YhImageRes.getImageIcon("toolbarBg.png");
    private ImageIcon toolBarBg2ImageIcon = YhImageRes.getImageIcon("toolbarBg2.png");

    public MainPane(SessionFrame sessionFrame) {
        this.sessionFrame = sessionFrame;
        this.contact = sessionFrame.getContact();
        initComponent();
    }

    private void initComponent() {
        setLayout(null);
        setBackground(new Color(119, 36, 111));

        JLabel label = JLabelFactory.createJLabel(toolBarBgImageIcon);
        label.setLocation(0, 0);
        add(label);


        Presence type = PresenceManager.getPresence(contact.getJid());
        statusIcon = JLabelFactory.createJLabel(PresenceManager.getPresenceIcon(type));
        statusIcon.setLocation(10, 80);
        add(statusIcon);

        chatLabel = JLabelFactory.createJLabel(contact.getUserName(),null,Color.BLACK);
        chatLabel.setBounds(25, 75,100,21);
        add(chatLabel);

        chatDisplayPane = new ChatDisplayPane();
        chatDisplayPane.setBounds(5, 100, 457, 300);
        add(chatDisplayPane);

        JLabel label2 = JLabelFactory.createJLabel(toolBarBg2ImageIcon);
        label2.setLocation(5, 400);
        add(label2);

        chatWritePane = new ChatWritePanel(this);

        JPanel writePane = new JPanel(new BorderLayout());
        writePane.setBackground(Color.WHITE);
        writePane.setBounds(5, 430, 457, 125);
        writePane.add(chatWritePane, BorderLayout.CENTER);
        add(writePane);

        jButton = YhButtonFactory.getInstance().createSendButton();
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Message message = getMessage(chatWritePane.getPlainText());
                    ChatManager.getInstance().sendChatMessage(message);
                    chatDisplayPane.insertMessage(message.getBody());
                    chatWritePane.clear();//清空输入文本
                } catch (XMPPException e1) {
                    e1.printStackTrace();
                }
            }
        });
        writePane.add(jButton, BorderLayout.EAST);
    }

    private Message getMessage(String content){
        Message message = new Message();
        message.setFrom(MainFrame.getInstance().getLoginUser());
        message.setTo(contact.getJid());
        message.setProperty("sendTime",new Timestamp(System.currentTimeMillis()));
        message.setBody(getContentHtml(content,message));
        return message;
    }

    private String getContentHtml(String content,Message message) {
        String html = BasicHtml.outBasicHtml();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(message.getProperty("sendTime"));
        html = html
                .replaceAll("#algin#", "left")
                .replaceAll("#msgtip#", message.getFrom() + "  " + dateString)
                .replaceAll("#content#", content);
        return html;
    }

    public void insertMessage(Message message){
        chatDisplayPane.insertMessage(message.getBody());
    }

    public void updatePresence(){
        statusIcon.setIcon(PresenceManager.getPresenceIcon(PresenceManager.getPresence(contact.getJid())));
    }
}
