package qq.friends;

import qq.ui.window.PubFrame;

import java.awt.*;

/**
 * Created by lenovo on 2014/10/15.
 */
public class SearchFriendsFrame extends PubFrame {
    private final static SearchFriendsFrame searchFriendsFrame = new SearchFriendsFrame();
    private SearchFriendsFrameContentPane searchFriendsFrameContentPane;

    public static SearchFriendsFrame getInstance() {
        return searchFriendsFrame;
    }

    private SearchFriendsFrame() throws HeadlessException {
        searchFriendsFrameContentPane = new SearchFriendsFrameContentPane(this);
        setContentPane(searchFriendsFrameContentPane);
        setSize(searchFriendsFrameContentPane.getSize());
        setLocationRelativeTo(null);
    }
}
