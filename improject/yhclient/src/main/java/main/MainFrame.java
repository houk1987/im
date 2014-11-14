package main;

import addContact.AddContactDialog;
import button.AddContactButtonFactory;
import button.MainButtonFactory;
import com.ui.jlabel.JLabelFactory;
import images.MainImagesFactory;
import tree.YhContactTree;
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
    private ContentPane content;

    private MainFrame() throws HeadlessException {
        content = new ContentPane();
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
        private ImageIcon bgImageIcon;
        private JButton rightButton;
        private JButton leftButton;
        private JLabel rightBgLabel;
        private JScrollPane yhTreeScrollPane;

        ContentPane() {
            bgImageIcon = MainImagesFactory.createMainFrameBg();
            rightButton = MainButtonFactory.getInstance().createRightButton();
            leftButton = MainButtonFactory.getInstance().createLeftButton();
            rightBgLabel = JLabelFactory.createJLabel(MainImagesFactory.createRightBg());
            yhTreeScrollPane = new JScrollPane();
            setSize(bgImageIcon.getIconWidth(), bgImageIcon.getIconHeight());

            setLayout(null);
            PresenceSelectButton presenceSelectButton = new PresenceSelectButton();
            JPanel panel = new JPanel(new BorderLayout());
            panel.setBackground(new Color(151, 56, 142));
            panel.setSize(presenceSelectButton.getWidth(),presenceSelectButton.getHeight()+2);
            panel.setLocation(65,25);
            add(panel);
            panel.add(presenceSelectButton);

           // leftButton.setSelectedIcon(YhImageRes.getButtonImageIcon("leftIsSelected.png"));
            leftButton.setSelected(true);
            leftButton.setPressedIcon(leftButton.getSelectedIcon());
            leftButton.setLocation(4, 82);
            leftButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    leftButton.setSelected(true);
                    leftButton.setPressedIcon(leftButton.getSelectedIcon());
                    rightButton.setSelected(false);
                    rightBgLabel.setVisible(false);
                    yhTreeScrollPane.setVisible(true);
                }
            });
            add(leftButton);

            rightButton.setLocation(122,82);
            add(rightButton);
            rightButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    rightBgLabel.setVisible(true);
                    yhTreeScrollPane.setVisible(false);
                   // rightButton.setSelectedIcon(YhImageRes.getButtonImageIcon("rightIsSelected.png"));
                    rightButton.setSelected(true);
                    rightButton.setPressedIcon(rightButton.getSelectedIcon());
                    leftButton.setSelected(false);
                    //leftButton.setPressedIcon(YhImageRes.getButtonImageIcon("leftPressed.png"));
                }
            });

            rightBgLabel.setVisible(false);
            rightBgLabel.setBounds(7,135,getWidth()-15,445);
            add(rightBgLabel);

            yhContactTree = new YhContactTree();
            yhTreeScrollPane.setBorder(null);
            yhTreeScrollPane.setBackground(Color.WHITE);
            yhTreeScrollPane.setViewportView(yhContactTree);
            yhTreeScrollPane.setBounds(7,135,getWidth()-15,445);
            add(yhTreeScrollPane);

            JButton addBuddyButton = AddContactButtonFactory.getInstance().createAddBuddy();
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
