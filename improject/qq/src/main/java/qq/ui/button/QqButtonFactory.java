package qq.ui.button;

import com.component.BaseButton;
import com.component.ButtonFactory;
import com.resource.ConfigurationRes;
import javax.swing.*;

/**
 * Created by HK on 2014/10/5.
 */
public class QqButtonFactory extends ButtonFactory{
    private static QqButtonFactory qqButtonFactory = new QqButtonFactory();

    public static QqButtonFactory getInstance() {
        if(qqButtonFactory == null){
            qqButtonFactory = new QqButtonFactory();
        }
        return qqButtonFactory;
    }

    private QqButtonFactory() {

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

    public JButton createSessionFrameCloseButton(){
        return createButton("sessionFrameClose.png");
    }

}
