package com.dt.main;


import com.ui.jlabel.JLabelFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by lenovo on 2014/9/11.
 */
public class DtNavigationBar extends NavigationBar{

    private JLabel orgLabel; //组织机构按钮 （导演才能看到）
    private JLabel firstLabel;
    public DtNavigationBar(final DtClientWindowContent dtClientWindowContent) {

        bg = new ImageIcon("resources/images/main/navigation/navigationBg.png");
        firstLabel = JLabelFactory.createJLabel(new ImageIcon("resources/images/main/navigation/custom.png"));
        orgLabel = JLabelFactory.createJLabel(new ImageIcon("resources/images/main/navigation/org.png"));
        firstLabel.setBorder(new EmptyBorder(3,0,3,0));
        add(firstLabel);
        //if(role == 3){
            add(orgLabel);
       // }
        firstLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dtClientWindowContent.setTreeView(DtClientWindow.getInstance().getOrgTree());
            }
        });

        orgLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dtClientWindowContent.setTreeView(DtClientWindow.getInstance().getCustomTree());
            }
        });
    }
}
