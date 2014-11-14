package images;

import com.resource.ImageUtils;

import javax.swing.*;

/**
 * Created by lenovo on 2014/11/11.
 */
public class TreeImagesFactory {

    private static ImageUtils imageUtils;

    static {
        imageUtils = new ImageUtils("tree");
    }

    public static ImageIcon createHeadItem(){
        return imageUtils.getImageIcon("headItem.png");
    }
}
