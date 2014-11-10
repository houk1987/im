package com.qq.login;

import com.qq.button.*;
import com.qq.button.Button;
import com.ui.image.ImageUtils;
import com.ui.pane.ExtendPane;
import com.ui.textField.JTextFieldFactory;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by lenovo on 2014/10/16.
 */
class LoginContentPane extends ExtendPane implements ActionListener{
    private JLabel registerLinkLabel; //注册的超链接
    private JTextField accountTextField; //账号输入框
    private JPasswordField passwordField;//密码输入框
    private JButton loginButton; //登陆按钮
    private JButton closeWindowButton; //关闭窗口的按钮
    private JButton minWindowButton; //最小化窗口按钮
    private LoginDialog loginDialog;  //登陆窗口
    private Font font = new Font("微软雅黑", Font.PLAIN, 14);  //字体
    private com.qq.button.Button button;

    LoginContentPane(LoginDialog loginDialog) {
        super(null, ImageUtils.getInstance("resources/images/login/").getImageIcon("loginFrameBg.png"));
        this.loginDialog = loginDialog;
        button = new Button(new LoginButtonFactory());

        /**
         * 添加账号密码输入框
         */
        this.addAccountTextFiled();
        this.addPasswordTextFiled();

        /**
         * 添加按钮
         */
        this.addCloseWindowButton();
        this.addMinWindowButton();
        this.addLoginButton();

        /**
         * 添加注册超链接
         */
        this.addRegisterLink();
    }

    /**
     * 添加账号输入文本框
     */
    private void addAccountTextFiled(){
        accountTextField = JTextFieldFactory.createJTextField(170, 23);
        accountTextField.setFont(font);
        accountTextField.setLocation(140, 200);
        add(accountTextField);
        Document document = accountTextField.getDocument();
        document.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
               accountTextField.requestFocus();
            }
        });
    }

    /**
     * 添加密码输入文本框
     */
    private void addPasswordTextFiled(){
        passwordField = JTextFieldFactory.createJPasswordField(165, 23,'●');
        passwordField.setFont(font);
        passwordField.setLocation(140, 225);
        Document document = passwordField.getDocument();
         document.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
//                if(passwordBalloonTip!=null && passwordBalloonTip.isVisible()){
//                    passwordBalloonTip.setVisible(false);
//                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                accountTextField.requestFocus();
            }
        });
        add(passwordField);
    }

    /**
     * 添加窗口关闭按钮
     */
    private void addCloseWindowButton(){
        closeWindowButton =button.createButton(LoginButtonFactory.CLOSE_WINDOW);
        closeWindowButton.setLocation(this.getWidth() - closeWindowButton.getWidth(), 0);
        addButton(closeWindowButton);
    }

    /**
     * 添加窗口最小化按钮
     */
    private void addMinWindowButton(){
        minWindowButton = button.createButton(LoginButtonFactory.MIN_WINDOW);
        minWindowButton.setLocation(this.getWidth()-closeWindowButton.getWidth()-minWindowButton.getWidth(),0);
        addButton(minWindowButton);
    }

    /**
     * 添加登陆按钮
     */
    private void addLoginButton(){
        loginButton =  button.createButton(LoginButtonFactory.LOGIN);
        loginButton.setLocation(133, 287);
        addButton(loginButton);
        loginDialog.getRootPane().setDefaultButton(loginButton);
    }

    /**
     * 添加到面板
     * 并且添加监听
     * @param jButton
     */
    private void addButton(JButton jButton){
        add(jButton);
        jButton.addActionListener(this);  //添加事件监听
    }

    private void addRegisterLink(){
//        registerLinkLabel = JLabelFactory.createLinkLabel("注册帐号", FontFactory.createFont("微软雅黑", 12), "#66a1e3", "http://" + SmackConnection.getInstance().getHost() + ":9090/plugins/userservice/qqRegister.htm");
//        registerLinkLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        registerLinkLabel.setSize(100, 17);
//        registerLinkLabel.setLocation(337, 200);
//        add(registerLinkLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(closeWindowButton)){
            LoginManager.close();
        }else if(e.getSource().equals(minWindowButton)){
            loginDialog.setVisible(false);
        }else if(e.getSource().equals(loginButton)){
            try {
                String userName = accountTextField.getText().trim();
                String password = String.valueOf(passwordField.getPassword()).trim();
                if(userName.length() == 0){
                    accountTextField.requestFocus();
                    JOptionPane.showMessageDialog(this,"请输入帐号再进行登录！");
                }else if(password.length() == 0){
                    passwordField.requestFocus();
                    JOptionPane.showMessageDialog(this,"请输入密码再进行登录！");
                }else{
                    LoginManager.login(userName,password);
                }
            } catch (Exception e1) {
                e1.printStackTrace();
                accountTextField.requestFocus();
                JOptionPane.showMessageDialog(this,"账号或密码错误！");
            }
        }
    }
}
