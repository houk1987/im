package com.ui;

import com.ui.addContact.AddContactDialog;
import com.ui.button.YhButtonFactory;
import com.ui.resource.YhImageRes;
import com.ui.tree.YhContactTree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by HK on 2014/10/5.
 */
public class MainFrame extends JFrame {

    private static MainFrame mainFrame;
    private YhContactTree yhContactTree;
    private String loginUser;

    private MainFrame() throws HeadlessException {
        ContentPane content = new ContentPane();
        setContentPane(content);
        setSize(content.getWidth() + 5, content.getHeight() + 28);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static MainFrame getInstance() {
        if(mainFrame == null){
            mainFrame = new MainFrame();
        }
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

            JButton addBuddyButton = YhButtonFactory.getInstance().createAddBuddyButton();
            addBuddyButton.setLocation(16, 114);
            add(addBuddyButton);
            addBuddyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    AddContactDialog addContactDialog = new AddContactDialog(MainFrame.getInstance());
                    addContactDialog.setVisible(true);
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (bgImageIcon == null) return;
            Image image = bgImageIcon.getImage();
            g.drawImage(image, 0, 0, bgImageIcon.getIconWidth(), bgImageIcon.getIconHeight(), this);
        }
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }
}
