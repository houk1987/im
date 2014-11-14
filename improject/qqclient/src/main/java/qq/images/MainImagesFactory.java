package qq.images;

import com.resource.ImageUtils;

import javax.swing.*;

/**
 * Created by lenovo on 2014/11/11.
 */
public class MainImagesFactory {

    private static MainImagesFactory mainImagesFactory = new MainImagesFactory();
    private static ImageUtils imageUtils;

    static {
        imageUtils = new ImageUtils("main");
    }

    public static MainImagesFactory getInstance() {
        return mainImagesFactory;
    }

    public ImageIcon createMainFrameBg(){
        return imageUtils.getImageIcon("mainDialogBg.png");
    }
}
