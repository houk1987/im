package com.ui;


import com.component.FontFactory;
import com.component.jlabel.JLabelFactory;
import com.ui.button.YhButtonFactory;
import com.ui.jtextField.YhPasswordTextFiled;
import com.ui.jtextField.YhTextFiled;
import com.ui.notify.WarnNotifyDialog;
import com.ui.resource.Yh;
import com.ui.resource.YhImageRes;
import lister.YhPacketLister;
import lister.YhRosterListener;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.smackservice.SmackConnection;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by HK on 2014/10/4.
 */
public class LoginFrame extends JFrame {

    private ImageIcon loginFrameBg = YhImageRes.getImageIcon("loginFrameBg.png");
    private JButton loginButton;
    private JTextField accountTextField;
    private JPasswordField passwordField;
    private JLabel registerLinkLabel; //◊¢≤·

    public static void main(String[] args) {
        createAndShowLoginFrame();
    }

    public static void createAndShowLoginFrame() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                UIManager.put("TextField.font", new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 11));
                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setResizable(false);
                loginFrame.setVisible(true);
            }
        });
    }

    private LoginFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ContentPane contentPane = new ContentPane();
        setSize(contentPane.getWidth(), contentPane.getHeight() + 28);
        setContentPane(contentPane);
        getRootPane().setDefaultButton(loginButton);
        setIconImage(YhImageRes.getImageIcon("Yahoo!_Messenger_aero.png").getImage());
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);

    }

    class ContentPane extends JPanel {
        ContentPane() {
            if (loginFrameBg != null) {
                this.setSize(loginFrameBg.getIconWidth(), loginFrameBg.getIconHeight());
            }
            setLayout(null);

            //’À∫≈ ID  ‰»ÎøÚ
            accountTextField = new YhTextFiled();
            accountTextField.setBounds(47, 193, 150, 15);
            add(accountTextField);
            accountTextField.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    passwordField.setText("");   //–ﬁ∏ƒ’À∫≈±‰ªØ£¨‘Ú«Âø’√‹¬Î ‰»ÎøÚ
                }

                @Override
                public void removeUpdate(DocumentEvent e) {

                }

                @Override
                public void changedUpdate(DocumentEvent e) {

                }
            });

            //√‹¬Î ‰»ÎøÚ
            passwordField = new YhPasswordTextFiled();
            passwordField.setBounds(47, 239, 150, 15);
            passwordField.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 11));
            add(passwordField);

            //µ«¬º∞¥≈•
            loginButton = YhButtonFactory.getInstance().createLoginButton();
            loginButton.setLocation(80, 341);
            add(loginButton);
            loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String account = accountTextField.getText().trim();
                    String password = String.valueOf(passwordField.getPassword());
                    if (account.length() == 0) {
                        createAndShowWarnDialog();
                        return;
                    }
                    if (password.length() == 0) {
                        createAndShowWarnDialog();
                        return;
                    }
                    try {
                        loginSuccess(account, password);  //µ«¬Ω≥…π¶
                    } catch (XMPPException e1) {
                        e1.printStackTrace();
                    }
                }
            });
            Font font = FontFactory.createFont("ÀŒÃÂ", 12);
            //◊¢≤·¡¥Ω”
            registerLinkLabel = JLabelFactory.createLinkLabel("¡¢º¥‘]É‘…Í’à", font, "#66a1e3", "http://" + SmackConnection.getInstance().getHost() + ":9090/plugins/userservice/YahooRegister.htm");
            registerLinkLabel.setSize(100, 20);
            registerLinkLabel.setLocation(this.getWidth() / 2 - 81, this.getHeight() - 61);
            add(registerLinkLabel);
        }


        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Image image = loginFrameBg.getImage();
            g.drawImage(image, -2, 0, loginFrameBg.getIconWidth(), loginFrameBg.getIconHeight(), this);
        }
    }

    private void createAndShowWarnDialog() {
        WarnNotifyDialog warnNotifyDialog = new WarnNotifyDialog(this, "µ«»Î∞l…˙ÜñÓ}", true);
        warnNotifyDialog.setSize(223, 109);
        warnNotifyDialog.setVisible(true);
        accountTextField.requestFocus();
    }

    private void loginSuccess(String account, String password) throws XMPPException {
        SmackConnection.getInstance().connect();
        SmackConnection.getInstance().login(account, password);
        dispose();
        Yh.setLoginUser(account);
        MainFrame.getInstance().setVisible(true);
        MainFrame.getInstance().setLoginUser(account);
        SmackConnection.getInstance().addPacketListener(new YhPacketLister(), new PacketTypeFilter(Message.class));
        SmackConnection.getInstance().getRoster().addRosterListener(new YhRosterListener());
    }
}
