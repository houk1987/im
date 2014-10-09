package com.ui;

import com.component.jlabel.ExpandableImageLabel;
import com.component.jlabel.JLabelFactory;
import com.ui.resource.Yh;
import com.ui.resource.YhImageRes;
import mangager.PresenceManager;
import org.jivesoftware.smack.packet.Presence;
import org.smackservice.SmackConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Created by a on 2014/9/1.
 */
public class PresenceSelectButton extends JPanel {
    private JLabel leftLabel;
    private ExpandableImageLabel centerLabel;
    private JLabel rightLabel;
    private JLabel statusIconLabel;
    private JLabel account;


    public PresenceSelectButton() {
        setLayout(new BorderLayout());
        setOpaque(false);
        leftLabel = JLabelFactory.createJLabel(null);
        centerLabel = new ExpandableImageLabel(null);
        rightLabel = JLabelFactory.createJLabel(null);
        add(leftLabel, BorderLayout.WEST);
        add(centerLabel, BorderLayout.CENTER);
        add(rightLabel, BorderLayout.EAST);
        //默认为在线
        statusIconLabel = JLabelFactory.createJLabel(PresenceManager.getOnline());
        leftLabel.setLayout(new BorderLayout(3, 0));
        leftLabel.add(statusIconLabel, BorderLayout.CENTER);
        Font font = new Font("宋体", Font.BOLD, 14);
        account = JLabelFactory.createJLabel(Yh.getLoginUser().split("@")[0], font, Color.WHITE);
        centerLabel.setLayout(new BorderLayout());
        centerLabel.add(account, BorderLayout.NORTH);
        defaultBg();
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                pressedBg();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                hoverBg();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                defaultBg();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                StatusMenu statusMenu = new StatusMenu();
                statusMenu.show(PresenceSelectButton.this,5,20);
            }
        });
    }




    private void defaultBg() {
        switchBg(YhImageRes.getPresenceSelectButtonImageIcon("leftDefault.png"), YhImageRes.getPresenceSelectButtonImageIcon("centerDefault.png"), YhImageRes.getPresenceSelectButtonImageIcon( "rightDefault.png"));
    }

    private void hoverBg() {
        switchBg(YhImageRes.getPresenceSelectButtonImageIcon( "leftHover.png"), YhImageRes.getPresenceSelectButtonImageIcon( "centerHover.png"), YhImageRes.getPresenceSelectButtonImageIcon( "rightHover.png"));
    }

    private void pressedBg() {
        switchBg(YhImageRes.getPresenceSelectButtonImageIcon( "leftPressed.png"), YhImageRes.getPresenceSelectButtonImageIcon("centerPressed.png"), YhImageRes.getPresenceSelectButtonImageIcon( "rightPressed.png"));
    }


    private void switchBg(ImageIcon left, ImageIcon center, ImageIcon right) {
        leftLabel.setIcon(left);
        leftLabel.setSize(left.getIconWidth(), left.getIconHeight());
        centerLabel.setImageIcon(center);
        rightLabel.setIcon(right);
        rightLabel.setSize(right.getIconWidth(), right.getIconHeight());
        setSize(100, rightLabel.getHeight());
    }



    class StatusMenu extends JPopupMenu{

        StatusMenu() {
            List<Presence> presenceTypeList = PresenceManager.getPresences();
            for(Presence e : presenceTypeList){
                StatusItem menuItem = new StatusItem(e);
                add(menuItem);
            }
        }
    }

    class StatusItem extends JMenuItem{
        StatusItem(final Presence type) {
            this.setText(type.getStatus());
            this.setIcon(PresenceManager.getPresenceIcon(type));
            this.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    statusIconLabel.setIcon(PresenceManager.getPresenceIcon(type));
                    SmackConnection.getInstance().sendPacket(type);
                }
            });
        }
    }
}
