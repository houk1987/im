package button;

import com.ui.button.ButtonFactory;

import javax.swing.*;

/**
 * Created by lenovo on 2014/11/11.
 */
public class LoginButtonFactory extends ButtonFactory {

    private static LoginButtonFactory loginButtonFactory = new LoginButtonFactory();

    public static LoginButtonFactory getInstance() {
        return loginButtonFactory;
    }

    private LoginButtonFactory() {
        super("login/button");
    }

    public JButton createLoginButton() {
        return createButton("login.png") ;
    }
}
