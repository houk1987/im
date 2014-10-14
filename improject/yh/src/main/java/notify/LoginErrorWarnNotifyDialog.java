package notify;

import button.YhButtonFactory;
import com.component.jlabel.JLabelFactory;
import com.ui.LoginFrame;
import resource.YhImageRes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by lenovo on 2014/10/11.
 */
public class LoginErrorWarnNotifyDialog extends JDialog {


    public LoginErrorWarnNotifyDialog(LoginFrame owner,String title,boolean model) {
        super(owner,title,model);
        ContentPane contentPane = new ContentPane();
        setSize(contentPane.getSize());
        setContentPane(contentPane);
        setLocationRelativeTo(owner);
    }

    class ContentPane extends JPanel{
        private ImageIcon loginWarnTipMessage = YhImageRes.getImageIcon("loginErrorMessageBg.png");
        ContentPane() {
            setSize(loginWarnTipMessage.getIconWidth(),loginWarnTipMessage.getIconHeight());
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Image image = loginWarnTipMessage.getImage();
            g.drawImage(image,loginWarnTipMessage.getIconWidth(),loginWarnTipMessage.getIconHeight(),this);
        }
    }


}
