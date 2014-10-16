package qq.ui.session;


import com.component.ExtendPane;
import com.component.ImageUtils;
import com.component.session.ChatDisplayPane;
import com.component.session.ChatWritePanel;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.smackservice.ChatManager;
import qq.ui.button.QqButtonFactory;
import qq.ui.session.message.BasicHtml;
import qq.main.tree.QQContactItem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by a on 2014/8/20.
 */
public class SessionMainPane extends ExtendPane {
    ChatWritePanel chatWritePane;
    ChatDisplayPane chatDisplayPane;
    private JLabel statusIcon;
    private JLabel chatLabel;
    private JButton sendButton;
    private JButton closeButton;
    private QQContactItem contact;
    private ImageIcon sessionFrameBg;
    private SessionFrame sessionFrame;

    public SessionMainPane(SessionFrame sessionFrame) {
        super(null,ImageUtils.getImageIcon("sessionFramebg.png"));
        this.sessionFrame = sessionFrame;
        this.contact = sessionFrame.getContact();
        initComponent();
    }

    private void initComponent() {
//        chatLabel = JLabelFactory.createJLabel(contact.getUserName(),null,Color.BLACK);
//        chatLabel.setBounds(25, 75,100,21);
//        add(chatLabel);

        chatDisplayPane = new ChatDisplayPane();
        chatDisplayPane.setBounds(1, 81, 443, 287);
        add(chatDisplayPane);

        chatWritePane = new ChatWritePanel(this);
        chatWritePane.setBounds(1, 400, 443, 70);
        add(chatWritePane);

        closeButton = QqButtonFactory.getInstance().createSessionFrameCloseButton();
        closeButton.setLocation(274, 474);
        add(closeButton);

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sessionFrame.dispose();
            }
        });

        sendButton = QqButtonFactory.getInstance().createSendButton();
        sendButton.setLocation(348, 474);
        sendButton.addActionListener(new ActionListener() {
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
        add(sendButton);
    }

    private Message getMessage(String content){
        Message message = new Message();
        message.setFrom("1@30san");
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
        //statusIcon.setIcon(PresenceManager.getPresenceIcon(PresenceManager.getPresence(contact.getJid())));
    }
}
