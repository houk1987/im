package qq.session;

import com.component.ExtendPane;
import com.resource.ImageUtils;
import com.component.jlabel.JLabelFactory;
import com.component.session.ChatDisplayPane;
import com.component.session.ChatWritePanel;
import com.resource.ConfigurationRes;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import qq.lunch.QQClient;
import qq.main.tree.QQContactItem;
import qq.session.message.BasicHtml;
import qq.ui.headPicture.HeadPicture;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by a on 2014/8/20.
 */
public class SessionFrameContentPane extends ExtendPane implements ActionListener {
    ChatWritePanel chatWritePane;
    ChatDisplayPane chatDisplayPane;
    private HeadPicture animation;
    private JLabel userNameLabel;
    private JButton sendButton;
    private JButton closeButton;
    private JButton closeWindowButton; //�رմ��ڵİ�ť
    private JButton minWindowButton; //��С�����ڰ�ť
    private JButton maxWindowButton; //��󻯴��ڰ�ť
    private SessionFrameButtonFactory sessionFrameButtonFactory;
    private QQContactItem contact;
    private SessionFrame sessionFrame;

    public SessionFrameContentPane(SessionFrame sessionFrame) {
        super(null, ImageUtils.getInstance("session/").getImageIcon("sessionFrameBg.png"));
        this.sessionFrame = sessionFrame;
        this.contact = sessionFrame.getContact();
        this.sessionFrameButtonFactory = new SessionFrameButtonFactory();

        //��Ӵ������Ͻ� ��С�����رհ�ť  ��ǰ���ڲ������
        this.addCloseWindowButton();
        this.addMinWindowButton();

        //����û�ͷ��ͼ��
        this.addUserNameLabel();
        this.addUserHeadIconLabel();

        //����м�Ự��Ϣ��ʾ����ʾ��������
        this.addChatDisplayPane();
        this.addChatWriterPane();

        //��ӵײ���Ϣ���ͣ��رհ�ť
        this.addSendMessageButton();
        this.addCloseSessionButton();
    }

    /**
     * ����û�������ǩ
     */
    private void addUserNameLabel() {
        final Font font = new Font("΢���ź�",Font.PLAIN,17);
        userNameLabel = JLabelFactory.createJLabel(contact.getUserName(), font, Color.BLACK);
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBounds(55, 3, 300, 21);
        panel.setBackground(new Color(234, 236, 248));
        panel.add(userNameLabel,BorderLayout.CENTER);
        add(panel);
    }

    /**
     * ����û�ͷ��ϯ״̬
     */
    private void addUserHeadIconLabel() {
        animation=new HeadPicture(ConfigurationRes.getPath()+"common/headItem1.png");
        animation.setLocation(6, 1);
        add(animation);
    }

    /**
     * ��Ӵ��ڹرհ�ť
     */
    private void addCloseWindowButton() {
        closeWindowButton = sessionFrameButtonFactory.createCloseWindowButton();
        closeWindowButton.setLocation(this.getWidth() - closeWindowButton.getWidth(), 0);
        addButton(closeWindowButton);
    }

    /**
     * ��Ӵ�����С����ť
     */
    private void addMinWindowButton() {
        minWindowButton = sessionFrameButtonFactory.createMinWindowButton();
        minWindowButton.setLocation(this.getWidth() - closeWindowButton.getWidth() - minWindowButton.getWidth()-minWindowButton.getWidth(), 0);
        addButton(minWindowButton);
    }

//    /**
//     * ��Ӵ�����󻯰�ť
//     */
//    private void addMaxWindowButton() {
//        maxWindowButton = sessionFrameButtonFactory.createMaxWindowButton();
//        maxWindowButton.setLocation(this.getWidth() - closeWindowButton.getWidth() - maxWindowButton.getWidth(), 0);
//        addButton(maxWindowButton);
//    }

    /**
     * ��ӷ��Ͱ�ť
     */
    private void addSendMessageButton() {
        sendButton = sessionFrameButtonFactory.createSendButton();
        sendButton.setLocation(348, 474);
        addButton(sendButton);
    }

    /**
     * ��ӹرհ�ť
     */
    private void addCloseSessionButton() {
        closeButton = sessionFrameButtonFactory.createCloseSessionFrameButton();
        closeButton.setLocation(274, 474);
        addButton(closeButton);
    }

    /**
     * ��ӵ����
     * ������Ӽ���
     *
     * @param jButton
     */
    private void addButton(JButton jButton) {
        add(jButton);
        jButton.addActionListener(this);  //����¼�����
    }

    private void addChatDisplayPane() {
        chatDisplayPane = new ChatDisplayPane();
        chatDisplayPane.setBounds(1, 81, 443, 287);
        add(chatDisplayPane);
    }

    private void addChatWriterPane() {
        chatWritePane = new ChatWritePanel(this);
        chatWritePane.setBounds(3, 400, 443, 70);
        add(chatWritePane);
    }


    private Message getMessage(String content) {
        Message message = new Message();
        message.setFrom(QQClient.getInstance().getLoginUserNameWithDomain());
        message.setTo(contact.getJid());
        message.setProperty("sendTime", new Timestamp(System.currentTimeMillis()));
        message.setBody(getContentHtml(content, message));
        return message;
    }

    private String getContentHtml(String content, Message message) {
        String html = BasicHtml.outBasicHtml();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(message.getProperty("sendTime"));
        html = html
                .replaceAll("#algin#", "left")
                .replaceAll("#msgtip#", QQClient.getInstance().getNickName() + "  " + dateString)
                .replaceAll("#content#", content);
        return html;
    }

    public void insertMessage(Message message) {
        chatDisplayPane.insertMessage(message.getBody());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == maxWindowButton) {
//            sessionFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        }

        if (e.getSource() == closeButton || e.getSource() == closeWindowButton) {
            sessionFrame.dispose();
            chatWritePane.clear();//��������ı�
        } else if (e.getSource() == minWindowButton) {
            sessionFrame.setExtendedState(JFrame.ICONIFIED); //���ó�ͼ�껯
        } else if (e.getSource() == sendButton) {
            try {
                Message message = getMessage(chatWritePane.getPlainText());
                QQClient.getInstance().getChatManager().sendChatMessage(message);
                chatDisplayPane.insertMessage(message.getBody());
                chatWritePane.clear();//��������ı�
            } catch (XMPPException e1) {
                e1.printStackTrace();
            }
        }
    }
}
