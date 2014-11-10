package com.qq.button;
import com.qq.button.ButtonFactory;
import com.ui.button.ImageButton;

import javax.swing.*;

/**
 * 登陆窗口中按钮工厂类
 * 提供登陆窗口中按钮创建
 * Created by lenovo on 2014/10/16.
 */
public class SearchFriendsFrameButtonFactory extends ButtonFactory {

    private final String imagePath = "resources/images/friends/button/";
    public final static String MIN_WINDOW = "minWindow.png";
    public final static String CLOSE_WINDOW = "closeWindow.png";
    public final static String search = "search.png";
    public final static String addFriends = "addFriends.png";
    public final static String next = "next.png";
    public final static String close = "close.png";
    public final static String finish = "finish.png";
    public final static String closeAddFriendsFrame = "closeAddFriendsFrame.png";
    public final static String minAddFriendsFrame = "minAddFriendsFrame.png";

    @Override
    public ImageButton createButton(String type) {
        return new ImageButton(imagePath,type);
    }
}
