package com.ui.tools;

import java.awt.*;

/**
 * Created by hq on 2014/11/2.
 */
public class Tools {

    private final static Dimension screenSize =Toolkit.getDefaultToolkit().getScreenSize();

    public static void setWindowLocationOnScreamRight(Window window){
        if(window == null)return;
        int x =  screenSize.width - window.getWidth()-50;
        window.setLocation(x,50);
    }

    public static void setWindowOnScreamCenter(Window window){
        if(window == null)return;
        window.setLocationRelativeTo(null);
    }
}
