package com.ui.window;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Created by lenovo on 2014/10/10.
 */
public class WindowDraggedHandle extends MouseAdapter implements MouseMotionListener {
    private Point origin = new Point();
    private Window window;

    public WindowDraggedHandle(Window window){
        this.window = window;
        window.addMouseListener(this);
        window.addMouseMotionListener(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // ����갴�µ�ʱ���ô��ڵ�ǰ��λ��
        origin.x = e.getX();
        origin.y = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // ������϶�ʱ��ȡ���ڵ�ǰλ��
        Point p = window.getLocation();
        // ���ô��ڵ�λ��
        // ���ڵ�ǰ��λ�� + ��굱ǰ�ڴ��ڵ�λ�� - ��갴�µ�ʱ���ڴ��ڵ�λ��
        window.setLocation(p.x + e.getX() - origin.x, p.y + e.getY()- origin.y);
    }
}
