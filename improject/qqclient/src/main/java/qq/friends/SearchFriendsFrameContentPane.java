package qq.friends;

import com.component.ExtendPane;

/**
 * Created by lenovo on 2014/10/17.
 */
class SearchFriendsFrameContentPane extends ExtendPane {
    private final int height = 600;
    SearchFriendsConditionPane searchFriendsConditionPane;
    SearchFriendsRsListPane searchFriendsRsListPane;
    SearchFriendsFrame searchFriendsFrame;
    public SearchFriendsFrameContentPane(SearchFriendsFrame searchFriendsFrame) {
        super(null, null);
        this.searchFriendsFrame = searchFriendsFrame;
        searchFriendsConditionPane = new SearchFriendsConditionPane(this); //搜索条件面板
        searchFriendsRsListPane = new SearchFriendsRsListPane(); //搜索结果面板
        searchFriendsRsListPane.setLocation(0, searchFriendsConditionPane.getHeight()); //设置结果面板的位置
        searchFriendsRsListPane.setSize(searchFriendsConditionPane.getWidth() - 1, height - searchFriendsConditionPane.getHeight()); //设置结果面板的高度
        add(searchFriendsConditionPane);
        add(searchFriendsRsListPane); //添加搜索结果面板
        setSize(searchFriendsConditionPane.getWidth(),height);  //设置面板的高度
    }
}
