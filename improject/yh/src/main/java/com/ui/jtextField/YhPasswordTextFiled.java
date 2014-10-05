package com.ui.jtextField;

import javax.swing.*;
import java.awt.*;

/**
 * Created by HK on 2014/10/5.
 */
public class YhPasswordTextFiled extends JPasswordField {

    public YhPasswordTextFiled() {
        setForeground(Color.GRAY);
        setBorder(null);
    }

    @Override
    public Insets getInsets() {
        return new Insets(1,0,1,0);
    }

    @Override
    public void setEchoChar(char c) {
        super.setEchoChar('*');
    }
}
