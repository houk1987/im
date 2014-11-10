package com.qq.login;

import com.ui.tools.Tools;
import com.ui.window.PubDialog;

/**
 * Created by lenovo on 2014/9/16.
 */
class LoginDialog extends PubDialog {
    private LoginContentPane loginContentPane;
    /**
     * 构造函数
     * 创建qq 登陆窗口
     */
    public LoginDialog() {
        loginContentPane = new LoginContentPane(this);
        setContentPane(loginContentPane);//设置内容面板
        setSize(loginContentPane.getWidth(),loginContentPane.getHeight());//大小为内容面板大小
        Tools.setWindowOnScreamCenter(this); //居中显示
        toFront();
    }
}
