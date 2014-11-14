package images;

import com.resource.ImageUtils;

import javax.swing.*;

/**
 * Created by lenovo on 2014/11/11.
 */
public class CommonImagesFactroy {
    private static ImageUtils imageUtils;
    private static ImageUtils buttonImageUtils;

    static {
        imageUtils = new ImageUtils("common");
        buttonImageUtils = new ImageUtils("common/button");
    }


    public static ImageIcon createYahooMessenger(){
        return imageUtils.getImageIcon("YahooMessenger.png");
    }

    public static ImageIcon createIm(){
        return imageUtils.getImageIcon("im.png");
    }

    public static ImageIcon createPreviousDisable(){
        return buttonImageUtils.getImageIcon("previousDisable.png");
    }

    public static ImageIcon createNextDisable(){
        return buttonImageUtils.getImageIcon("nextDisable.png");
    }

    public static ImageIcon createCancelDisable(){
        return buttonImageUtils.getImageIcon("cancelDisable.png");
    }



}
