package com.qq.login;

import com.ui.tools.Tools;
import com.ui.window.PubDialog;

/**
 * Created by lenovo on 2014/9/16.
 */
class LoginDialog extends PubDialog {
    private LoginContentPane loginContentPane;
    /**
     * ���캯��
     * ����qq ��½����
     */
    public LoginDialog() {
        loginContentPane = new LoginContentPane(this);
        setContentPane(loginContentPane);//�����������
        setSize(loginContentPane.getWidth(),loginContentPane.getHeight());//��СΪ��������С
        Tools.setWindowOnScreamCenter(this); //������ʾ
        toFront();
    }
}
