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

    public JButton createLoginButton(){
        return createButton("login.png");
    }



    public JButton createOkButton(){
        return createButton("ok.png");
    }

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

    public JButton createSearchFriendsButton(){
        return createButton("searchFriends.png");
    }

    public JButton createSearchButton(){
        return createButton("search.png");
    }

    public JButton createAddFriendsButton(){
        return createButton("addFriends.png");
    }

    public JButton createMainDialogCloseButton(){
        return createButton("mainDialogCloseButton.png");
    }

    public JButton createMainDialogMinButton(){
        return createButton("");
    }
}
