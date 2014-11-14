package qq.login;



import com.ui.button.ButtonFactory;

import javax.swing.*;

/**
 * ��½�����а�ť������
 * �ṩ��½�����а�ť����
 * Created by lenovo on 2014/10/16.
 */
public class LoginDialogButtonFactory extends ButtonFactory {

    protected LoginDialogButtonFactory() {
        super("login/button/");
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
