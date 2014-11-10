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
    private JButton closeWindowButton; //关闭窗口的按钮
    private JButton minWindowButton; //最小化窗口按钮
    private JButton maxWindowButton; //最大化窗口按钮
    private SessionFrameButtonFactory sessionFrameButtonFactory;
    private QQContactItem contact;
    private SessionFrame sessionFrame;

    public SessionFrameContentPane(SessionFrame sessionFrame) {
        super(null, ImageUtils.getInstance("session/").getImageIcon("sessionFrameBg.png"));
        this.sessionFrame = sessionFrame;
        this.contact = sessionFrame.getContact();
        this.sessionFrameButtonFactory = new SessionFrameButtonFactory();

        //添加窗口右上角 最小化，关闭按钮  当前窗口不能最大化
        this.addCloseWindowButton();
        this.addMinWindowButton();

        //添加用户头像图标
        this.addUserNameLabel();
        this.addUserHeadIconLabel();

        //添加中间会话消息显示，显示输入的面板
        this.addChatDisplayPane();
        this.addChatWriterPane();

        //添加底部消息发送，关闭按钮
        this.addSendMessageButton();
        this.addCloseSessionButton();
    }

    /**
     * 添加用户姓名标签
     */
    private void addUserNameLabel() {
        final Font font = new Font("微软雅黑",Font.PLAIN,17);
        userNameLabel = JLabelFactory.createJLabel(contact.getUserName(), font, Color.BLACK);
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBounds(55, 3, 300, 21);
        panel.setBackground(new Color(234, 236, 248));
        panel.add(userNameLabel,BorderLayout.CENTER);
        add(panel);
    }

    /**
     * 添加用户头像席状态
     */
    private void addUserHeadIconLabel() {
        animation=new HeadPicture(ConfigurationRes.getPath()+"common/headItem1.png");
        animation.setLocation(6, 1);
        add(animation);
    }

    /**
     * 添加窗口关闭按钮
     */
    private void addCloseWindowButton() {
        closeWindowButton = sessionFrameButtonFactory.createCloseWindowButton();
        closeWindowButton.setLocation(this.getWidth() - closeWindowButton.getWidth(), 0);
        addButton(closeWindowButton);
    }

    /**
     * 添加窗口最小化按钮
     */
    private void addMinWindowButton() {
        minWindowButton = sessionFrameButtonFactory.createMinWindowButton();
        minWindowButton.setLocation(this.getWidth() - closeWindowButton.getWidth() - minWindowButton.getWidth()-minWindowButton.getWidth(), 0);
        addButton(minWindowButton);
    }

//    /**
//     * 添加窗口最大化按钮
//     */
//    private void addMaxWindowButton() {
//        maxWindowButton = sessionFrameButtonFactory.createMaxWindowButton();
//        maxWindowButton.setLocation(this.getWidth() - closeWindowButton.getWidth() - maxWindowButton.getWidth(), 0);
//        addButton(maxWindowButton);
//    }

    /**
     * 添加发送按钮
     */
    private void addSendMessageButton() {
        sendButton = sessionFrameButtonFactory.createSendButton();
        sendButton.setLocation(348, 474);
        addButton(sendButton);
    }

    /**
     * 添加关闭按钮
     */
    private void addCloseSessionButton() {
        closeButton = sessionFrameButtonFactory.createCloseSessionFrameButton();
        closeButton.setLocation(274, 474);
        addButton(closeButton);
    }

    /**
     * 添加到面板
     * 并且添加监听
     *
     * @param jButton
     */
    private void addButton(JButton jButton) {
        add(jButton);
        jButton.addActionListener(this);  //添加事件监听
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
            chatWritePane.clear();//清空输入文本
        } else if (e.getSource() == minWindowButton) {
            sessionFrame.setExtendedState(JFrame.ICONIFIED); //设置成图标化
        } else if (e.getSource() == sendButton) {
            try {
                Message message = getMessage(chatWritePane.getPlainText());
                QQClient.getInstance().getChatManager().sendChatMessage(message);
                chatDisplayPane.insertMessage(message.getBody());
                chatWritePane.clear();//清空输入文本
            } catch (XMPPException e1) {
                e1.printStackTrace();
            }
        }
    }
}
