package com.ui.jtextField;

import javax.swing.*;
import java.awt.*;

/**
 * Created by HK on 2014/10/5.
 */
public class YhTextFiled extends JTextField{

    public YhTextFiled() {
      setForeground(Color.GRAY);
      setBorder(null);
    }

    @Override
    public Insets getInsets() {
        return new Insets(1,0,1,0);
    }
}
