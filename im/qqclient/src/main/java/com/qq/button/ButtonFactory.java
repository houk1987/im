package com.qq.button;

import com.ui.button.ImageButton;

import java.awt.*;

/**
 * Created by lenovo on 2014/11/3.
 */
public abstract class ButtonFactory {

    public abstract ImageButton createButton(String type);
}
