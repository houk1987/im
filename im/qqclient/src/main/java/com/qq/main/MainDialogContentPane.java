package com.qq.main;

import com.comunication.connection.ConnectionManager;
import com.qq.button.*;
import com.qq.button.Button;
import com.qq.friends.FriendsManager;
import com.qq.main.tree.QQContactTree;
import com.ui.image.ImageUtils;
import com.ui.jlabel.JLabelFactory;
import com.ui.pane.ExtendPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by lenovo on 2014/9/17.
 */
class MainDialogContentPane extends ExtendPane implements ActionListener{
    private com.qq.button.Button button;
    private QQContactTree friendsTree;
    private JButton closeWindowButton; //�رմ��ڵİ�ť
    private JButton minWindowButton; //��С�����ڰ�ť
    private JButton searchFriendsButton; //�������Ѱ�ť
    private MainDialog mainDialog;

    MainDialogContentPane(MainDialog mainDialog) {
        super(null, ImageUtils.getInstance("resources/images/main/").getImageIcon("mainDialogBg.png"));
        this.mainDialog = mainDialog;
        button = new Button(new MainDialogButtonFactory());
        this.addCloseWindowButton();
        this.addMinWindowButton();
        this.addSearchAddFriendsButton();
        this.addShowAccountPane();
        this.addFriendsTree();
    }

    /**
     * ��Ӵ��ڹرհ�ť
     */
    private void addCloseWindowButton(){
        closeWindowButton = button.createButton(MainDialogButtonFactory.CLOSE_WINDOW);
        closeWindowButton.setLocation(this.getWidth()-closeWindowButton.getWidth(),0);
        addButton(closeWindowButton);
    }

    /**
     * ��Ӵ�����С����ť
     */
    private void addMinWindowButton(){
        minWindowButton = button.createButton(MainDialogButtonFactory.MIN_WINDOW);
        minWindowButton.setLocation(this.getWidth() - closeWindowButton.getWidth() - minWindowButton.getWidth(), 0);
        addButton(minWindowButton);
    }

    /**
     * ��Ӳ�����Ӻ��Ѱ�ť
     */
    private void addSearchAddFriendsButton(){
        searchFriendsButton =button.createButton(MainDialogButtonFactory.SEARCH_FRIENDS);
        searchFriendsButton.setLocation(170,this.getHeight()-searchFriendsButton.getHeight()-1); //���ð�ť��λ��
        addButton(searchFriendsButton); //����������Ѱ�ť
    }

    private void addButton(JButton jButton){
        add(jButton);
        jButton.addActionListener(this);
    }

    private void addShowAccountPane(){
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(40,138,221));
        panel.setBounds(5,50,150,50);
        FriendsManager friendsManager = FriendsManager.getInstance();
        JLabel label = JLabelFactory.createJLabel(friendsManager.getName(ConnectionManager.getUserName()),new Font("����", Font.BOLD, 14), Color.WHITE);
        label.setBounds(0,0,50,50);
        panel.add(label);


//        final JLabel presenceLabel = JLabelFactory.createJLabel(PresenceManager.getOnline());
//        presenceLabel.setLocation(50,70);
//        presenceLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        add(presenceLabel);
//        presenceLabel.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseReleased(MouseEvent e) {
//                PresenceMenu statusMenu = new PresenceMenu(presenceLabel);
//                statusMenu.show(MainDialogContentPane.this,50,85);
//            }
//        });
        add(panel);
    }

    /**
     * ��Ӻ�����
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
            //SearchFriendsFrame searchFriendsFrame = new SearchFriendsFrame();
            //searchFriendsFrame.setVisible(true);
        }else if(e.getSource() == closeWindowButton){
            mainDialog.dispose();
        }else if(e.getSource() == minWindowButton){
            mainDialog.dispose();
        }
    }

    public QQContactTree getFriendsTree() {
        return friendsTree;
    }
}
