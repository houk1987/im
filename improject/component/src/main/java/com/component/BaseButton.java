package com.component;

import javax.swing.*;
import java.awt.*;

/**
 * Created by HK on 2014/10/4.
 */
public class BaseButton extends JButton {
    public BaseButton(String path,String imageName) {
        String buttonPath = path+imageName;
        String otherPath = buttonPath.substring(0, buttonPath.lastIndexOf("."));
        String hoverFullPath = otherPath + "Hover.png";
        String pressedFullPath = otherPath + "Pressed.png";
        ImageIcon defaultImage = new ImageIcon(buttonPath);
        setIcon(defaultImage);
        setRolloverIcon(new ImageIcon(hoverFullPath));
       setPressedIcon(new ImageIcon(pressedFullPath));
        setBorderPainted(false);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setContentAreaFilled(false);
        setFocusPainted(false);
        Dimension dim = new Dimension(defaultImage.getIconWidth(), defaultImage.getIconHeight());
        setPreferredSize(dim);
        setSize(dim);
    }
}
