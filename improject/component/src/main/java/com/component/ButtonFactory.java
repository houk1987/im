package com.component;

import javax.swing.*;

/**
 * Created by HK on 2014/10/4.
 */
public abstract class ButtonFactory {

    private String buttonImagePath;    //��ťͼƬ·��

    protected ButtonFactory(String buttonImagePath) {
        this.buttonImagePath = buttonImagePath;
    }

    protected JButton createButton(String buttonName){
        return new BaseButton(buttonImagePath,buttonName);
    }

    protected JButton createButton(String buttonName,boolean isSelected){
        return new BaseButton(buttonImagePath,buttonName,isSelected);
    }
}
