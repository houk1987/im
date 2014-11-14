package com.ui.button;

import com.resource.ConfigurationRes;

import javax.swing.*;

/**
 * Created by HK on 2014/10/4.
 */
public abstract class ButtonFactory {
    private String buttonImagePath;    //°´Å¥Í¼Æ¬Â·¾¶
    protected ButtonFactory(String buttonImagePath) {
        this.buttonImagePath = buttonImagePath;
    }

    protected JButton createButton(String buttonName){
        return new ImageButton(buttonImagePath,buttonName);
    }


}
