package com.ui.button;

import com.component.BaseButton;
import com.ui.resource.Yh;

import javax.swing.*;

/**
 * Created by HK on 2014/10/5.
 */
public class YhButtonFactory {

    private static YhButtonFactory yhButtonFactory = new YhButtonFactory();
    private final static String resSource = Yh.getResSource()+"button/";

    public static YhButtonFactory getInstance() {
        return yhButtonFactory;
    }

    private YhButtonFactory() {
    }

    public JButton createLoginButton(){
        return createBaseButton("login.png");
    }

    public JButton createOkButton(){
        return createBaseButton("ok.png");
    }

    private JButton createBaseButton(String name){
       return new BaseButton(resSource,name);
    }
}
