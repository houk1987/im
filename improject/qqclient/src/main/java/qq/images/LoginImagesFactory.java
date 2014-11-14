package qq.images;

import com.resource.ImageUtils;

import javax.swing.*;

/**
 * Created by lenovo on 2014/11/11.
 */
public class LoginImagesFactory {

    private static LoginImagesFactory loginImagesFactory = new LoginImagesFactory();
    private static ImageUtils imageUtils;

    static {
        imageUtils = new ImageUtils("login");
    }

    public static LoginImagesFactory getInstance() {
        return loginImagesFactory;
    }

    public ImageIcon getLoginFrameBg(){
        return imageUtils.getImageIcon("loginFrameBg.png");
    }
}
