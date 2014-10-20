package qq.friends;

import com.component.ButtonFactory;

import javax.swing.*;

/**
 * ��½�����а�ť������
 * �ṩ��½�����а�ť����
 * Created by lenovo on 2014/10/16.
 */
public class SearchFriendsFrameButtonFactory extends ButtonFactory{

    protected SearchFriendsFrameButtonFactory() {
        super("friends/button/");
    }

    public JButton createMinWindowButton() {
        return createButton("minWindow.png");
    }

    public JButton createCloseWindowButton(){
        return createButton("closeWindow.png");
    }

    public JButton createSearchFriendsButton() {
        return createButton("search.png");
    }

    public JButton createAddFriendsButton() {
        return createButton("addFriends.png");
    }

    public JButton createNextButton(){
        return createButton("next.png");
    }

    public JButton createCloseButton(){
        return createButton("close.png");
    }


    public JButton createFinishButton(){
        return createButton("finish.png");
    }
}
