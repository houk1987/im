package com.qq.button;
import com.ui.button.ImageButton;

import javax.swing.*;

/**
 * 登陆窗口中按钮工厂类
 * 提供登陆窗口中按钮创建
 * Created by lenovo on 2014/10/16.
 */
public class MainDialogButtonFactory extends ButtonFactory{

    private final String imagePath = "resources/images/main/button/";
    public final static String MIN_WINDOW = "minWindow.png";
    public final static String CLOSE_WINDOW = "closeWindow.png";
    public final static String SEARCH_FRIENDS = "searchFriends.png";

    @Override
    public ImageButton createButton(String type) {
        return new ImageButton(imagePath,type);
    }
}
