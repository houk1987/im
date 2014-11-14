package images;

import com.resource.ImageUtils;

import javax.swing.*;

/**
 * Created by lenovo on 2014/11/11.
 */
public class SessionImageFactory {

    private static SessionImageFactory sessionImageFactory = new SessionImageFactory();

    public static SessionImageFactory getInstance() {
        return sessionImageFactory;
    }
    
    private static ImageUtils imageUtils;

    static{
        imageUtils = new ImageUtils("session");
    }

    private SessionImageFactory() {
    }

    public ImageIcon createToolbarBg(){
        return imageUtils.getImageIcon("toolbarBg.png");
    }

    public ImageIcon createToolbarBg2(){
        return imageUtils.getImageIcon("toolbarBg2.png");
    }
}
