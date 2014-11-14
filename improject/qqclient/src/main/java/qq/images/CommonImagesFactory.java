package qq.images;

import com.resource.ImageUtils;

import javax.swing.*;

/**
 * Created by lenovo on 2014/11/11.
 */
public class CommonImagesFactory {

    private static CommonImagesFactory commonImagesFactory = new CommonImagesFactory();

    private static ImageUtils imageUtils;

    static {
        imageUtils = new ImageUtils("common");
    }

    public static CommonImagesFactory getInstance() {
        return commonImagesFactory;
    }

    public ImageIcon createPicBox(){
        return imageUtils.getImageIcon("picbox.png");
    }

    public ImageIcon createHeadItem(){
        return imageUtils.getImageIcon("headItem.png");
    }

    public ImageIcon createTitleIcon(){
        return imageUtils.getImageIcon("TitleIcon.png");
    }

    public ImageIcon createIcon(){
        return imageUtils.getImageIcon("icon.png");
    }
}
