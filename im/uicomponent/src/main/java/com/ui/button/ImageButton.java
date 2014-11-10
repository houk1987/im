package com.ui.button;

import com.ui.image.ImageUtils;

import javax.swing.*;
import java.awt.*;

/**
 * ͼƬ��ť
 * һ��������״̬��ͼƬ default,hover,pressed
 * Created by lenovo on 2014/11/3.
 */
public class ImageButton extends JButton {
    private ImageUtils imageUtils;
    private String otherPath;
    /**
     * ��ҪĬ��״̬�µ�ͼƬ����
     * �ڲ������Ĭ�ϵ�ͼƬ���ƴ�������2��״̬��ͼ��
     *
     * @param imagePath ͼƬ�ļ�����λ��
     * @param imageName Ĭ��״̬��ťͼƬ�ļ���
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
