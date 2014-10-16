package qq.login;


import qq.ui.window.PubDialog;

import javax.swing.*;
import java.awt.*;

/**
 * Created by lenovo on 2014/9/16.
 */
public class LoginDialog extends PubDialog {

    private static LoginDialog loginDialog;
    private LoginContentPane loginContentPane;

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
        loginContentPane = new LoginContentPane(this);
        setContentPane(loginContentPane);//�����������
        setSize(loginContentPane.getWidth(),loginContentPane.getHeight());//��СΪ��������С
        setLocationRelativeTo(null); //������ʾ

    }
}
