package com.component.rosterTree;

import javax.swing.*;
import java.awt.*;

/**
 * Created by HK on 2014/10/6.
 */
public class ContactItem {

    private String jid;
    private String userName;
    private ImageIcon headIcon;
    private ImageIcon presenceIcon;
    private Color hoverColor;
    private Color pressedColor;

    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ImageIcon getHeadIcon() {
        return headIcon;
    }

    public void setHeadIcon(ImageIcon headIcon) {
        this.headIcon = headIcon;
    }

    public ImageIcon getPresenceIcon() {
        return presenceIcon;
    }

    public void setPresenceIcon(ImageIcon presenceIcon) {
        this.presenceIcon = presenceIcon;
    }

    public Color getHoverColor() {
        return hoverColor;
    }

    public void setHoverColor(Color hoverColor) {
        this.hoverColor = hoverColor;
    }

    public Color getPressedColor() {
        return pressedColor;
    }

    public void setPressedColor(Color pressedColor) {
        this.pressedColor = pressedColor;
    }
}
