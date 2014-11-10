package com.ui.headPicture;

import com.ui.image.ImageUtils;
import com.ui.jlabel.JLabelFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HeadPicture extends JLayeredPane {

    private static final long serialVersionUID = 1L;
    public Pricture pic;

    public HeadPicture(String path) {
        pic = new Pricture(path);
        pic.setBounds(1, 2, 43, 43);
        this.moveToFront(pic);
        this.add(pic);
        JLabel boxLabel = JLabelFactory.createJLabel(ImageUtils.getInstance("resources/images/common/").getImageIcon("picbox.png"));
        boxLabel.setLocation(0, 0);
        this.add(boxLabel);
        this.moveToBack(boxLabel);
        this.setSize(boxLabel.getSize());
    }
}

