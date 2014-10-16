package qq.lunch;

import qq.login.LoginDialog;
import qq.sysTray.SysTrayManager;

import javax.swing.*;

/**
 * Created by HK on 2014/10/16.
 */
public class StartClient {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginDialog.getInstance().setVisible(true); //显示登陆窗口
                SysTrayManager.getInstance().initImTray();  //初始化右下系统托盘
            }
        });
    }
}
