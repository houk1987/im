package com.ui.tree;

import javax.swing.*;
import javax.swing.plaf.metal.MetalTreeUI;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.KeyListener;

/**
 * Created by a on 2014/7/11.
 */
public class PubTree extends JTree {

    public PubTree() {
        super();
        initTreeUI();
    }

    private void initTreeUI() {
        this.putClientProperty("JTree.lineStyle", "None");//ȥ������
        this.setToggleClickCount(1); // ���һ��չ���ڵ�
        this.setRowHeight(23);//�����и�
        this.setEditable(false); //������༭
        this.setBackground(Color.white);//���ñ���ɫΪ��ɫ
        this.updateMetalTreelUI(this);//ȥ�����Ͼ����������
    }

    /**
     * ************************
     * ȥ�����Ͼ����������
     *
     * @param tree *********************
     * @author houk
     */
    private void updateMetalTreelUI(final JTree tree) {

        final MetalTreeUI customUI = new MetalTreeUI() {
            @Override
            protected boolean shouldPaintExpandControl(TreePath path, int row, boolean isExpanded, boolean wasExpanded, boolean leaf) {
                return false;
            }

            @Override
            protected void paintHorizontalLine(Graphics g, JComponent c, int y,
                                               int left, int right) {

            }

            @Override
            protected void paintVerticalLine(Graphics g, JComponent c, int x,
                                             int top, int bottom) {

            }

            @Override
            protected KeyListener createKeyListener() {//��д�˷�����ȡ������Ĭ�ϵļ�����Ӧ������ʱ��������ӦC����
                return null;
            }
        };
        if (tree != null) {
            tree.setUI(customUI);
        }
    }
}
