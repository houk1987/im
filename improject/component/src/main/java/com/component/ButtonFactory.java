package com.component;

/**
 * Created by HK on 2014/10/4.
 */
public class ButtonFactory {
    private static ButtonFactory buttonFactory = new ButtonFactory();

    protected ButtonFactory(){

    }

    public static ButtonFactory getInstance() {
        return buttonFactory;
    }

    public void createLoginButton(){

    }
}
