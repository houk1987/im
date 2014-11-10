package com.qq.button;

import com.ui.button.ImageButton;

/**
 * Created by lenovo on 2014/11/3.
 */
public class Button {
    private ButtonFactory buttonFactory;

    public Button(ButtonFactory buttonFactory) {
        this.buttonFactory = buttonFactory;
    }

    public ImageButton createButton(String type){
        ImageButton imageButton = buttonFactory.createButton(type);
        return imageButton;
    }
}
