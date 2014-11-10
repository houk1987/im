package com.ui.rosterTree;

import javax.swing.*;
import java.awt.*;

/**
 * Created by a on 2014/8/28.
 */
public abstract  class TreePane extends JPanel {

    private JScrollPane contactListScrollPane;
    protected JPanel mainPanel;
    public TreePane() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        mainPanel = new JPanel();
        setBackGround();
        mainPanel.setLayout(new VerticalFlowLayout(VerticalFlowLayout.TOP, 0, 0, true, false));
        contactListScrollPane = new JScrollPane(mainPanel);
        contactListScrollPane.setAutoscrolls(true);
        contactListScrollPane.setBorder(BorderFactory.createEmptyBorder());
        contactListScrollPane.getVerticalScrollBar().setBlockIncrement(200);
        contactListScrollPane.getVerticalScrollBar().setUnitIncrement(20);
        contactListScrollPane.setBounds(0,0,this.getWidth(),getHeight());
        add(contactListScrollPane,BorderLayout.CENTER);
        loadData();
    }

    protected abstract void loadData();

    protected abstract void setBackGround();
}
