package com.qq.button;

import com.ui.button.ImageButton;

/**
 * Created by lenovo on 2014/11/3.
 */
public class LoginButtonFactory extends ButtonFactory {

    private final String imagePath = "resources/images/login/button/";
    public final static String MIN_WINDOW = "minWindow.png";
    public final static String CLOSE_WINDOW = "closeWindow.png";
    public final static String LOGIN = "login.png";

    @Override
    public ImageButton createButton(String type) {
        return new ImageButton(imagePath,type);
    }
}
