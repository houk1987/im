package qq.images;

import com.resource.ImageUtils;

import javax.swing.*;

/**
 * Created by lenovo on 2014/11/11.
 */
public class SessionImagesFactory {

    private static SessionImagesFactory sessionImagesFactory = new SessionImagesFactory();
    private static ImageUtils imageUtils;

    static {
        imageUtils = new ImageUtils("session");
    }

    public static SessionImagesFactory getInstance() {
        return sessionImagesFactory;
    }

    public ImageIcon createSessionBg(){
        return imageUtils.getImageIcon("sessionFramebg.png");
    }
}
