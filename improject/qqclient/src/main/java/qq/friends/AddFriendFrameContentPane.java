package qq.friends;

import com.component.ExtendPane;
import com.resource.ImageUtils;
import com.component.jlabel.JLabelFactory;
import com.component.rosterTree.ContactItem;
import qq.lunch.QQClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by lenovo on 2014/10/20.
 */
public class AddFriendFrameContentPane extends ExtendPane implements ActionListener{
    private SearchFriendsFrameButtonFactory searchFriendsFrameButtonFactory;
    private AddFriendFrame addFriendFrame;
    private JButton nextButton;         //下一步
    private JButton cancelButton;      //取消
    private JButton finishButton;       //完成
    private JPanel finishPane;
    private JButton closeWindowButton;
    private JButton minWindowButton;


    public AddFriendFrameContentPane(AddFriendFrame addFriendFrame) {
        super(null, ImageUtils.getInstance("friends/").getImageIcon("addFriendsBg.png"));
        searchFriendsFrameButtonFactory = new SearchFriendsFrameButtonFactory();
        this.addFriendFrame = addFriendFrame;
        this.addCloseWindowButton();
        this.addMinWindowButton();
        this.addCloseButton();
        this.addFinishButton();
        this.addNextButton();
        this.addFinishPane();
        this.addAccountPane();
    }

    /**
     * 添加窗口关闭按钮
     */
    private void addCloseWindowButton() {
        closeWindowButton = searchFriendsFrameButtonFactory.createCloseFriendsFrame();
        closeWindowButton.setLocation(this.getWidth() - closeWindowButton.getWidth(), 0);
        addButton(closeWindowButton);
    }

    /**
     * 添加窗口最小化按钮
     */
    private void addMinWindowButton() {
        minWindowButton = searchFriendsFrameButtonFactory.createMinFriendsFrame();
        minWindowButton.setLocation(this.getWidth() - closeWindowButton.getWidth() - minWindowButton.getWidth(), 0);
        addButton(minWindowButton);
    }


    private void addNextButton(){
        nextButton = searchFriendsFrameButtonFactory.createNextButton();
        nextButton.setLocation(this.getWidth()-cancelButton.getWidth()-16-nextButton.getWidth(),this.getHeight()-cancelButton.getHeight()-4);
        addButton(nextButton);
    }

    private void addCloseButton(){
        cancelButton = searchFriendsFrameButtonFactory.createCloseButton();
        cancelButton.setLocation(this.getWidth()-cancelButton.getWidth()-8,this.getHeight()-cancelButton.getHeight()-4);
        addButton(cancelButton);
    }

    private void addFinishButton(){
        finishButton = searchFriendsFrameButtonFactory.createFinishButton();
        finishButton.setLocation(this.getWidth()-finishButton.getWidth()-8,this.getHeight()-finishButton.getHeight()-4);
        finishButton.setVisible(false);
        addButton(finishButton);
    }

    private void addButton(JButton jButton){
        add(jButton);
        jButton.addActionListener(this);
    }

    private void addFinishPane(){
        finishPane = new JPanel(null);
        finishPane.setBounds(140,40,400,100);
        finishPane.setBackground(Color.WHITE);
        JLabel label = new JLabel();
        boolean isFriend = QQClient.getInstance().getFriendsManager().isFriend(addFriendFrame.getJid());
        if(isFriend) {
            finishPane.setVisible(true);
            label.setText(addFriendFrame.getJid()+"已经为你的好友");
            finishButton.setVisible(true);
            nextButton.setVisible(false);
            cancelButton.setVisible(false);
       initTempPane();
        }else{
            finishPane.setVisible(false);
            label.setText("已发出了好友申请。");
        }
        label.setBounds(0,0,200,50);
        finishPane.add(label);
        add(finishPane);
    }

    /**
     * 帐号面板
     */
    private void addAccountPane(){
        JPanel accountPane = new JPanel(null);
        accountPane.setBackground(new Color(243,247,251));
        accountPane.setBounds(10,180,120,140);
        add(accountPane);
        JLabel nameLabel = JLabelFactory.createJLabel(addFriendFrame.getName(),new Font("宋体",Font.BOLD,12),Color.BLACK);
        JLabel accountLabel = JLabelFactory.createJLabel(addFriendFrame.getJid(),new Font("宋体",Font.PLAIN,12),Color.BLACK);
        nameLabel.setBounds(5,0,100,30);
        accountLabel.setBounds(5,10,100,50);
        accountPane.add(nameLabel);
        accountPane.add(accountLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         if(e.getSource().equals(nextButton)){
             QQClient.getInstance().getFriendsManager().sendFriendApply(addFriendFrame.getJid()); //发送好友请求
             finishPane.setVisible(true);
             nextButton.setVisible(false);
             finishButton.setVisible(true);
             cancelButton.setVisible(false);
             initTempPane();
         }else if(e.getSource().equals(cancelButton)){
             addFriendFrame.dispose();
         }else if(e.getSource().equals(finishButton)){
            addFriendFrame.dispose();
         }else if(e.getSource().equals(closeWindowButton)){
             addFriendFrame.dispose();
         }else if(e.getSource().equals(minWindowButton)){
             addFriendFrame.setExtendedState(JFrame.ICONIFIED);
         }
    }

    private void initTempPane(){
        JPanel temp = new JPanel();
        temp.setBackground(new Color(228,234,245));
        temp.setSize(nextButton.getSize());
        temp.setLocation(this.getWidth() - cancelButton.getWidth() - 16 - nextButton.getWidth(), this.getHeight() - cancelButton.getHeight() - 4);
        add(temp);
    }
}
