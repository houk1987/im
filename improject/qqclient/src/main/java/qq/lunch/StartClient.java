package qq.lunch;

import org.jivesoftware.smack.XMPPException;
import org.smackservice.SmackConnection;
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
                try {
                    QQClient.getInstance().lunchStartQQClient();
                } catch (XMPPException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
