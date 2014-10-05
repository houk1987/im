package com.ui.notify;

import com.component.JLabelFactory;
import com.ui.button.YhButtonFactory;
import com.ui.resource.YhImageRes;

import javax.swing.*;
import java.awt.*;

/**
 * Created by HK on 2014/10/5.
 */
public class WarnNotifyDialog extends JDialog {

    private ImageIcon loginWarnTipMessage = YhImageRes.getImageIcon("loginWarnTipMessage.png");
    private JButton okButton = YhButtonFactory.getInstance().createOkButton();

    public WarnNotifyDialog(Frame owner,String title,boolean model) {
        super(owner,title,model);
        setLocationRelativeTo(owner);
        setContentPane(new ContentPane());

    }

    class ContentPane extends JPanel{
        ContentPane() {
            setBackground(new Color(119,36,111));
            setLayout(null);


            JLabel loginWarnTipMessageLabel = JLabelFactory.getInstance().createJLabelWithImage(loginWarnTipMessage);
            loginWarnTipMessageLabel.setLocation(5,5);
            add(loginWarnTipMessageLabel);

            okButton.setLocation(65,40);
            add(okButton);
        }
    }
}
