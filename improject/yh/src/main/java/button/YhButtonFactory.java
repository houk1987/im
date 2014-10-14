package button;

import com.component.BaseButton;
import com.component.ButtonFactory;
import com.resource.ConfigurationRes;
import resource.Yh;

import javax.swing.*;

/**
 * Created by HK on 2014/10/5.
 */
public class YhButtonFactory extends ButtonFactory {
    private static YhButtonFactory yhButtonFactory;
    public static YhButtonFactory getInstance() {
        if(yhButtonFactory == null){
            yhButtonFactory = new YhButtonFactory();
        }
        return yhButtonFactory;
    }

    private YhButtonFactory() {

    }

    public JButton createLoginButton(){
        return createButton("login.png");
    }

    public JButton createOkButton(){
        return createButton("ok.png");
    }

    public JButton createAddBuddyButton(){ return createButton("AddBuddy.png");}

    public JButton createPreviousButton() {
        return createButton("previous.png");
    }

    public JButton createNextButton() {
        return createButton("next.png");
    }

    public JButton createCancelButton() {
        return createButton("cancel.png");
    }

    public JButton createFinishButton() {
        return createButton("finish.png");
    }

    public JButton createSendButton() {
        return createButton("send.png");
    }

    public JButton createRight() {
        return createButton("right.png");
    }

    public JButton createLeft() {
        return createButton("left.png");
    }
}
