package qq.ui.login;

import com.qq.lanuch.QQClient;
import com.ui.JTextField.JTextFieldFactory;
import com.ui.button.ImageButtonFactory;
import com.ui.jlabel.JLabelFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by lenovo on 2014/9/16.
 */
public class LoginPane extends JPanel {

    private ImageIcon bgIcon;  //±³¾°Í¼Æ¬
    private JLabel registerLinkLabel; //×¢²áµÄ³¬Á´½Ó
    private JTextField accountTextField;
    private JPasswordField passwordField;
    private JButton loginButton; //µÇÂ½°´Å¥

    // private JLabel closeLabel;
    //GlassPanel glassPanel = new GlassPanel();
    private LoginDialog loginDialog;
    public LoginPane(LoginDialog loginDialog) {
        bgIcon = new ImageIcon("res/login/loginFrameBg.png"); //³õÊ¼»¯±³¾°Í¼Æ¬
        if (bgIcon == null) return;
        setSize(bgIcon.getIconWidth(), bgIcon.getIconHeight()); //ÉèÖÃÎª±³¾°Í¼Æ¬´óÐ¡
        this.loginDialog = loginDialog;
        initComponent();
    }

    /**
     * ³õÊ¼»¯×é¼þ
     */
    private void initComponent() {
        setLayout(null);
        /**
         * ×¢²á³¬Á´½Ó
         */
        registerLinkLabel = JLabelFactory.createJLabel(new ImageIcon("res/login/regeisterLink.png")); //×¢²áÕËºÅµÄ³¬Á´½Ó
        registerLinkLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerLinkLabel.setLocation(337, 200);
        add(registerLinkLabel);
        registerLinkLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //LoginHandel.getInstance().register(); //×¢²áÓÃ»§
            }
        });

        /**
         * ÕÊºÅÊäÈë¿ò
         */
        Font font = new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14);
        accountTextField = JTextFieldFactory.createJTextField(170, 23, Color.BLACK);
        accountTextField.setFont(font);
        accountTextField.setLocation(140, 200);
        add(accountTextField);

        /**
         * ÃÜÂëÊäÈë¿ò
         */
        passwordField = JTextFieldFactory.createJPasswordField(165, 23, Color.BLACK, '¡ñ');
        passwordField.setFont(font);
        passwordField.setLocation(140, 225);
        add(passwordField);

        /**
         * µÇÂ½°´Å¥
         */
        loginButton = ImageButtonFactory.createButton("res/button/login/", "", "login.png");
        loginButton.setLocation(133, 287);
        add(loginButton);
        loginButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String account = accountTextField.getText();
                    String password = String.valueOf(passwordField.getPassword());
                    if ("".equals(account)) {
                        // JOptionPane.showMessageDialog();
                    } else if ("".equals(password)) {

                    }
                    QQClient.getInstance().loginClient(account, password);
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(LoginPane.this,e1.getMessage());
                }
            }
        });
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (bgIcon == null) return;
        Image icon = bgIcon.getImage();
        g.drawImage(icon, 0, 0, icon.getWidth(this), icon.getHeight(this), this);
    }
}
