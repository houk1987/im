package qq.login;


import com.component.ButtonFactory;
import com.component.ExtendPane;
import com.component.FontFactory;
import com.component.ImageUtils;
import com.component.jlabel.JLabelFactory;
import com.resource.ConfigurationRes;
import org.smackservice.SmackConnection;
import qq.manager.QQManager;
import qq.ui.JTextField.JTextFieldFactory;
import qq.ui.button.QqButtonFactory;
import qq.ui.window.PubDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by lenovo on 2014/9/16.
 */
public class LoginDialog extends PubDialog {

    private static LoginDialog loginDialog;

    public static LoginDialog getInstance() {
        if(loginDialog == null){
            loginDialog = new LoginDialog();
        }
        return loginDialog;
    }
    /**
     * ���캯��
     * ����qq ��½����
     */
    public LoginDialog() {
        setContentPane(new LoginContentPane(this));//�����������
        setSize(429,329);//��СΪ��������С
        setLocationRelativeTo(null); //������ʾ
    }
}
