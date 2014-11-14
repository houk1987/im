package com.resource;
import javax.swing.*;

/**
 * Created by lenovo on 2014/10/14.
 */
public class ImageUtils {

    private static String resPath = ConfigurationRes.getPath();
    private String imageFullPath;

    /**
     * ͼƬ���� �ļ���·��
     * @param imageDirPath
     */
    public ImageUtils(String imageDirPath) {
        this.imageFullPath = resPath+imageDirPath+"/";
    }

    public ImageIcon getImageIcon(String imageName){
        if(imageName == null){
            return null;
        }
        return new ImageIcon(imageFullPath+imageName);
    }
}
