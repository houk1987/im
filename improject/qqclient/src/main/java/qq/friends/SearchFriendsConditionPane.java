package qq.friends;

import com.google.gson.Gson;
import com.resource.ConfigurationRes;
import com.san30.pub.tools.SanHttpClient;
import com.ui.jlabel.JLabelFactory;
import com.ui.pane.ExtendPane;
import qq.images.FriendsImageFactory;
import qq.ui.JTextField.JTextFieldFactory;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * 搜索的条件的区域面板
 * Created by lenovo on 2014/10/17.
 */
class SearchFriendsConditionPane extends ExtendPane implements ActionListener {
    private final Font font = new Font("微软雅黑", Font.PLAIN, 12);
    private final JLabel defaultTipLabel = JLabelFactory.createJLabel(FriendsImageFactory.getInstance().createTipIcon());
    private SearchFriendsFrameContentPane searchFriendsFrameContentPane;
    private SearchFriendsFrameButtonFactory searchFriendsFrameButtonFactory;
    private JTextField keywordsTextField;
    private JButton searchButton;
    private JButton closeWindowButton;
    private JButton minWindowButton;

    public SearchFriendsConditionPane(SearchFriendsFrameContentPane searchFriendsFrameContentPane) {
        super(null, FriendsImageFactory.getInstance().createSearchFriendsTopBg());
        this.searchFriendsFrameContentPane = searchFriendsFrameContentPane;
        this.searchFriendsFrameButtonFactory = new SearchFriendsFrameButtonFactory();
        this.addCloseWindowButton();//添加窗口关闭按钮
        this.addMinWindowButton();//添加窗口最小化按钮
        this.addSearchButton();  //添加搜索按钮
        this.addKeyWordsText(); //添加关键字文本输入框
    }

    /**
     * 添加窗口关闭按钮
     */
    private void addCloseWindowButton() {
        closeWindowButton = searchFriendsFrameButtonFactory.createCloseWindowButton();
        closeWindowButton.setLocation(this.getWidth() - closeWindowButton.getWidth(), 0);
        addButton(closeWindowButton);
    }

    private void addSearchButton() {
        searchButton = searchFriendsFrameButtonFactory.createSearchFriendsButton(); //创建
        searchButton.setLocation(745, 91);
        searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addButton(searchButton);
    }

    /**
     * 添加窗口最小化按钮
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
