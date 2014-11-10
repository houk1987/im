package com.ui.button;

import com.ui.image.ImageUtils;

import javax.swing.*;
import java.awt.*;

/**
 * 图片按钮
 * 一般有三种状态的图片 default,hover,pressed
 * Created by lenovo on 2014/11/3.
 */
public class ImageButton extends JButton {
    private ImageUtils imageUtils;
    private String otherPath;
    /**
     * 需要默认状态下的图片名称
     * 内部会根据默认的图片名称处理另外2种状态的图标
     *
     * @param imagePath 图片文件所在位置
     * @param imageName 默认状态按钮图片文件名
     */
    public ImageButton(String imagePath, String imageName) {
        imageUtils = ImageUtils.getInstance(imagePath);
        otherPath = imageName.substring(0, imageName.lastIndexOf("."));
        setIcon(imageUtils.getImageIcon(imageName));
        setRolloverIcon(imageUtils.getImageIcon(otherPath + "Hover.png"));
        setPressedIcon(imageUtils.getImageIcon(otherPath + "Pressed.png"));
        setBorderPainted(false);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setContentAreaFilled(false);
        setFocusPainted(false);
        Dimension dim = new Dimension(getIcon().getIconWidth(), getIcon().getIconHeight());
        setPreferredSize(dim);
        setSize(dim);
    }

    @Override
    public void setSelected(boolean b) {
        super.setSelected(b);
        if (b) {
            String selectedFullPath = otherPath + "Pressed.png";
            setSelectedIcon(imageUtils.getImageIcon(selectedFullPath));
        }
    }
}
