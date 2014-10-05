package com.qq.login;

import com.ui.button.ImageButtonFactory;
import com.ui.jlabel.JLabelFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by HK on 2014/9/15.
 */
public class LoginPane extends JPanel {
    private final String SKIN_PATH="res/login/";
    private final String SKIN_PATH_BUTTON="res/button/login/";
    private ImageIcon loginFrameBg;       //±³¾°Í¼Æ¬
    private JLabel closeButton;

    public LoginPane() {
        loginFrameBg = new ImageIcon(SKIN_PATH+"loginFrameBg.png");
        setSize(loginFrameBg.getIconWidth(),loginFrameBg.getIconHeight());
        setLayout(null);
        initButton();
    }

    private void initButton(){
        closeButton = JLabelFactory.createJLabel(new ImageIcon(SKIN_PATH_BUTTON+"close.png"));
        closeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                closeButton.setIcon(new ImageIcon(SKIN_PATH_BUTTON+"closeHover.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                closeButton.setIcon(new ImageIcon(SKIN_PATH_BUTTON+"close.png"));
            }
        });

        closeButton.setLocation(396,0);
        add(closeButton);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        Image image = loginFrameBg.getImage();
        g.drawImage(image,0,0,image.getWidth(this),image.getHeight(this),this);
    }
}
