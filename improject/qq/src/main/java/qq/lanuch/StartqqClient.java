package qq.lanuch;

import com.qq.ui.login.LoginDialog;
import com.qq.ui.session.SessionFrame;
import org.jivesoftware.smack.XMPPException;

import javax.swing.*;

/**
 * Created by lenovo on 2014/9/16.
 */
public class StartqqClient {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
                    QQClient.getInstance().loginClient("3","1");
//                    SessionFrame.openChatSessionFrame("1");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                } catch (XMPPException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
