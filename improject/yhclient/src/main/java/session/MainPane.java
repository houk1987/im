package session;

import button.SessionButtonFactory;
import com.comunication.chat.ChatMessageFactory;
import com.comunication.chat.ChatMessageParser;
import com.comunication.chat.ChatOperate;
import com.comunication.chat.SingleChat;
import com.ui.chat.ChatDisplayPane;
import com.ui.chat.ChatWritePanel;
import com.ui.jlabel.JLabelFactory;
import com.ui.rosterTree.ContactItem;
import images.SessionImageFactory;
import mangager.PresenceManager;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by a on 2014/8/20.
 */
public class MainPane extends JPanel {
    ChatWritePanel chatWritePane;
    ChatDisplayPane chatDisplayPane;
    private JLabel statusIcon;
    private JLabel chatLabel;
    private JButton jButton;
    private ContactItem contact;
    private ImageIcon toolBarBgImageIcon = SessionImageFactory.getInstance().createToolbarBg();
    private ImageIcon toolBarBg2ImageIcon = SessionImageFactory.getInstance().createToolbarBg2();

    public MainPane(SessionFrame sessionFrame) {
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

        final JPanel writePane = new JPanel(new BorderLayout());
        writePane.setBackground(Color.WHITE);
        writePane.setBounds(5, 430, 457, 125);
        writePane.add(chatWritePane, BorderLayout.CENTER);
        add(writePane);

        jButton = SessionButtonFactory.getInstance().createSendMessageButton();
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    ChatOperate chatOperate = new SingleChat();
                try {
                    Message message = ChatMessageFactory.createSingleChatMessage(null,contact.getJid(),chatWritePane.getPlainText());
                    chatOperate.sendChatMessage(message);
                    chatDisplayPane.insertMessage(ChatMessageParser.getContentHtml(message));
                    chatWritePane.clear();//清空输入文本
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        writePane.add(jButton, BorderLayout.EAST);
    }

    public void insertMessage(Message message){
        chatDisplayPane.insertMessage(ChatMessageParser.getContentHtml(message));
    }

    public void updatePresence(){
        statusIcon.setIcon(PresenceManager.getPresenceIcon(PresenceManager.getPresence(contact.getJid())));
    }
}
