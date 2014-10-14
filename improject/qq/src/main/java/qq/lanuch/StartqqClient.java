package qq.lanuch;
import qq.manager.QQManager;
import qq.ui.login.LoginDialog;

import javax.swing.*;

/**
 * Created by lenovo on 2014/9/16.
 */
public class StartqqClient {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                QQManager.getInstance().createAndShowLoginDialog();
            }
        });
    }
}
