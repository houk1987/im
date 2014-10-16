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
     * 构造函数
     * 创建qq 登陆窗口
     */
    public LoginDialog() {
        loginContentPane = new LoginContentPane(this);
        setContentPane(loginContentPane);//设置内容面板
        setSize(loginContentPane.getWidth(),loginContentPane.getHeight());//大小为内容面板大小
        setLocationRelativeTo(null); //居中显示

    }
}
