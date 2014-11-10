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
        // 当鼠标按下的时候获得窗口当前的位置
        origin.x = e.getX();
        origin.y = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // 当鼠标拖动时获取窗口当前位置
        Point p = window.getLocation();
        // 设置窗口的位置
        // 窗口当前的位置 + 鼠标当前在窗口的位置 - 鼠标按下的时候在窗口的位置
        window.setLocation(p.x + e.getX() - origin.x, p.y + e.getY()- origin.y);
    }
}
