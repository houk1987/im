package qq.ui.window;

import com.san30.sim.pub.imagewindow.ImageDialog;

import java.awt.*;

/**
 * Created by lenovo on 2014/9/12.
 */
public class PubDialog extends ImageDialog implements WindowButtonHandel{
    private Title title;

    public PubDialog() {
    }

    public PubDialog(Frame owner,Title title) {
        super(owner);
        setLayout(new BorderLayout());
        add(new TitlePane(title,this),BorderLayout.NORTH);
    }

    public PubDialog(Dialog owner,Title title) {
        super(owner);
        setLayout(new BorderLayout());
        add(new TitlePane(title,this), BorderLayout.NORTH);
    }



    @Override
    public void close() {
        dispose();
    }

    @Override
    public void max() {

    }

    @Override
    public void min() {

    }

    @Override
    public void restore() {

    }


}
