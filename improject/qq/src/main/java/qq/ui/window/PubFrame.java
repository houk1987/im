package qq.ui.window;

import com.san30.sim.pub.imagewindow.ImageFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by lenovo on 2014/9/11.
 */
public class PubFrame extends ImageFrame implements WindowButtonHandel{

    private Title title;

    public PubFrame(Title title) {
        setLayout(new BorderLayout());
        add(new TitlePane(title,this),BorderLayout.NORTH);
    }

    @Override
    public void close() {
        dispose();
    }

    @Override
    public void max() {
        setExtendedState(JFrame.MAXIMIZED_BOTH); //����״̬Ϊ���
    }

    @Override
    public void min() {
        setExtendedState(JFrame.ICONIFIED); //ͼ�껯
    }

    @Override
    public void restore() {
        setExtendedState(JFrame.NORMAL);
    }
}
