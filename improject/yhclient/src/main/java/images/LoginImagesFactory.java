package images;

import com.resource.ImageUtils;

import javax.swing.*;
import java.awt.*;

/**
 * ��½ģ��ͼƬ������
 * Created by lenovo on 2014/11/11.
 */
public class LoginImagesFactory {
    private static ImageUtils imageUtils;
    static {
        imageUtils = new ImageUtils("login");
    }

    private LoginImagesFactory() {
    }

    /**
     * ������½���汳��ͼƬ
     * @return
     */
    public static ImageIcon createLoginFrameBg(){
        return imageUtils.getImageIcon("loginFrameBg.png");
    }

    public static ImageIcon createLoginErrorMeesageBg(){return imageUtils.getImageIcon("loginErrorMessageBg.png");}

    public static ImageIcon createLoginWarnTipMessageBg(){return imageUtils.getImageIcon("loginWarnTipMessage.png");}
}
