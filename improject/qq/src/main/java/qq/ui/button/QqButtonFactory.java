package qq.ui.button;

import com.component.BaseButton;
import qq.resource.Qq;
import javax.swing.*;

/**
 * Created by HK on 2014/10/5.
 */
public class QqButtonFactory {

    private static QqButtonFactory yhButtonFactory = new QqButtonFactory();
    private final static String resSource = Qq.getResSource()+"button/";

    public static QqButtonFactory getInstance() {
        return yhButtonFactory;
    }

    private QqButtonFactory() {
    }

    public JButton createLoginButton(){
        return createBaseButton("login.png");
    }

    public JButton createOkButton(){
        return createBaseButton("ok.png");
    }

    public JButton createAddBuddyButton(){ return createBaseButton("AddBuddy.png");}

    public JButton createPreviousButton() {
        return createBaseButton("previous.png");
    }

    private JButton createBaseButton(String name){
       return new BaseButton(resSource,name);
    }


    public JButton createNextButton() {
        return createBaseButton("next.png");
    }

    public JButton createCancelButton() {
        return createBaseButton("cancel.png");
    }

    public JButton createFinishButton() {
        return createBaseButton("finish.png");
    }

    public JButton createSendButton() {
        return createBaseButton("send.png");
    }
}
