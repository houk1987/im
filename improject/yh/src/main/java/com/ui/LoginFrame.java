package com.ui;


import com.ui.button.YhButtonFactory;
import com.ui.jtextField.YhPasswordTextFiled;
import com.ui.jtextField.YhTextFiled;
import com.ui.notify.WarnNotifyDialog;
import com.ui.resource.YhImageRes;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by HK on 2014/10/4.
 */
public class LoginFrame extends JFrame {

    private ImageIcon loginFrameBg = YhImageRes.getImageIcon("loginFrameBg.png");
    private JButton loginButton;
    private JTextField accountTextField;
    private JPasswordField passwordField;

    public static void main(String[] args) {
        createAndShowLoginFrame();
    }

    private static void createAndShowLoginFrame() {
        UIManager.put("TextField.font", new Font("΢���ź�", Font.PLAIN, 11));
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setResizable(false);
        loginFrame.setVisible(true);
    }

    private LoginFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ContentPane contentPane = new ContentPane();
        setSize(contentPane.getWidth(), contentPane.getHeight() + 28);
        setContentPane(contentPane);
        getRootPane().setDefaultButton(loginButton);
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

            //�˺� ID �����
            accountTextField = new YhTextFiled();
            accountTextField.setBounds(47, 193, 150, 15);
            add(accountTextField);

            //���������
            passwordField = new YhPasswordTextFiled();
            passwordField.setBounds(47, 239, 150, 15);
            add(passwordField);

            //��¼��ť
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
                }
            });
        }

        private void createAndShowWarnDialog() {
            WarnNotifyDialog warnNotifyDialog = new WarnNotifyDialog(LoginFrame.this,"",true);
            warnNotifyDialog.setSize(223, 109);
            warnNotifyDialog.setVisible(true);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Image image = loginFrameBg.getImage();
            g.drawImage(image, -2, 0, loginFrameBg.getIconWidth(), loginFrameBg.getIconHeight(), this);
        }
    }
}
