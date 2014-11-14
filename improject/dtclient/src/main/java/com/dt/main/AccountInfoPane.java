package com.dt.main;

import com.dt.main.presence.PresenceManager;
import com.dt.main.presence.PresenceMenu;
import com.dt.main.tree.SynDataService;
import com.dt.start.StartClient;
import com.dt.vo.UserInfo;
import com.ui.jlabel.JLabelFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * Created by lenovo on 2014/9/11.
 */
public class AccountInfoPane extends JPanel {
    public AccountInfoPane(DtClientWindowContent dtClientWindowContent) {
        setLayout(new BorderLayout());
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel accountName = new JLabel();
        panel.add(accountName);
        final JLabel presenceLabel = JLabelFactory.createJLabel(PresenceManager.getOnline());
        presenceLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        final PresenceMenu presenceMenu = new PresenceMenu(presenceLabel);
        presenceLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                presenceMenu.show(presenceLabel, 5, 15);
            }
        });
        panel.add(presenceLabel);
        accountName.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 24));
        accountName.setForeground(Color.WHITE);
        panel.setBorder(new EmptyBorder(20, 0, 20, 0));
        add(panel, BorderLayout.CENTER);
        DtNavigationBar dtNavigationBar = new DtNavigationBar(dtClientWindowContent);
        add(dtNavigationBar, BorderLayout.SOUTH);
        java.util.List<UserInfo> userInfoList = SynDataService.getInstance().synUsers();
        for (UserInfo userInfo : userInfoList) {
            String account = StartClient.getUserInfo().getId();
            if (userInfo.getId().equals(account)) {
                accountName.setText(userInfo.getUsername());
            }
        }
    }
}
