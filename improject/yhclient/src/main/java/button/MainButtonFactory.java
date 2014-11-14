package button;

import com.ui.button.ButtonFactory;

import javax.swing.*;

/**
 * Created by lenovo on 2014/11/11.
 */
public class MainButtonFactory extends ButtonFactory {
    private static MainButtonFactory mainButtonFactory = new MainButtonFactory();
    protected MainButtonFactory() {
        super("main/button");
    }

    public static MainButtonFactory getInstance() {
        return mainButtonFactory;
    }

    public JButton createRightButton(){
        return createButton("right.png");
    }

    public JButton createLeftButton(){
        return createButton("left.png");
    }

}
