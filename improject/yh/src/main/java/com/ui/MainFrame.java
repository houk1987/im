package com.ui;

import com.ui.resource.YhImageRes;

import javax.swing.*;
import java.awt.*;

/**
 * Created by HK on 2014/10/5.
 */
public class MainFrame extends JFrame {

    private static MainFrame mainFrame = new MainFrame();

    private MainFrame() throws HeadlessException {
        ContentPane content = new ContentPane();
        setContentPane(content);
        setSize(content.getWidth()+5,content.getHeight()+28);
        setResizable(false);
    }

    public static MainFrame getInstance() {
        return mainFrame;
    }

    private class ContentPane extends JPanel{
        private ImageIcon bgImageIcon = YhImageRes.getImageIcon("mainFrame.png");

         ContentPane() {
            if(bgImageIcon == null)return;
            setSize(bgImageIcon.getIconWidth(), bgImageIcon.getIconHeight());
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if(bgImageIcon == null)return;
            Image image = bgImageIcon.getImage();
            g.drawImage(image,0,0,bgImageIcon.getIconWidth(),bgImageIcon.getIconHeight(),this);
        }
    }
}
