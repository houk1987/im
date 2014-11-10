package resource;

import javax.swing.*;

/**
 * Created by HK on 2014/10/4.
 */
public class YhImageRes{

    private final static String resSource = Yh.getResSource();

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

    public static ImageIcon getPresenceSelectButtonImageIcon(String imageName){
        if(imageName == null){
            return null;
        }
        return new ImageIcon(resSource+"presenceSelectButton/"+imageName);
    }



}
