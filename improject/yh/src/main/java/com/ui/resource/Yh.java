package com.ui.resource;

import com.component.ImTray;

/**
 * Created by HK on 2014/10/4.
 */
public class Yh {
    private final static String resSource = "resources/images/";
    private static ImTray imTray;
    private static String loginUser;

    public static String getResSource() {
        return resSource;
    }

    public static String getLoginUser() {
        return loginUser;
    }

    public static void setLoginUser(String loginUser) {
        Yh.loginUser = loginUser;
    }

    public static ImTray getImTray() {
        if(imTray == null){
            imTray = new ImTray(YhImageRes.getImageIcon("offline.gif").getImage(),"YM");
        }
        return imTray;
    }
}
