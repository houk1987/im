package qq.friends;

import com.component.ImageUtils;
import com.component.jlabel.JLabelFactory;
import com.resource.ConfigurationRes;
import qq.ui.headPicture.HeadPicture;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Created by lenovo on 2014/10/17.
 */
public class SearchFriendsRsListPane extends JScrollPane {
    JPanel rsList;
    private final Font font = new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 12);
    private SearchFriendsFrameButtonFactory searchFriendsFrameButtonFactory;
    SearchFriendsRsListPane() {
        setBorder(null); //…Ë÷√Œ™Œﬁ±ﬂøÚ
        searchFriendsFrameButtonFactory = new SearchFriendsFrameButtonFactory();
        rsList = new JPanel();
        setViewportView(rsList);
        rsList.setBackground(Color.WHITE);
    }

    public void loadData(java.util.List<String> list) {
        int rows = 1;
        int col = 5;
        if (list.size() > 5) {
            rows = list.size() / col + 1;
        } else {
            col = list.size();
        }
        GridLayout gridLayout = new GridLayout(rows, col);
        rsList.setLayout(gridLayout);
        if (col < 5) {
            rsList.setBorder(new EmptyBorder(0, 0, 0, 550));
        }
        for (int i = 0; i < list.size(); i++) {
            String[] splitData = list.get(i).split(",");
            String username = splitData[0];
            String name = splitData[1];
            if (name == null || name.isEmpty()) {
                name = username;
            }
            rsList.add(new AddFriends(username, name));
        }
    }

    class AddFriends extends JPanel implements ActionListener {
        private JButton addFriendsButton;  //ÃÌº”
        private JLabel head;
        private JLabel nameLabel; //√˚≥∆
        private final int width = 150;
        private String userName;
        private String name;

        AddFriends(String userName, String name) {
            this.userName = userName;
            this.name = name;
            initAddFriends();
        }

        private void initAddFriends() {
            setLayout(null);
            head = JLabelFactory.createJLabel(ImageUtils.getInstance("common/").getImageIcon("headItem.png"));
            head.setLocation(15, 5);
            add(head);

            nameLabel = JLabelFactory.createJLabel(name, font, Color.BLACK);
            nameLabel.setBounds(head.getWidth() + 15, 5, 200, 23);
            add(nameLabel);

            addFriendsButton = searchFriendsFrameButtonFactory.createAddFriendsButton();
            addFriendsButton.setLocation(head.getWidth() + 15, head.getHeight() - addFriendsButton.getHeight());
            addFriendsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            addFriendsButton.addActionListener(this);
            add(addFriendsButton);

        }

        @Override
        public Dimension getPreferredSize() {
            int height = head.getHeight() + addFriendsButton.getHeight();
            return new Dimension(width, height);
        }

        @Override
        public Color getBackground() {
            return Color.WHITE;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == addFriendsButton) {

            }
        }
    }
}
