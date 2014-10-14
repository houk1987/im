package com.component;

import com.resource.ConfigurationRes;

import javax.swing.*;

/**
 * Created by lenovo on 2014/10/14.
 */
public class ImageUtils {

    private final static String resPath = ConfigurationRes.getImageResPath();
    private final static String buttonResPath = ConfigurationRes.getButtonImageResPath();
    private final static String presenceResPath = ConfigurationRes.getPresenceImageResPath();

    public static ImageIcon getImageIcon(String imageName){
        if(imageName == null){
            return null;
        }
        return new ImageIcon(resPath+imageName);
    }

    public static ImageIcon getButtonImageIcon(String imageName){
        if(imageName == null){
            return null;
        }
        return new ImageIcon(buttonResPath+imageName);
    }

    public static ImageIcon getPresenceImageIcon(String imageName){
        if(imageName == null){
            return null;
        }
        return new ImageIcon(presenceResPath+imageName);
    }
}
