package qq.ui.window;

import com.san30.sim.pub.imagewindow.ImageDialog;

import javax.swing.*;
import java.awt.*;

/**
 * Created by lenovo on 2014/9/12.
 */
public class PubDialog extends JDialog{
    public PubDialog() {
        setUndecorated(true);
        setLayout(new BorderLayout());
        new WindowDraggedHandle(this);
    }
}
