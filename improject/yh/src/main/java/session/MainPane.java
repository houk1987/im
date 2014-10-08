package session;

import com.component.jlabel.JLabelFactory;
import com.component.rosterTree.ContactItem;
import com.component.session.ChatDisplayPane;
import com.ui.MainFrame;
import com.ui.button.YhButtonFactory;
import com.ui.resource.YhImageRes;
import mangager.PresenceManager;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Presence;
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
        //this.contact = sessionFrame.getContact();
        initComponent();
        //layoutComponent();
    }

    private void initComponent() {
        setLayout(null);
        setBackground(new Color(119, 36, 111));

        JLabel label = JLabelFactory.createJLabel(toolBarBgImageIcon);
        label.setLocation(0, 0);
        add(label);


//        Presence type = PresenceManager.getPresence("2@30san");
        statusIcon = JLabelFactory.createJLabel(PresenceManager.getOnline());
        statusIcon.setLocation(10, 80);
        add(statusIcon);

        //chatLabel = JLabelFactory.createJLabel(contact.getUserName(),null,Color.BLACK);

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
                System.out.println(chatWritePane.getPlainText());
                //  SessionMessage sessionMessage = chatWritePane.getSessionMessage();
//                Session session = YhClient.getInstance().getChatSession(contact.getJid());
//                sessionMessage.setSendTime(new Timestamp(System.currentTimeMillis()));
//                session.sendMessage(sessionMessage);
                chatDisplayPane.insertMessage(getContentHtml(chatWritePane.getPlainText()));
                chatWritePane.clear();//清空输入文本
            }
        });

        writePane.add(jButton, BorderLayout.EAST);

    }


    public String getContentHtml(String content) {
        String html = BasicHtml.getBasicHtml();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(new Timestamp(System.currentTimeMillis()));
        html = html
                .replaceAll("#algin#", "left")
                .replaceAll("#msgtip#", "asdas" + "  " + dateString)
                .replaceAll("#content#", content);
        return html;
    }


    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        MainPane mainPane = new MainPane(null);
        jFrame.setContentPane(mainPane);
        jFrame.setSize(483, 600);
        jFrame.setVisible(true);
        mainPane.chatWritePane.requestFocus();
    }
}
