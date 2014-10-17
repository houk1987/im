package qq.friends;

import com.component.ExtendPane;
import com.component.ImageUtils;
import com.component.jlabel.JLabelFactory;
import com.google.gson.Gson;
import com.resource.ConfigurationRes;
import com.san30.pub.tools.SanHttpClient;
import qq.ui.JTextField.JTextFieldFactory;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * �������������������
 * Created by lenovo on 2014/10/17.
 */
class SearchFriendsConditionPane extends ExtendPane implements ActionListener {
    private final ImageUtils imageUtils = ImageUtils.getInstance("friends/");
    private final Font font = new Font("΢���ź�", Font.PLAIN, 12);
    private final JLabel defaultTipLabel = JLabelFactory.createJLabel(imageUtils.getImageIcon("defaultTip.png"));
    private SearchFriendsFrameContentPane searchFriendsFrameContentPane;
    private SearchFriendsFrameButtonFactory searchFriendsFrameButtonFactory;
    private JTextField keywordsTextField;
    private JButton searchButton;
    private JButton closeWindowButton;
    private JButton minWindowButton;

    public SearchFriendsConditionPane(SearchFriendsFrameContentPane searchFriendsFrameContentPane) {
        super(null, ImageUtils.getInstance("friends/").getImageIcon("searchFriendsTopBg.png"));
        this.searchFriendsFrameContentPane = searchFriendsFrameContentPane;
        this.searchFriendsFrameButtonFactory = new SearchFriendsFrameButtonFactory();
        this.addCloseWindowButton();//��Ӵ��ڹرհ�ť
        this.addMinWindowButton();//��Ӵ�����С����ť
        this.addSearchButton();  //���������ť
        this.addKeyWordsText(); //��ӹؼ����ı������
    }

    /**
     * ��Ӵ��ڹرհ�ť
     */
    private void addCloseWindowButton() {
        closeWindowButton = searchFriendsFrameButtonFactory.createCloseWindowButton();
        closeWindowButton.setLocation(this.getWidth() - closeWindowButton.getWidth(), 0);
        addButton(closeWindowButton);
    }

    private void addSearchButton() {
        searchButton = searchFriendsFrameButtonFactory.createSearchFriendsButton(); //����
        searchButton.setLocation(745, 91);
        searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addButton(searchButton);
    }

    /**
     * ��Ӵ�����С����ť
     */
    private void addMinWindowButton() {
        minWindowButton = searchFriendsFrameButtonFactory.createMinWindowButton();
        minWindowButton.setLocation(this.getWidth() - closeWindowButton.getWidth() - minWindowButton.getWidth(), 0);
        addButton(minWindowButton);
    }

    private void addButton(JButton jButton) {
        add(jButton);
        jButton.addActionListener(this);
    }

    private void addKeyWordsText() {
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
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(closeWindowButton)) {
            searchFriendsFrameContentPane.searchFriendsFrame.dispose();
        } else if (e.getSource().equals(minWindowButton)) {
            searchFriendsFrameContentPane.searchFriendsFrame.setExtendedState(JFrame.ICONIFIED);
        } else if (e.getSource().equals(searchButton)) {
            HashMap<String, String> paramMap = new HashMap<>();
            paramMap.put("jid", keywordsTextField.getText());
            paramMap.put("type", "searchFriends");
            String url = "http://" + ConfigurationRes.getHostName() + ":" + 9090 + "/plugins/udpserver/addcontact";
            try {
                String rs = SanHttpClient.getDataAsString(url, paramMap);
                Gson gson = new Gson();
                java.util.List<String> list = gson.fromJson(rs, java.util.List.class);
                searchFriendsFrameContentPane.searchFriendsRsListPane.loadData(list);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
