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
                LoginDialog.getInstance().setVisible(true); //��ʾ��½����
                SysTrayManager.getInstance().initImTray();  //��ʼ������ϵͳ����
            }
        });
    }
}
