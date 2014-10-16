package qq.main;

import com.component.ExtendPane;
import com.component.ImageUtils;
import com.resource.ConfigurationRes;

import qq.main.tree.QQContactTree;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by lenovo on 2014/9/17.
 */
public class MainPane extends ExtendPane implements ActionListener{
    private MainDialogButtonFactory mainDialogButtonFactory;
    private QQContactTree friendsTree;
    private JButton closeWindowButton; //关闭窗口的按钮
    private JButton minWindowButton; //最小化窗口按钮
    private JButton searchFriendsButton; //搜索好友按钮

    public MainPane() {
        super(null,ImageUtils.getInstance(ConfigurationRes.getImageResPath()+"main/").getImageIcon("mainDialogBg.png"));
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
        add(minWindowButton);
    }

    /**
     * 添加查找添加好友按钮
     */
    private void addSearchAddFriendsButton(){
        searchFriendsButton =mainDialogButtonFactory.createSearchFriendsButton();  //搜索好友的按钮
        searchFriendsButton.setLocation(400,500); //设置按钮的位置
        add(searchFriendsButton); //添加搜索好友按钮
        searchFriendsButton.addActionListener(this); //添加事件监听
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

        }
    }


}
