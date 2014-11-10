package com.ui.textField;

import javax.swing.*;
import java.awt.*;

/**
 * Created by hq on 2014/11/2.
 */
public class FreeTextField extends JTextField {

    private Color BLACK = new Color(0,0,0);

    public FreeTextField() {
        super();
        this.getInsets();
        this.setForeground(BLACK);
    }

    @Override
    public Insets getInsets() {
        return new Insets(0, 3, 0, 10);
    }
}
