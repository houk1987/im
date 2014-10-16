package qq.login;

import com.component.ButtonFactory;
import com.resource.ConfigurationRes;

import javax.swing.*;

/**
 * 登陆窗口中按钮工厂类
 * 提供登陆窗口中按钮创建
 * Created by lenovo on 2014/10/16.
 */
public class LoginDialogButtonFactory extends ButtonFactory{

    protected LoginDialogButtonFactory() {
        super(ConfigurationRes.getImageResPath()+"login/button/");
    }

    public JButton createLoginButton(){
        return createButton("login.png");
    }

    public JButton createMinWindowButton() {
        return createButton("minWindow.png");
    }

    public JButton createCloseWindowButton(){
        return createButton("closeWindow.png");
    }
}
