package qq.ui.pub;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by lenovo on 2014/9/16.
 */
public class MouseDraggedFrameHandel extends MouseAdapter {

    private Window window;

    public MouseDraggedFrameHandel(Window window) {
        this.point = new Point();
        this.window = window;
    }

    private Point point;

    @Override
    public void mousePressed(MouseEvent e) {
        /**
         * 添加鼠标监听
         * 监听鼠标按下时，当前坐标位置
         */
        point.x = e.getX();
        point.y = e.getY();
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        // 当鼠标拖动时获取窗口当前位置
        Point p = window.getLocation();
        // 设置窗口的位置
        // 窗口当前的位置 + 鼠标当前在窗口的位置 - 鼠标按下的时候在窗口的位置
        window.setLocation(p.x + e.getX() - point.x, p.y + e.getY() - point.y);
    }
}
