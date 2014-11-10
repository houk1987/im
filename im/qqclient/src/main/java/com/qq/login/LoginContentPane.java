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
    private JLabel registerLinkLabel; //ע��ĳ�����
    private JTextField accountTextField; //�˺������
    private JPasswordField passwordField;//���������
    private JButton loginButton; //��½��ť
    private JButton closeWindowButton; //�رմ��ڵİ�ť
    private JButton minWindowButton; //��С�����ڰ�ť
    private LoginDialog loginDialog;  //��½����
    private Font font = new Font("΢���ź�", Font.PLAIN, 14);  //����
    private com.qq.button.Button button;

    LoginContentPane(LoginDialog loginDialog) {
        super(null, ImageUtils.getInstance("resources/images/login/").getImageIcon("loginFrameBg.png"));
        this.loginDialog = loginDialog;
        button = new Button(new LoginButtonFactory());

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
     * ������������ı���
     */
    private void addPasswordTextFiled(){
        passwordField = JTextFieldFactory.createJPasswordField(165, 23,'��');
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
     * ��Ӵ��ڹرհ�ť
     */
    private void addCloseWindowButton(){
        closeWindowButton =button.createButton(LoginButtonFactory.CLOSE_WINDOW);
        closeWindowButton.setLocation(this.getWidth() - closeWindowButton.getWidth(), 0);
        addButton(closeWindowButton);
    }

    /**
     * ��Ӵ�����С����ť
     */
    private void addMinWindowButton(){
        minWindowButton = button.createButton(LoginButtonFactory.MIN_WINDOW);
        minWindowButton.setLocation(this.getWidth()-closeWindowButton.getWidth()-minWindowButton.getWidth(),0);
        addButton(minWindowButton);
    }

    /**
     * ��ӵ�½��ť
     */
    private void addLoginButton(){
        loginButton =  button.createButton(LoginButtonFactory.LOGIN);
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
//        registerLinkLabel = JLabelFactory.createLinkLabel("ע���ʺ�", FontFactory.createFont("΢���ź�", 12), "#66a1e3", "http://" + SmackConnection.getInstance().getHost() + ":9090/plugins/userservice/qqRegister.htm");
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
                    JOptionPane.showMessageDialog(this,"�������ʺ��ٽ��е�¼��");
                }else if(password.length() == 0){
                    passwordField.requestFocus();
                    JOptionPane.showMessageDialog(this,"�����������ٽ��е�¼��");
                }else{
                    LoginManager.login(userName,password);
                }
            } catch (Exception e1) {
                e1.printStackTrace();
                accountTextField.requestFocus();
                JOptionPane.showMessageDialog(this,"�˺Ż��������");
            }
        }
    }
}
