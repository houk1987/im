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
    private final Font font = new Font("΢���ź�", Font.PLAIN, 12);
    private SearchFriendsTopPane searchFriendsTopPane;
    private SearchFriendsRsListPane searchFriendsRsListPane;
    private JTextField keywordsTextField;  //�ؼ���
    private JButton searchButton; //������ť
    private JButton closeButton; //�رհ�ť
    private JButton minButton;


    public SearchFriendsFrame() throws HeadlessException {
        setLayout(null); //���ó����ɲ���
        searchFriendsTopPane = new SearchFriendsTopPane();  //����������Ϣ���
        add(searchFriendsTopPane);  //���������Ϣ���
        setSize(searchFriendsTopPane.getWidth(), frameHeight);//���ô��ڵĴ�С

        searchFriendsRsListPane = new SearchFriendsRsListPane(); //����������
        searchFriendsRsListPane.setLocation(0, searchFriendsTopPane.getHeight()); //���ý������λ��
        searchFriendsRsListPane.setSize(searchFriendsTopPane.getWidth() - 1, frameHeight - searchFriendsTopPane.getHeight()); //���ý�����ĸ߶�
        add(searchFriendsRsListPane); //�������������
    }

    class SearchFriendsTopPane extends ExtendPane implements ActionListener {
        SearchFriendsTopPane() {
            super(null, ImageUtils.getImageIcon("searchFriendsTopBg.png"));
            keywordsTextField = JTextFieldFactory.createJTextField(180, 21, Color.BLACK);//�ؼ��ʵ������

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
                        String textContent = doc.getText(0, doc.getLength()); //�����ı������������
                        defaultTipLabel.setVisible(textContent.isEmpty());
                    } catch (BadLocationException e1) {
                        e1.printStackTrace();
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    try {
                        Document doc = e.getDocument();
                        String textContent = doc.getText(0, doc.getLength()); //�����ı������������
                        defaultTipLabel.setVisible(textContent.isEmpty());
                    } catch (BadLocationException e1) {
                        e1.printStackTrace();
                    }
                }

                @Override
                public void changedUpdate(DocumentEvent e) {

                }
            });

            searchButton = QqButtonFactory.getInstance().createSearchButton(); //����
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
     * ����������б����
     */
    class SearchFriendsRsListPane extends JScrollPane {
        JPanel rsList;

        SearchFriendsRsListPane() {
            setBorder(null); //����Ϊ�ޱ߿�
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
        private JButton addFriendsButton;  //���
        private JLabel head;
        private JLabel nameLabel; //����
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
