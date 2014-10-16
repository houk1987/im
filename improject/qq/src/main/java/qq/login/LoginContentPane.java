package qq.login;

import com.component.ExtendPane;
import com.component.FontFactory;
import com.component.ImageUtils;
import com.component.jlabel.JLabelFactory;
import com.resource.ConfigurationRes;
import org.smackservice.SmackConnection;
import qq.manager.QQManager;
import qq.ui.JTextField.JTextFieldFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by lenovo on 2014/10/16.
 */
class LoginContentPane extends ExtendPane implements ActionListener{
    private LoginDialogButtonFactory buttonFactory;
    private JLabel registerLinkLabel; //ע��ĳ�����
    private JTextField accountTextField; //�˺������
    private JPasswordField passwordField;//���������
    private JButton loginButton; //��½��ť
    private JButton closeWindowButton; //�رմ��ڵİ�ť
    private JButton minWindowButton; //��С�����ڰ�ť
    private LoginDialog loginDialog;  //��½����
    private Font font = new Font("΢���ź�", Font.PLAIN, 14);  //����

    LoginContentPane(LoginDialog loginDialog) {
        super(null, ImageUtils.getInstance(ConfigurationRes.getImageResPath() + "login/").getImageIcon("loginFrameBg.png"));
        this.loginDialog = loginDialog;
        this.buttonFactory = new LoginDialogButtonFactory();

        /**
         * ����˺����������
         */
        this.addAccountTextFiled();
        this.addPasswordTextFiled();

        /**
         * ��Ӱ�ť
         */
        this.addCloseWindowButton();
        this.addMinWindowButton();
        this.addLoginButton();

        /**
         * ���ע�ᳬ����
         */
        this.addRegisterLink();
    }

    /**
     * ����˺������ı���
     */
    private void addAccountTextFiled(){
        accountTextField = JTextFieldFactory.createJTextField(170, 23, Color.BLACK);
        accountTextField.setFont(font);
        accountTextField.setLocation(140, 200);
        add(accountTextField);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                accountTextField.requestFocus();
            }
        });
    }

    /**
     * ������������ı���
     */
    private void addPasswordTextFiled(){
        passwordField = JTextFieldFactory.createJPasswordField(165, 23, Color.BLACK, '��');
        passwordField.setFont(font);
        passwordField.setLocation(140, 225);
        add(passwordField);
    }

    /**
     * ��Ӵ��ڹرհ�ť
     */
    private void addCloseWindowButton(){
        closeWindowButton =buttonFactory.createCloseWindowButton();
        addButton(closeWindowButton);
    }

    /**
     * ��Ӵ�����С����ť
     */
    private void addMinWindowButton(){
        minWindowButton = buttonFactory.createMinWindowButton();
        minWindowButton.setLocation(this.getWidth()-closeWindowButton.getWidth()-minWindowButton.getWidth(),0);
        addButton(minWindowButton);
    }

    /**
     * ��ӵ�½��ť
     */
    private void addLoginButton(){
        loginButton = buttonFactory.createLoginButton();
        loginButton.setLocation(133, 287);
        addButton(loginButton);
        loginDialog.getRootPane().setDefaultButton(loginButton);
    }

    /**
     * ��ӵ����
     * ������Ӽ���
     * @param jButton
     */
    private void addButton(JButton jButton){
        add(jButton);
        jButton.addActionListener(this);  //����¼�����
    }

    private void addRegisterLink(){
        registerLinkLabel = JLabelFactory.createLinkLabel("ע���ʺ�", FontFactory.createFont("΢���ź�", 12), "#66a1e3", "http://" + SmackConnection.getInstance().getHost() + ":9090/plugins/userservice/qqRegister.htm");
        registerLinkLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerLinkLabel.setSize(100, 17);
        registerLinkLabel.setLocation(337, 200);
        add(registerLinkLabel);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(closeWindowButton)){
            loginDialog.dispose();
            System.exit(0);
        }else if(e.getSource().equals(minWindowButton)){
            loginDialog.setVisible(false);
        }else if(e.getSource().equals(loginButton)){
            String account = accountTextField.getText();
            String password = String.valueOf(passwordField.getPassword());
            //LoginManager.getInstance().loginClient(account,password);
            loginDialog.dispose();
            loginDialog = null;
            QQManager.getInstance().createAndShowMainDialog();
        }
    }
}
