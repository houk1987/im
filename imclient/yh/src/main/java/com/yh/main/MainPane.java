package com.yh.main;

import com.ui.jlabel.JLabelFactory;
import com.yh.button.CustomButtonFactory;
import com.yh.login.MenuPane;
import com.yh.main.contact.YhContactTree;
import com.yh.presence.PresenceSelectButton;
import org.jivesoftware.smack.packet.Presence;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by 356 on 14-8-27.
 */
public class MainPane extends JPanel {
    private final static String SKIN_PATH = "res/login/";
    private ImageIcon bgImageIcon = new ImageIcon(SKIN_PATH + "loginBg.png");
    private String[] menuNames = {"����(M)", "����(C)", "����(A)", "�f��(H)"};
    private YhContactTree yhContactTree;
    public MainPane() {
        setLayout(new BorderLayout(0, 5));
        MenuPane menuPane = new MenuPane(menuNames);
        add(menuPane, BorderLayout.NORTH);
        MeBgPane meBgPane = new MeBgPane();
        add(meBgPane, BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Image image = bgImageIcon.getImage();
        g2d.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    public void refreshTreePresence(Presence presence) {
        yhContactTree.changeContactItemPresence(presence);
    }


    class MeBgPane extends JPanel {
        private final static String SKIN_PATH = "res/main/";
        private ImageIcon bgImageIcon = new ImageIcon(SKIN_PATH + "me_bg.png");

        MeBgPane() {
            setLayout(null);
            this.setOpaque(false);
            JLabel headLabel = JLabelFactory.createJLabel(new ImageIcon(SKIN_PATH + "headIcon.png"));
            headLabel.setLocation(5, 0);
            add(headLabel);

            //���״̬ѡ��ť
            PresenceSelectButton presenceSelectButton = new PresenceSelectButton();
            presenceSelectButton.setLocation(55, 0);
            add(presenceSelectButton);

            //ǩ��png
            JLabel qmLabel = JLabelFactory.createJLabel(new ImageIcon(SKIN_PATH + "qm.png"));
            qmLabel.setLocation(55, 18);
            add(qmLabel);

            //���ѽ���
            JLabel friendJsLabel = JLabelFactory.createJLabel(new ImageIcon(SKIN_PATH + "friendJs.png"));
            friendJsLabel.setLocation(373, 0);
            add(friendJsLabel);

            //��Ϸ
            JLabel gameLabel = JLabelFactory.createJLabel(new ImageIcon(SKIN_PATH + "game.png"));
            gameLabel.setLocation(373, 40);
            add(gameLabel);

            //������
            JLabel searchBarPane = JLabelFactory.createJLabel(new ImageIcon(SKIN_PATH + "search.png"));
            searchBarPane.setLocation(5, 82);
            searchBarPane.setLayout(null);
            add(searchBarPane);

            JButton addBuddyButton = CustomButtonFactory.createAddBuddyButton();
            addBuddyButton.setLocation(11, 6);
            addBuddyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // YhManager.getAddContactManager().showAddContactDialog();
                }
            });
            searchBarPane.add(addBuddyButton);
            //�����
            yhContactTree = new YhContactTree();
            yhContactTree.setSize(452, 528);
            yhContactTree.setLocation(7, 110);
            add(yhContactTree);

            //�ײ�
            JLabel bottomLabel = JLabelFactory.createJLabel(new ImageIcon(SKIN_PATH + "bottom.png"));
            bottomLabel.setLocation(7, 630);
            add(bottomLabel);
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            Image image = bgImageIcon.getImage();
            g2d.drawImage(image, 0, 0, this.getWidth(), image.getHeight(this), this);
        }
    }
}
