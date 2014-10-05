package com.component;

import javax.swing.*;

/**
 * Created by HK on 2014/10/5.
 */
public class JLabelFactory {

    private static JLabelFactory jLabelFactory = new JLabelFactory();

    private JLabelFactory() {
    }

    public static JLabelFactory getInstance() {
        return jLabelFactory;
    }
    
    
    public JLabel createJLabelWithImage(ImageIcon imageIcon){
        if(imageIcon == null)return null;
        JLabel jLabel = new JLabel(imageIcon);
        jLabel.setSize(imageIcon.getIconWidth(),imageIcon.getIconHeight());
        return jLabel;
    }
}
