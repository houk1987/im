package qq.login;

import com.component.ButtonFactory;
import com.resource.ConfigurationRes;

import javax.swing.*;

/**
 * ��½�����а�ť������
 * �ṩ��½�����а�ť����
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
