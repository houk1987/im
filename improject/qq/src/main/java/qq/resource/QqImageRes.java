package qq.resource;

import javax.swing.*;

/**
 * Created by HK on 2014/10/4.
 */
public class QqImageRes {

    private final static String resSource = Qq.getResSource();

    public static ImageIcon getImageIcon(String imageName){
        if(imageName == null){
            return null;
        }
        return new ImageIcon(resSource+imageName);
    }

    public static ImageIcon getButtonImageIcon(String imageName){
        if(imageName == null){
            return null;
        }
        return new ImageIcon(resSource+"button/"+imageName);
    }

    public static ImageIcon getPresenceImageIcon(String imageName){
        if(imageName == null){
            return null;
        }
        return new ImageIcon(resSource+"presence/"+imageName);
    }
}
