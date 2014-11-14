package login;
import images.LoginImagesFactory;
import javax.swing.*;
import java.awt.*;

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
        private ImageIcon loginWarnTipMessage = LoginImagesFactory.createLoginWarnTipMessageBg();
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
