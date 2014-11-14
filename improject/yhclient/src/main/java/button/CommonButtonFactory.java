package button;

import com.ui.button.ButtonFactory;
import org.junit.internal.builders.JUnit3Builder;

import javax.swing.*;

/**
 * Created by lenovo on 2014/11/11.
 */
public class CommonButtonFactory extends ButtonFactory{

    private static CommonButtonFactory commonButtonFactory = new CommonButtonFactory();

    public static CommonButtonFactory getInstance() {
        return commonButtonFactory;
    }

    private CommonButtonFactory() {
        super("common");
    }

    public JButton createOkButton() {
        return createButton("ok.png");
    }

    public JButton createCancelButton() {
        return createButton("cancel.png");
    }

    public JButton createPreviousButton(){
        return createButton("previous.png");
    }

    public JButton createNextButton(){
        return createButton("next.png");
    }

    public JButton createFinishButton(){
        return createButton("finish.png");
    }
}
