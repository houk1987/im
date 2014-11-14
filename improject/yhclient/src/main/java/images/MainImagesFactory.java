package images;

import com.resource.ImageUtils;

import javax.swing.*;
import java.awt.*;

/**
 * Created by lenovo on 2014/11/11.
 */
public class MainImagesFactory {
    private static ImageUtils imageUtils;
    private static ImageUtils presenceImageUtils;

    static {
        imageUtils = new ImageUtils("main");
        presenceImageUtils = new ImageUtils("main/presenceSelectButton");
    }

    public static ImageIcon createMainFrameBg(){
        return imageUtils.getImageIcon("mainFrame.png");
    }

    public static ImageIcon createRightBg(){
        return imageUtils.getImageIcon("rightBg.png");
    }

    public static ImageIcon createLeftIsSelected(){
        return imageUtils.getImageIcon("mainFrame.png");
    }

    public static ImageIcon createRightPressed(){
        return imageUtils.getImageIcon("rightPressed.png");
    }

    public static ImageIcon PresenceSelectLeft(){

            return presenceImageUtils.getImageIcon("leftDefault.png");
    }

    public static ImageIcon PresenceSelectLeftHover(){
        return presenceImageUtils.getImageIcon("leftHover.png");
    }

    public static ImageIcon PresenceSelectLeftPressed(){
        return presenceImageUtils.getImageIcon("leftPressed.png");
    }


    public static ImageIcon PresenceSelectCenter(){
        return presenceImageUtils.getImageIcon("centerDefault.png");
    }

    public static ImageIcon PresenceSelectCenterHover(){
        return presenceImageUtils.getImageIcon("centerHover.png");
    }

    public static ImageIcon PresenceSelectCenterPressed(){
        return presenceImageUtils.getImageIcon("centerPressed.png");
    }

    public static ImageIcon PresenceSelectRight(){
        return presenceImageUtils.getImageIcon("rightDefault.png");
    }

    public static ImageIcon PresenceSelectRightHover(){
        return presenceImageUtils.getImageIcon("rightHover.png");
    }

    public static ImageIcon PresenceSelectRightPressed(){
        return presenceImageUtils.getImageIcon("rightPressed.png");
    }
}
