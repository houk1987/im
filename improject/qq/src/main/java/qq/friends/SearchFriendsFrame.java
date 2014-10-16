package qq.friends;

import com.component.ExtendPane;
import com.component.ImageUtils;
import com.component.jlabel.JLabelFactory;
import com.google.gson.Gson;
import com.resource.ConfigurationRes;
import com.san30.pub.tools.SanHttpClient;
import qq.ui.JTextField.JTextFieldFactory;
import qq.ui.button.QqButtonFactory;
import qq.ui.window.PubFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by lenovo on 2014/10/15.
 */
public class SearchFriendsFrame extends PubFrame {

    private final int frameHeight = 600;
    private final JLabel defaultTipLabel = JLabelFactory.createJLabel(ImageUtils.getImageIcon("defaultTip.png"));
    private final Font font = new Font("微软雅黑", Font.PLAIN, 12);
    private SearchFriendsTopPane searchFriendsTopPane;
    private SearchFriendsRsListPane searchFriendsRsListPane;
    private JTextField keywordsTextField;  //关键词
    private JButton searchButton; //搜索按钮
    private JButton closeButton; //关闭按钮
    private JButton minButton;


    public SearchFriendsFrame() throws HeadlessException {
        setLayout(null); //设置成自由布局
        searchFriendsTopPane = new SearchFriendsTopPane();  //顶部搜索信息面板
        add(searchFriendsTopPane);  //添加搜索信息面板
        setSize(searchFriendsTopPane.getWidth(), frameHeight);//设置窗口的大小

        searchFriendsRsListPane = new SearchFriendsRsListPane(); //搜索结果面板
        searchFriendsRsListPane.setLocation(0, searchFriendsTopPane.getHeight()); //设置结果面板的位置
        searchFriendsRsListPane.setSize(searchFriendsTopPane.getWidth() - 1, frameHeight - searchFriendsTopPane.getHeight()); //设置结果面板的高度
        add(searchFriendsRsListPane); //添加搜索结果面板
    }

    class SearchFriendsTopPane extends ExtendPane implements ActionListener {
        SearchFriendsTopPane() {
            super(null, ImageUtils.getImageIcon("searchFriendsTopBg.png"));
            keywordsTextField = JTextFieldFactory.createJTextField(180, 21, Color.BLACK);//关键词的输入框

            keywordsTextField.setFont(font);
            keywordsTextField.setLocation(72, 78);
            this.add(keywordsTextField);
            defaultTipLabel.setLocation(5, 5);
            keywordsTextField.add(defaultTipLabel);
            Document keywordsDocument = keywordsTextField.getDocument();
            keywordsDocument.addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    try {
                        Document doc = e.getDocument();
                        String textContent = doc.getText(0, doc.getLength()); //返回文本框输入的内容
                        defaultTipLabel.setVisible(textContent.isEmpty());
                    } catch (BadLocationException e1) {
                        e1.printStackTrace();
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    try {
                        Document doc = e.getDocument();
                        String textContent = doc.getText(0, doc.getLength()); //返回文本框输入的内容
                        defaultTipLabel.setVisible(textContent.isEmpty());
                    } catch (BadLocationException e1) {
                        e1.printStackTrace();
                    }
                }

                @Override
                public void changedUpdate(DocumentEvent e) {

                }
            });

            searchButton = QqButtonFactory.getInstance().createSearchButton(); //创建
            searchButton.setLocation(745, 91);
            searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            searchButton.addActionListener(this);
            add(searchButton);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            HashMap<String, String> paramMap = new HashMap<>();
            paramMap.put("jid", keywordsTextField.getText());
            paramMap.put("type", "searchFriends");
            String url = "http://" + ConfigurationRes.getHostName() + ":" + 9090 + "/plugins/udpserver/addcontact";
            try {
                String rs = SanHttpClient.getDataAsString(url, paramMap);
                Gson gson = new Gson();
                java.util.List<String> list = gson.fromJson(rs,List.class);
                searchFriendsRsListPane.loadData(list);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 搜索结果的列表面板
     */
    class SearchFriendsRsListPane extends JScrollPane {
        JPanel rsList;

        SearchFriendsRsListPane() {
            setBorder(null); //设置为无边框
            rsList = new JPanel();
            setViewportView(rsList);
            rsList.setBackground(Color.WHITE);
        }

        public void loadData(List<String> list) {
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
                rsList.add(new AddFriends(username,name));
            }
        }
    }


    class AddFriends extends JPanel implements ActionListener {
        private JButton addFriendsButton;  //添加
        private JLabel head;
        private JLabel nameLabel; //名称
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
            head = JLabelFactory.createJLabel(ImageUtils.getImageIcon("headItem.png"));
            head.setLocation(15, 5);
            add(head);

            nameLabel = JLabelFactory.createJLabel(name, font, Color.BLACK);
            nameLabel.setBounds(head.getWidth() + 15, 5, 200, 23);
            add(nameLabel);

            addFriendsButton = QqButtonFactory.getInstance().createAddFriendsButton();
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

    public static void main(String[] args) {
        SearchFriendsFrame searchFriendsFrame = new SearchFriendsFrame();
        searchFriendsFrame.setLocationRelativeTo(null);
        searchFriendsFrame.setVisible(true);
    }
}
