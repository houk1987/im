package com.component;

import com.resource.ConfigurationRes;

import javax.swing.*;

/**
 * Created by lenovo on 2014/10/14.
 */
public class ImageUtils {
    private String resPath;
    private static ImageUtils imageUtils;

    public static ImageUtils getInstance(String resPath) {
        if(imageUtils == null){
            imageUtils = new ImageUtils(resPath);
        }
        imageUtils.setResPath(ConfigurationRes.getImageResPath()+resPath);
        return imageUtils;
    }

    private ImageUtils(String resPath) {
        this.resPath = resPath;
    }

    private void setResPath(String resPath) {
        this.resPath = resPath;
    }

    public ImageIcon getImageIcon(String imageName){
        if(imageName == null){
            return null;
        }
        return new ImageIcon(resPath+imageName);
    }
}
