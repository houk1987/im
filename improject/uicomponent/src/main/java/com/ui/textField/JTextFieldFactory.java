package com.ui.textField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by hq on 2014/11/2.
 */
public class JTextFieldFactory {

    public static FreeTextField createJTextField(int width,int height){
        FreeTextField jTextField = new FreeTextField();
        jTextField.setSize(width,height);
        setBorder(jTextField);
        return jTextField;
    }

    public static JPasswordField createJPasswordField(int width,int height,char echoChar){
        JPasswordField jPasswordField = new JPasswordField();
        jPasswordField.setSize(width,height);
        jPasswordField.setEchoChar(echoChar);
        setBorder(jPasswordField);
        return jPasswordField;
    }

    private static void setBorder(JTextField jTextField){
        Color BORDER_COLOR = new Color(210, 210, 210, 0);
        jTextField.setBorder(BorderFactory.createCompoundBorder(new LineBorder(BORDER_COLOR, 1), new EmptyBorder(new Insets(2, 2, 2, 2))));
    }
}
