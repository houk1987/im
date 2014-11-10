package com.qq.main.tree;


import com.qq.chat.ChatWindowManager;
import com.ui.image.ImageUtils;
import com.ui.rosterTree.ContactItem;

import javax.swing.*;
import java.awt.*;

/**
 * Created by lenovo on 2014/10/8.
 */
public class QQContactItem extends ContactItem {

    private final static Color hoverColor = new Color(252, 240, 193);
    private final static Color pressedColor = hoverColor;
    private final static ImageIcon headIcon = ImageUtils.getInstance("tree/").getImageIcon("head.png");

    public QQContactItem() {
    }

    public QQContactItem(String account,String userName) {
        setJid(account);
        setUserName(userName);
        setHoverColor(hoverColor);
        setPressedColor(pressedColor);
        setHeadIcon(headIcon);
    }

    @Override
    public void click(){
        ChatWindowManager.openChatWindow(getJid());
    }
}
