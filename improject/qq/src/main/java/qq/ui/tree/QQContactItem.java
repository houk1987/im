package qq.ui.tree;

import com.component.ImageUtils;
import com.component.rosterTree.ContactItem;
import qq.ui.session.SessionFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by lenovo on 2014/10/8.
 */
public class QQContactItem extends ContactItem {

    private final static Color hoverColor = new Color(252, 240, 193);
    private final static Color pressedColor = hoverColor;
    private final static ImageIcon headIcon = ImageUtils.getImageIcon("tree/" + "head.png");

    public QQContactItem(String account,String userName) {
        setJid(account);
        setUserName(userName);
        setHoverColor(hoverColor);
        setPressedColor(pressedColor);
        setHeadIcon(headIcon);
    }

    @Override
    public void click(){
        SessionFrame.CreateAndShowSessionFrame(this);
    }
}
