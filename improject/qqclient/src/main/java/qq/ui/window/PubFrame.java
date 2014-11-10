package qq.ui.window;

import javax.swing.*;
import java.awt.*;

/**
 * Created by lenovo on 2014/10/13.
 */
public class PubFrame extends JFrame {

    public PubFrame() throws HeadlessException {
        setUndecorated(true);
        setLayout(new BorderLayout());
        new WindowDraggedHandle(this);
    }
}
