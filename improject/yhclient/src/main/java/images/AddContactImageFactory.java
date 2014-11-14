package images;

import com.resource.ImageUtils;

import javax.swing.*;

/**
 * Created by lenovo on 2014/11/11.
 */
public class AddContactImageFactory {

    private static ImageUtils imageUtils;

    static {
        imageUtils = new ImageUtils("addContact");
    }

    public static ImageIcon createAddContactBgFirst(){
        return imageUtils.getImageIcon("addContactBgFirst.png");
    }

    public static ImageIcon createAddContactSecondBg(){
        return imageUtils.getImageIcon("addContactSecondBg.png");
    }

    public static ImageIcon createThridBg(){
        return imageUtils.getImageIcon("thridBg.png");
    }

    public static ImageIcon createLineBg(){
        return imageUtils.getImageIcon("line.png");
    }

    public static ImageIcon createContactRightBg(){
        return imageUtils.getImageIcon("contactRightbg.png");
    }

}
