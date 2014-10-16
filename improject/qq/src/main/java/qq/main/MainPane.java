package qq.main;

import com.component.ExtendPane;
import com.component.ImageUtils;
import qq.ui.button.QqButtonFactory;
import qq.main.tree.QQContactTree;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by lenovo on 2014/9/17.
 */
public class MainPane extends ExtendPane implements ActionListener{

    private QQContactTree qqContactTree;
    private JButton closeWindowButton; //�رմ��ڵİ�ť
    private JButton minWindowButton; //��С�����ڰ�ť
    private JButton searchFriendsButton; //�������Ѱ�ť

    public MainPane() {
        super(null,ImageUtils.getImageIcon("mainFramebg.jpg"));
        initLayout();
    }

    private void initLayout() {
        qqContactTree = new QQContactTree();
        qqContactTree.setBackground(Color.blue);
        qqContactTree.setSize(452, 440);
        qqContactTree.setLocation(0, 200);
        add(qqContactTree);

        searchFriendsButton = QqButtonFactory.getInstance().createSearchFriendsButton();  //�������ѵİ�ť
        searchFriendsButton.setLocation(400,500); //���ð�ť��λ��
        add(searchFriendsButton); //����������Ѱ�ť
        searchFriendsButton.addActionListener(this); //����¼�����

        //��ӹرհ�ť
        closeWindowButton = QqButtonFactory.getInstance().createMainDialogCloseButton();
        closeWindowButton.setLocation(this.getWidth()-closeWindowButton.getWidth(),0);
        add(closeWindowButton);
        closeWindowButton.addActionListener(this);

        //�����С�����ڰ�ť
        minWindowButton = QqButtonFactory.getInstance().createMinWindowButton();
        minWindowButton.setLocation(this.getWidth()-closeWindowButton.getWidth()-minWindowButton.getWidth(),0);
        add(minWindowButton);
        minWindowButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == searchFriendsButton){

        }
    }


}
