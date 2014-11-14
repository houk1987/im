package button;

import com.ui.button.ButtonFactory;

import javax.swing.*;

/**
 * Created by lenovo on 2014/11/11.
 */
public class AddContactButtonFactory extends ButtonFactory {
    
    private static AddContactButtonFactory addContactButtonFactory = new AddContactButtonFactory();

    public static AddContactButtonFactory getInstance() {
        return addContactButtonFactory;
    }
    
    protected AddContactButtonFactory() {
        super("addContact/button");
    }


    public JButton createAddBuddy(){
        return createButton("addBuddy.png");
    }

    
}
