package com.component;

import javax.swing.*;

/**
 * Created by HK on 2014/10/4.
 */
public abstract class ButtonFactory {

    protected JButton createButton(String buttonName){
        return new BaseButton(buttonName);
    }

    protected JButton createButton(String buttonName,boolean isSelected){
        return new BaseButton(buttonName,isSelected);
    }
}
