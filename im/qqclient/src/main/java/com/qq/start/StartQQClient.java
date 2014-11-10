package com.qq.start;

import com.comunication.connection.ConnectionManager;
import com.qq.login.LoginManager;
import org.jivesoftware.smack.XMPPException;

import javax.swing.*;

/**
 * Created by HK on 2014/10/16.
 */
public class StartQQClient {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginManager.showLoginDialog();
            }
        });
    }
}
