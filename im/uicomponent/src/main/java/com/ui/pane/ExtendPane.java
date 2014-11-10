package com.ui.pane;

import javax.swing.*;
import java.awt.*;

/**
 * Created by lenovo on 2014/10/14.
 */
public class ExtendPane extends JPanel {

    private ImageIcon bgImageIcon;

    public ExtendPane(LayoutManager layout, ImageIcon bgImageIcon) {
        super(layout);
        if(bgImageIcon!=null){
            setSize(bgImageIcon.getIconWidth(), bgImageIcon.getIconHeight()); //����Ϊ����ͼƬ��С
            this.bgImageIcon = bgImageIcon;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(bgImageIcon == null)return;
        Image image = bgImageIcon.getImage();
        g.drawImage(image,0,0,image.getWidth(this),image.getHeight(this),this);
    }

    public void setBgImageIcon(ImageIcon bgImageIcon) {
        this.bgImageIcon = bgImageIcon;
    }

    public ImageIcon getBgImageIcon() {
        return bgImageIcon;
    }
}
