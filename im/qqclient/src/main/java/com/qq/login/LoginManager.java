package com.qq.login;

import com.comunication.connection.ConnectionManager;
import com.comunication.interceptor.MessageHandleManager;
import com.comunication.interceptor.MessagePacketHandle;
import com.qq.interceptor.ChatMessageHandler;
import com.qq.main.MainDialog;
import org.apache.log4j.Logger;
import org.jivesoftware.smack.XMPPException;

import javax.swing.*;
import java.util.concurrent.ExecutionException;

/**
 * Created by lenovo on 2014/11/3.
 */
public class LoginManager {
    private static Logger logger = Logger.getLogger(LoginManager.class);
    private static LoginDialog loginDialog;


    /**
     * ��ʾ��½����
     */
    public static void showLoginDialog(){
        logger.info("��ʾ��½����");
        if(loginDialog == null){
            loginDialog = new LoginDialog();
        }
        loginDialog.setVisible(true);
        loginDialog.toFront();
    }

    public static void login(String username,String password){
        LoginWork loginWork = new LoginWork(username,password);
        loginWork.execute();
    }

    public static void close(){
        ConnectionManager.closeConnection();
        System.exit(0);
    }

    static class LoginWork extends SwingWorker<Boolean,Boolean>{
        private String username;
        private String password;
        LoginWork(String username,String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        protected Boolean doInBackground() throws Exception {
            try {
                ConnectionManager.createConnection(); //��������
            } catch (XMPPException e) {
                JOptionPane.showMessageDialog(null,"����������ʧ�ܣ�");
                e.printStackTrace();
            }
            boolean loginSuccess = ConnectionManager.login(username,password);
            return loginSuccess;
        }

        @Override
        protected void done() {
            super.done();
            try {
                if(isDone() && get()){
                    logger.info("��½�ɹ�");
                    LoginManager.loginDialog.dispose();
                    MainDialog.getInstance().setVisible(true);
                    MessageHandleManager.addMessagePackHandle(new ChatMessageHandler());
                }else {
                    logger.info("��½ʧ��");
                    JOptionPane.showMessageDialog(null,"��½ʧ�ܣ�");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
