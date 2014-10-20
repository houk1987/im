package qq.main;

import com.component.ExtendPane;
import com.component.ImageUtils;
import com.resource.ConfigurationRes;

import qq.friends.SearchFriendsFrame;
import qq.main.tree.QQContactTree;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by lenovo on 2014/9/17.
 */
class MainDialogContentPane extends ExtendPane implements ActionListener{
    private MainDialogButtonFactory mainDialogButtonFactory;
    private QQContactTree friendsTree;
    private JButton closeWindowButton; //关闭窗口的按钮
    private JButton minWindowButton; //最小化窗口按钮
    private JButton searchFriendsButton; //搜索好友按钮
    private MainDialog mainDialog;

    MainDialogContentPane(MainDialog mainDialog) {
        super(null,ImageUtils.getInstance("main/").getImageIcon("mainDialogBg.png"));
        this.mainDialog = mainDialog;
        mainDialogButtonFactory = new MainDialogButtonFactory();
        this.addCloseWindowButton();
        this.addMinWindowButton();
        this.addSearchAddFriendsButton();
        this.addFriendsTree();
    }

    /**
     * 添加窗口关闭按钮
     */
    private void addCloseWindowButton(){
        closeWindowButton = mainDialogButtonFactory.createCloseWindowButton();
        closeWindowButton.setLocation(this.getWidth()-closeWindowButton.getWidth(),0);
        addButton(closeWindowButton);
    }

    /**
     * 添加窗口最小化按钮
     */
    private void addMinWindowButton(){
        minWindowButton = mainDialogButtonFactory.createMinWindowButton();
        minWindowButton.setLocation(this.getWidth() - closeWindowButton.getWidth() - minWindowButton.getWidth(), 0);
        addButton(minWindowButton);
    }

    /**
     * 添加查找添加好友按钮
     */
    private void addSearchAddFriendsButton(){
        searchFriendsButton =mainDialogButtonFactory.createSearchFriendsButton();  //搜索好友的按钮
        searchFriendsButton.setLocation(170,this.getHeight()-searchFriendsButton.getHeight()-1); //设置按钮的位置
        addButton(searchFriendsButton); //添加搜索好友按钮
    }

    private void addButton(JButton jButton){
        add(jButton);
        jButton.addActionListener(this);
    }

    /**
     * 添加好友树
     */
    private void addFriendsTree(){
        friendsTree = new QQContactTree();
        friendsTree.setBackground(Color.blue);
        friendsTree.setSize(452, 440);
        friendsTree.setLocation(0, 200);
        add(friendsTree);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == searchFriendsButton){
            SearchFriendsFrame.getInstance().setVisible(true);
        }else if(e.getSource() == closeWindowButton){
            mainDialog.dispose();
            System.exit(0);
        }else if(e.getSource() == minWindowButton){
            mainDialog.dispose();
        }
    }

    public QQContactTree getFriendsTree() {
        return friendsTree;
    }
}
