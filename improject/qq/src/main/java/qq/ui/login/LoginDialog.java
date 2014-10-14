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
     * 构造函数
     * 创建qq 登陆窗口
     */
    public LoginDialog() {
        contentPane = new ContentPane();
        setContentPane(contentPane);//设置内容面板
        setSize(429,329);//大小为内容面板大小
        setLocationRelativeTo(null); //居中显示
        getRootPane().setDefaultButton(contentPane.loginButton); //按Enter键执行登陆按钮
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
        private JLabel registerLinkLabel; //注册的超链接
        private JTextField accountTextField; //账号输入框
        private JPasswordField passwordField;//密码输入框
        private JButton loginButton; //登陆按钮
        private JButton closeWindowButton; //关闭窗口的按钮
        private JButton minWindowButton; //最小化窗口按钮

        ContentPane() {
            super(null, ImageUtils.getImageIcon("loginFrameBg.png"));
            initComponent();
        }

        /**
         * 初始化组件
         */
        private void initComponent() {
            //添加关闭按钮
            closeWindowButton = QqButtonFactory.getInstance().createCloseWindowButton();
            closeWindowButton.setLocation(this.getWidth()-closeWindowButton.getWidth(),0);
            add(closeWindowButton);
            closeWindowButton.addActionListener(this);

            //添加最小化窗口按钮
            minWindowButton = QqButtonFactory.getInstance().createMinWindowButton();
            minWindowButton.setLocation(this.getWidth()-closeWindowButton.getWidth()-minWindowButton.getWidth(),0);
            add(minWindowButton);
            minWindowButton.addActionListener(this);

            /**
             * 注册超链接
             */
            registerLinkLabel = JLabelFactory.createLinkLabel("注册帐号", FontFactory.createFont("微软雅黑", 12), "#66a1e3", "http://" + SmackConnection.getInstance().getHost() + ":9090/plugins/userservice/qqRegister.htm");
            registerLinkLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            registerLinkLabel.setSize(100, 17);
            registerLinkLabel.setLocation(337, 200);
            add(registerLinkLabel);

            /**
             * 帐号输入框
             */
            Font font = new Font("微软雅黑", Font.PLAIN, 14);
            accountTextField = JTextFieldFactory.createJTextField(170, 23, Color.BLACK);
            accountTextField.setFont(font);
            accountTextField.setLocation(140, 200);
            add(accountTextField);


            /**
             * 密码输入框
             */
            passwordField = JTextFieldFactory.createJPasswordField(165, 23, Color.BLACK, '●');
            passwordField.setFont(font);
            passwordField.setLocation(140, 225);
            add(passwordField);

            /**
             * 登陆按钮
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
