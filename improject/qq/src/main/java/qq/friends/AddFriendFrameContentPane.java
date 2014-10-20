package qq.friends;

import com.component.ExtendPane;
import com.component.ImageUtils;
import com.component.jlabel.JLabelFactory;
import org.smackservice.RosterManager;
import org.smackservice.SmackConnection;

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



    public AddFriendFrameContentPane(AddFriendFrame addFriendFrame) {
        super(null, ImageUtils.getInstance("friends/").getImageIcon("addFriendsBg.png"));
        searchFriendsFrameButtonFactory = new SearchFriendsFrameButtonFactory();
        this.addFriendFrame = addFriendFrame;
        this.addCloseButton();
        this.addFinishButton();
        this.addNextButton();
        this.addFinishPane();
    }

    private void addNextButton(){
        nextButton = searchFriendsFrameButtonFactory.createNextButton();
        nextButton.setLocation(this.getWidth()-cancelButton.getWidth()-15-nextButton.getWidth(),this.getHeight()-cancelButton.getHeight()-3);
        addButton(nextButton);
    }

    private void addCloseButton(){
        cancelButton = searchFriendsFrameButtonFactory.createCloseButton();
        cancelButton.setLocation(this.getWidth()-cancelButton.getWidth()-7,this.getHeight()-cancelButton.getHeight()-3);
        addButton(cancelButton);
    }

    private void addFinishButton(){
        finishButton = searchFriendsFrameButtonFactory.createFinishButton();
        finishButton.setLocation(this.getWidth()-finishButton.getWidth()-7,this.getHeight()-finishButton.getHeight()-3);
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
        if(RosterManager.getRosterEntry(addFriendFrame.getJid()+"@"+ SmackConnection.getInstance().getServiceName())!=null) {
            finishPane.setVisible(true);
            label.setText(addFriendFrame.getJid()+"已经为你的好友");
            finishButton.setVisible(true);
            nextButton.setEnabled(true);
        }else{
            finishPane.setVisible(false);
            label.setText("已发出了好友申请。");
        }

        label.setBounds(0,0,200,50);
        finishPane.add(label);
        add(finishPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         if(e.getSource().equals(nextButton)){
             finishPane.setVisible(true);
         }else if(e.getSource().equals(cancelButton)){
             addFriendFrame.dispose();
         }else if(e.getSource().equals(finishButton)){
            addFriendFrame.dispose();
         }
    }
}
