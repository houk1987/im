package qq.ui.pub;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * ȥ������ʽ��JFrame
 * ����������ק����
 * Created by lenovo on 2014/9/16.
 */
public class PubFrame extends JFrame {
    private Point point;
    public PubFrame() throws HeadlessException {
        //ȥ������ʽ
        setUndecorated(true);
        //�����ק���ڵ�ʵ��
        MouseDraggedFrameHandel mouseDraggedFrameHandel = new MouseDraggedFrameHandel(this);
        this.addMouseListener(mouseDraggedFrameHandel);
        this.addMouseMotionListener(mouseDraggedFrameHandel);
    }

    public static void main(String[] args) {
        PubFrame pubFrame = new PubFrame();
        pubFrame.setSize(500,500);
        pubFrame.setLocationRelativeTo(null);
        pubFrame.setVisible(true);
    }
}
