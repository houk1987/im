package images;

import com.resource.ImageUtils;

import javax.swing.*;
import java.awt.*;

/**
 * 登陆模块图片工厂类
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
     * 创建登陆界面背景图片
     * @return
     */
    public static ImageIcon createLoginFrameBg(){
        return imageUtils.getImageIcon("loginFrameBg.png");
    }

    public static ImageIcon createLoginErrorMeesageBg(){return imageUtils.getImageIcon("loginErrorMessageBg.png");}

    public static ImageIcon createLoginWarnTipMessageBg(){return imageUtils.getImageIcon("loginWarnTipMessage.png");}
}
