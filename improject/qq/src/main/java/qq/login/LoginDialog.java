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
     * 构造函数
     * 创建qq 登陆窗口
     */
    public LoginDialog() {
        setContentPane(new LoginContentPane(this));//设置内容面板
        setSize(429,329);//大小为内容面板大小
        setLocationRelativeTo(null); //居中显示
    }
}
