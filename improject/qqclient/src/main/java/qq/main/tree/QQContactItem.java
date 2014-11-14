package qq.main.tree;

import com.ui.rosterTree.ContactItem;
import qq.images.CommonImagesFactory;
import qq.lunch.QQClient;
import qq.session.SessionFrame;
import javax.swing.*;
import java.awt.*;

/**
 * Created by lenovo on 2014/10/8.
 */
public class QQContactItem extends ContactItem {

    private final static Color hoverColor = new Color(252, 240, 193);
    private final static Color pressedColor = hoverColor;
    private final static ImageIcon headIcon = CommonImagesFactory.getInstance().createTitleIcon();

    public QQContactItem() {
    }

    public QQContactItem(String account,String userName) {
        setJid(account);
        setUserName(userName);
        setHoverColor(hoverColor);
        setPressedColor(pressedColor);
        setHeadIcon(headIcon);
    }

    @Override
    public void click(){
        if(!QQClient.getInstance().getLoginUserNameWithDomain().equals(getJid())){
            SessionFrame.CreateAndShowSessionFrame(this);
        }
    }
}
