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
        searchFriendsConditionPane = new SearchFriendsConditionPane(this); //�����������
        searchFriendsRsListPane = new SearchFriendsRsListPane(); //����������
        searchFriendsRsListPane.setLocation(0, searchFriendsConditionPane.getHeight()); //���ý������λ��
        searchFriendsRsListPane.setSize(searchFriendsConditionPane.getWidth() - 1, height - searchFriendsConditionPane.getHeight()); //���ý�����ĸ߶�
        add(searchFriendsConditionPane);
        add(searchFriendsRsListPane); //�������������
        setSize(searchFriendsConditionPane.getWidth(),height);  //�������ĸ߶�
    }
}
