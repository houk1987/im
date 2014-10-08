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
         * ���������
         * ������갴��ʱ����ǰ����λ��
         */
        point.x = e.getX();
        point.y = e.getY();
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        // ������϶�ʱ��ȡ���ڵ�ǰλ��
        Point p = window.getLocation();
        // ���ô��ڵ�λ��
        // ���ڵ�ǰ��λ�� + ��굱ǰ�ڴ��ڵ�λ�� - ��갴�µ�ʱ���ڴ��ڵ�λ��
        window.setLocation(p.x + e.getX() - point.x, p.y + e.getY() - point.y);
    }
}
