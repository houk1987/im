package login;
import button.LoginButtonFactory;
import com.comunication.connection.ConnectionManager;
import com.ui.jlabel.JLabelFactory;
import com.ui.textField.JTextFieldFactory;
import com.ui.tools.Tools;
import images.LoginImagesFactory;
import images.TrayImageFactory;
import lister.ConnectionHandle;
import lister.YhPacketLister;
import lister.YhRosterListener;
import main.MainFrame;
import org.jivesoftware.smack.XMPPException;
import tray.SysTrayManager;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by HK on 2014/10/4.
 */
public class LoginFrame extends JFrame {
    private ImageIcon loginFrameBg = LoginImagesFactory.createLoginFrameBg();
    private JButton loginButton = LoginButtonFactory.getInstance().createLoginButton();
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
                SysTrayManager.getInstance();
            }
        });
    }

    private LoginFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ContentPane contentPane = new ContentPane();
        setContentPane(contentPane);
        this.setSize(loginFrameBg.getIconWidth(), loginFrameBg.getIconHeight()+ 28);
        getRootPane().setDefaultButton(loginButton);
        Tools.setWindowLocationOnScreamRight(this);
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);

    }

    class ContentPane extends JPanel {
        ContentPane() {
            setLayout(null);
            //’À∫≈ ID  ‰»ÎøÚ
            accountTextField = JTextFieldFactory.createJTextField(150, 15);
            accountTextField.setForeground(Color.GRAY);
            accountTextField.setLocation(47, 193);
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
            passwordField = JTextFieldFactory.createJPasswordField(150, 15,'°Ò');
            passwordField.setForeground(Color.GRAY);
            passwordField.setLocation(47, 239);
            passwordField.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 11));
            add(passwordField);

            //µ«¬º∞¥≈•

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
            //◊¢≤·¡¥Ω”
            registerLinkLabel = JLabelFactory.createLinkLabel("¡¢º¥‘]É‘…Í’à", new Font("ÀŒÃÂ", Font.PLAIN,12), "#66a1e3", "http://" + ConnectionManager.getConnection().getHost() + ":9090/plugins/userservice/YahooRegister.htm");
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
        ConnectionManager.createConnection();
        ConnectionManager.login(account, password);
        new YhPacketLister();
        new ConnectionHandle();
        ConnectionManager.getConnection().getRoster().addRosterListener(new YhRosterListener());
        dispose();
        MainFrame.getInstance().setVisible(true);
        MainFrame.getInstance().setLoginUser(account);
        SysTrayManager.getInstance().setImTrayIcon(TrayImageFactory.createAfterLogin().getImage());
    }
}
