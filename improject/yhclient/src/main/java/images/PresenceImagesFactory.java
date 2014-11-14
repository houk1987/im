package images;

import com.resource.ImageUtils;

import javax.swing.*;

/**
 * Created by lenovo on 2014/11/11.
 */
public class PresenceImagesFactory {

    private static PresenceImagesFactory presenceImagesFactory = new PresenceImagesFactory();
    private static ImageUtils imageUtils;

    static{
        imageUtils = new ImageUtils("presence");
    }

    public static PresenceImagesFactory getInstance() {
        return presenceImagesFactory;
    }

    private PresenceImagesFactory(){

    };

    public ImageIcon createOnLine(){
        return imageUtils.getImageIcon("online.png");
    }

    public ImageIcon createOffLine(){
        return imageUtils.getImageIcon("offline.png");
    }

    public ImageIcon createBusy(){
        return imageUtils.getImageIcon("busy.png");
    }
}
