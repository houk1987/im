package com.component.session;

import javax.swing.*;
import javax.swing.text.Element;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by a on 2014/8/22.
 * ��Ϣ��ʾ���
 */
public class ChatDisplayPane extends JScrollPane {
    public final JTextPane chatTextPane = new JTextPane();
    private final HTMLEditorKit editorKit;
    public HTMLDocument hdocument;

    private final Runnable scrollToBottomRunnable = new Runnable() {
        public void run() {
//            JScrollBar verticalScrollBar = getVerticalScrollBar();
//            if (verticalScrollBar != null)
//                verticalScrollBar.setValue(verticalScrollBar.getMaximum());
        }
    };

    public ChatDisplayPane() {
        editorKit = new HTMLEditorKit();
        this.hdocument = (HTMLDocument) editorKit.createDefaultDocument();
        this.chatTextPane.setEditorKitForContentType("text/html", editorKit);
        this.chatTextPane.setEditorKit(editorKit);
        this.chatTextPane.setEditable(false);
        this.chatTextPane.setDocument(hdocument);
        this.chatTextPane.setDragEnabled(true);
        this.chatTextPane.updateUI();
        this.chatTextPane.setCursor(
                new Cursor(Cursor.TEXT_CURSOR));
        this.setWheelScrollingEnabled(true);
        this.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        chatTextPane.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES,
                Boolean.TRUE);
        this.setViewportView(chatTextPane);
        chatTextPane.setLayout(null);
        this.setBorder(BorderFactory.createEmptyBorder());
//        try {
//            hdocument.setInnerHTML(hdocument.getDefaultRootElement(), "<body><table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"TABLE-LAYOUT: fixed;WORD-WRAP: break-word\"><tr id='start'><td> </td></tr> <tr id='end'><td> &nbsp;</td></tr></table></body>");
//        }catch(Exception e){
//            e.printStackTrace();
//        }
    }

    public void insertMessage(String html) {
        Element end = hdocument.getElement("end");
        try {
            hdocument.insertAfterStart(hdocument.getDefaultRootElement(),html);

            //SwingUtilities.invokeLater(scrollToBottomRunnable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {


        JFrame jFrame = new JFrame();
        ChatDisplayPane chatDisplayPane = new ChatDisplayPane();
        chatDisplayPane.insertMessage(getContentHtml("1"));
        jFrame.setContentPane(chatDisplayPane);
        jFrame.setSize(500, 500);
        jFrame.setVisible(true);
    }

    public static String getContentHtml(String content) {

        String s = "<body >\n" +
                "<body><table>" +
                "<tr> <td style=\"padding-top:2pt\" align=\"left\">" +
                "<table width=\"68%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"TABLE-LAYOUT: fixed;WORD-WRAP: break-word\" >" +
                "<td width=\"16\" height=\"21\" background=\"#top1url#\"  ></td>" +
                "<td bubbleid=\"#messageid#\"  msgtype=\"#msgtype#\"  background=\"#top2url#\"   style=\"line-height:21pt; color:#333; font-size:12pt\">" +
                "houk1987  2014-10-08 16:46:37</td>" +
                "<td width=\"500pt\" background=\"#top3url#\"></td>  </tr> " +
                "<tr> <td valign=\"top\" width=\"16\"  background=\"#center1url#\" style=\"background-repeat:repeat-y\"><div background=\"#center2url#\"   style=\"width:16pt; height:13pt; background-repeat:no-repeat;\"></div></td><td style=\"WORD-BREAK: break-all; WORD-WRAP: break-word\">1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111</td></tr>\n" +
                "</table></body>";
        System.out.println(s);
        return s;
    }
}
