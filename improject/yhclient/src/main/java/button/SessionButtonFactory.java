package button;

import com.ui.button.ButtonFactory;

import javax.swing.*;

/**
 * Created by lenovo on 2014/11/11.
 */
public class SessionButtonFactory extends ButtonFactory {

    private  static SessionButtonFactory sessionButtonFactory = new SessionButtonFactory();

    public static SessionButtonFactory getInstance() {
        return sessionButtonFactory;
    }

    protected SessionButtonFactory() {
        super("session/button");
    }

    public JButton createSendMessageButton(){
        return createButton("send.png");
    }


}
