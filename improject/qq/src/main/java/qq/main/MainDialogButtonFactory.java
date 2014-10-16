package qq.main;

import com.component.ButtonFactory;
import com.resource.ConfigurationRes;

import javax.swing.*;

/**
 * ��½�����а�ť������
 * �ṩ��½�����а�ť����
 * Created by lenovo on 2014/10/16.
 */
public class MainDialogButtonFactory extends ButtonFactory{

    protected MainDialogButtonFactory() {
        super(ConfigurationRes.getImageResPath()+"main/button/");
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

    public JButton createSearchFriendsButton() {
        return createButton("searchFriends.png");
    }
}
