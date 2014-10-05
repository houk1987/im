package com.qq.login;

import com.san30.sim.pub.imagewindow.ImageDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * Created by HK on 2014/9/15.
 */
public class LoginFrame extends JDialog{
    private Point origin = new Point();
    public LoginFrame() {
        setUndecorated(true);
        setContentPane(new LoginPane());

        setResizable(false);
        setSize(425,328);
        addDrageFrame();
    }

    private void addDrageFrame(){
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                origin.x = e.getX();
                origin.y = e.getY();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point p = getLocation();
                setLocation(
                        p.x + e.getX() - origin.x,
                        p.y + e.getY() - origin.y);
            }
        });
    }

    public static void main(String[] args) {
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
    }
}
