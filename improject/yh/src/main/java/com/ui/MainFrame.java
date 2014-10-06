package com.ui;

import com.ui.resource.YhImageRes;
import com.ui.tree.YhContactTree;

import javax.swing.*;
import java.awt.*;

/**
 * Created by HK on 2014/10/5.
 */
public class MainFrame extends JFrame {

    private final static MainFrame mainFrame = new MainFrame();
    private YhContactTree yhContactTree;

    private MainFrame() throws HeadlessException {
        ContentPane content = new ContentPane();
        setContentPane(content);
        setSize(content.getWidth() + 5, content.getHeight() + 28);
        setResizable(false);
    }

    public static MainFrame getInstance() {
        return mainFrame;
    }

    public YhContactTree getYhContactTree() {
        return yhContactTree;
    }

    private class ContentPane extends JPanel {
        private ImageIcon bgImageIcon = YhImageRes.getImageIcon("mainFrame.png");

        ContentPane() {
            if (bgImageIcon == null) return;
            setSize(bgImageIcon.getIconWidth(), bgImageIcon.getIconHeight());
            setLayout(null);
            yhContactTree = new YhContactTree();
            JScrollPane yhTreeScrollPane = new JScrollPane();
            yhTreeScrollPane.setBorder(null);
            yhTreeScrollPane.setBackground(Color.WHITE);
            yhTreeScrollPane.setViewportView(yhContactTree);
            yhTreeScrollPane.setBounds(7,135,getWidth()-15,445);
            add(yhTreeScrollPane);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (bgImageIcon == null) return;
            Image image = bgImageIcon.getImage();
            g.drawImage(image, 0, 0, bgImageIcon.getIconWidth(), bgImageIcon.getIconHeight(), this);
        }
    }
}
