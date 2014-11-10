package com.qq.friends;



import com.ui.window.PubFrame;

import java.awt.*;

/**
 * Created by lenovo on 2014/10/15.
 */
public class SearchFriendsFrame extends PubFrame {
    private SearchFriendsFrameContentPane searchFriendsFrameContentPane;

    public SearchFriendsFrame() throws HeadlessException {
        searchFriendsFrameContentPane = new SearchFriendsFrameContentPane(this);
        setContentPane(searchFriendsFrameContentPane);
        setSize(searchFriendsFrameContentPane.getSize());
        setLocationRelativeTo(null);
    }
}
