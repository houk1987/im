package qq.ui.main;

import com.component.ExtendPane;
import com.component.ImageUtils;
import qq.ui.tree.QQContactTree;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by lenovo on 2014/9/17.
 */
public class MainPane extends ExtendPane implements ActionListener{

    private QQContactTree qqContactTree;
    private JButton closeWindowButton; //关闭窗口的按钮
    private JButton minWindowButton; //最小化窗口按钮

    public MainPane() {
        super(null,ImageUtils.getImageIcon("mainFramebg.jpg"));
        initLayout();
    }

    private void initLayout() {
        setLayout(null);
        qqContactTree = new QQContactTree();
        qqContactTree.setBackground(Color.blue);
        qqContactTree.setSize(452, 440);
        qqContactTree.setLocation(0, 200);
        add(qqContactTree);

//        //添加关闭按钮
//        closeWindowButton = QqButtonFactory.getInstance().createCloseWindowButton();
//        closeWindowButton.setLocation(this.getWidth()-closeWindowButton.getWidth(),0);
//        add(closeWindowButton);
//        closeWindowButton.addActionListener(this);
//
//        //添加最小化窗口按钮
//        minWindowButton = QqButtonFactory.getInstance().createMinWindowButton();
//        minWindowButton.setLocation(this.getWidth()-closeWindowButton.getWidth()-minWindowButton.getWidth(),0);
//        add(minWindowButton);
//        minWindowButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
