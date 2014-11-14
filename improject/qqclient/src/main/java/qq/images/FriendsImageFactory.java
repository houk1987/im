package qq.images;

import com.resource.ImageUtils;

import javax.swing.*;
import java.awt.*;

/**
 * Created by lenovo on 2014/11/11.
 */
public class FriendsImageFactory {
    private static FriendsImageFactory friendsImageFactory = new FriendsImageFactory();
    private static ImageUtils imageUtils;

    static {
        imageUtils = new ImageUtils("friends");
    }

    public static FriendsImageFactory getInstance() {
        return friendsImageFactory;
    }

    private FriendsImageFactory() {
    }

    public ImageIcon createSearchFriendsTopBg(){
        return imageUtils.getImageIcon("searchFriendsTopBg.png");
    }

    public ImageIcon createAddFriendsBg(){
        return imageUtils.getImageIcon("addFriendsBg.png");
    }

    public ImageIcon createTipIcon(){
        return imageUtils.getImageIcon("defaultTip.png");
    }
}
