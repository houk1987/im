package qq.ui.pub;

import javax.swing.*;

/**
 * Created by lenovo on 2014/9/16.
 */
public class PubDialog extends JDialog {

    public PubDialog() {
        //ȥ������ʽ
        setUndecorated(true);
        //�����ק���ڵ�ʵ��
        MouseDraggedFrameHandel mouseDraggedFrameHandel = new MouseDraggedFrameHandel(this);
        this.addMouseListener(mouseDraggedFrameHandel);
        this.addMouseMotionListener(mouseDraggedFrameHandel);
    }

    public static void main(String[] args) {
        PubDialog pubDialog = new PubDialog();
        pubDialog.setSize(500, 500);
        pubDialog.setLocationRelativeTo(null);
        pubDialog.setVisible(true);
    }
}
