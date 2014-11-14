package login;
import button.CommonButtonFactory;
import com.ui.jlabel.JLabelFactory;
import images.LoginImagesFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by HK on 2014/10/5.
 */
public class WarnNotifyDialog extends JDialog {
    private ImageIcon loginWarnTipMessage = LoginImagesFactory.createLoginWarnTipMessageBg();
    private JButton okButton = CommonButtonFactory.getInstance().createOkButton();

    public WarnNotifyDialog(LoginFrame owner,String title,boolean model) {
        super(owner,title,model);
        setLocationRelativeTo(owner);
        setContentPane(new ContentPane());
        getRootPane().setDefaultButton(okButton);
        okButton.requestFocus();
    }

    class ContentPane extends JPanel{
        ContentPane() {
            setBackground(new Color(119,36,111));
            setLayout(null);
            JLabel loginWarnTipMessageLabel = JLabelFactory.createJLabel(loginWarnTipMessage);
            loginWarnTipMessageLabel.setLocation(5,5);
            add(loginWarnTipMessageLabel);
            okButton.setLocation(65, 40);
            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            add(okButton);
        }
    }
}
