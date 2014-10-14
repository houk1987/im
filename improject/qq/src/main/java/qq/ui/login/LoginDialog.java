package qq.ui.login;


import com.component.ExtendPane;
import com.component.FontFactory;
import com.component.ImageUtils;
import com.component.jlabel.JLabelFactory;
import org.smackservice.SmackConnection;
import qq.manager.LoginManager;
import qq.manager.QQManager;
import qq.ui.JTextField.JTextFieldFactory;
import qq.ui.button.QqButtonFactory;
import qq.ui.window.PubDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by lenovo on 2014/9/16.
 */
public class LoginDialog extends PubDialog {

    private static LoginDialog loginDialog;
    private ContentPane contentPane;

    public static LoginDialog getInstance() {
        if(loginDialog == null){
            loginDialog = new LoginDialog();
        }
        return loginDialog;
    }
    /**
     * ���캯��
     * ����qq ��½����
     */
    public LoginDialog() {
        contentPane = new ContentPane();
        setContentPane(contentPane);//�����������
        setSize(429,329);//��СΪ��������С
        setLocationRelativeTo(null); //������ʾ
        getRootPane().setDefaultButton(contentPane.loginButton); //��Enter��ִ�е�½��ť
    }

    @Override
    public void requestFocus() {
        super.requestFocus();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                contentPane.accountTextField.requestFocus();
            }
        });
    }

    class ContentPane extends ExtendPane implements ActionListener{
        private JLabel registerLinkLabel; //ע��ĳ�����
        private JTextField accountTextField; //�˺������
        private JPasswordField passwordField;//���������
        private JButton loginButton; //��½��ť
        private JButton closeWindowButton; //�رմ��ڵİ�ť
        private JButton minWindowButton; //��С�����ڰ�ť

        ContentPane() {
            super(null, ImageUtils.getImageIcon("loginFrameBg.png"));
            initComponent();
        }

        /**
         * ��ʼ�����
         */
        private void initComponent() {
            //��ӹرհ�ť
            closeWindowButton = QqButtonFactory.getInstance().createCloseWindowButton();
            closeWindowButton.setLocation(this.getWidth()-closeWindowButton.getWidth(),0);
            add(closeWindowButton);
            closeWindowButton.addActionListener(this);

            //�����С�����ڰ�ť
            minWindowButton = QqButtonFactory.getInstance().createMinWindowButton();
            minWindowButton.setLocation(this.getWidth()-closeWindowButton.getWidth()-minWindowButton.getWidth(),0);
            add(minWindowButton);
            minWindowButton.addActionListener(this);

            /**
             * ע�ᳬ����
             */
            registerLinkLabel = JLabelFactory.createLinkLabel("ע���ʺ�", FontFactory.createFont("΢���ź�", 12), "#66a1e3", "http://" + SmackConnection.getInstance().getHost() + ":9090/plugins/userservice/qqRegister.htm");
            registerLinkLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            registerLinkLabel.setSize(100, 17);
            registerLinkLabel.setLocation(337, 200);
            add(registerLinkLabel);

            /**
             * �ʺ������
             */
            Font font = new Font("΢���ź�", Font.PLAIN, 14);
            accountTextField = JTextFieldFactory.createJTextField(170, 23, Color.BLACK);
            accountTextField.setFont(font);
            accountTextField.setLocation(140, 200);
            add(accountTextField);


            /**
             * ���������
             */
            passwordField = JTextFieldFactory.createJPasswordField(165, 23, Color.BLACK, '��');
            passwordField.setFont(font);
            passwordField.setLocation(140, 225);
            add(passwordField);

            /**
             * ��½��ť
             */
            loginButton = QqButtonFactory.getInstance().createLoginButton();
            loginButton.setLocation(133, 287);
            add(loginButton);
            loginButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String account = accountTextField.getText();
                        String password = String.valueOf(passwordField.getPassword());
                        LoginManager.getInstance().loginClient(account,password);
                        dispose();
                        loginDialog = null;
                        QQManager.getInstance().createAndShowMainDialog();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(closeWindowButton)){
                dispose();
                System.exit(0);
            }else if(e.getSource().equals(minWindowButton)){
                LoginDialog.this.setVisible(false);
            }
        }
    }
}
