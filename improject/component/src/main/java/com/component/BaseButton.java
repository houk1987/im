package com.component;



import com.resource.ImageUtils;

import javax.swing.*;
import java.awt.*;

/**
 * Created by HK on 2014/10/4.
 */
public class BaseButton extends JButton {

    public BaseButton(String buttonImagePath,String imageName){
        this(buttonImagePath,imageName,false);
    }

    public BaseButton(String buttonImagePath,String imageName,boolean isSelected) {
        ImageUtils imageUtils = new ImageUtils(buttonImagePath);
        String otherPath = imageName.substring(0, imageName.lastIndexOf("."));
        setIcon(imageUtils.getImageIcon(imageName));
        setRolloverIcon(imageUtils.getImageIcon(otherPath + "Hover.png"));
        setPressedIcon(imageUtils.getImageIcon(otherPath + "Pressed.png"));
        if(isSelected){
            String selectedFullPath = otherPath + "Pressed.png";
            setSelected(isSelected);
            setSelectedIcon(imageUtils.getImageIcon(selectedFullPath));
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
