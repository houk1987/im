package com.ui.pane;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * �Զ���ָ����
 * �ָ���Ĭ�ϲ����ƶ�
 * Created by lenovo on 2014/11/5.
 */
public class ExtendSplitPane extends JSplitPane {

    public ExtendSplitPane(int newOrientation) {
        super(newOrientation,false);
        setDividerSize(1);
        setResizeWeight(0.8);
        setOpaque(false);
        setBorder(new EmptyBorder(0, 0, 0, 0));
        setEnabled(false); //���÷ָ��߲����ƶ�
    }
}
