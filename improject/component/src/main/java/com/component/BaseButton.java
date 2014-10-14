package com.component;

import javax.swing.*;
import java.awt.*;

/**
 * Created by HK on 2014/10/4.
 */
public class BaseButton extends JButton {

    public BaseButton(String imageName){
        this(imageName,false);
    }

    public BaseButton(String imageName,boolean isSelected) {
        String otherPath = imageName.substring(0, imageName.lastIndexOf("."));
        String hoverFullPath = otherPath + "Hover.png";
        String pressedFullPath = otherPath + "Pressed.png";
        setIcon(ImageUtils.getButtonImageIcon(imageName));
        setRolloverIcon(ImageUtils.getButtonImageIcon(hoverFullPath));
        setPressedIcon(ImageUtils.getButtonImageIcon(pressedFullPath));
        if(isSelected){
            String selectedFullPath = otherPath + "Pressed.png";
            setSelected(isSelected);
            setSelectedIcon(ImageUtils.getButtonImageIcon(selectedFullPath));
        }
        setBorderPainted(false);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setContentAreaFilled(false);
        setFocusPainted(false);
        Dimension dim = new Dimension(getIcon().getIconWidth(), getIcon().getIconHeight());
        setPreferredSize(dim);
        setSize(dim);
    }
}
