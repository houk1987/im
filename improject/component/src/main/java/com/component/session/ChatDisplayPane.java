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
 * 信息显示面板
 */
public class ChatDisplayPane extends JScrollPane {
    public final JTextPane chatTextPane = new JTextPane();
    private final HTMLEditorKit editorKit;
    public HTMLDocument hdocument;

    private final Runnable scrollToBottomRunnable = new Runnable() {
        public void run() {
            JScrollBar verticalScrollBar = getVerticalScrollBar();
            if (verticalScrollBar != null)
                verticalScrollBar.setValue(verticalScrollBar.getMaximum());
        }
    };

    public ChatDisplayPane() {
        editorKit = new WrapLetterHTMLEditorKit();
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
        try {
            hdocument.setInnerHTML(hdocument.getDefaultRootElement(), "<body><table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"TABLE-LAYOUT: fixed;WORD-WRAP: break-word\"><tr id='start'><td> </td></tr> <tr id='end'><td> &nbsp;</td></tr></table></body>");
        }catch(Exception e){
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(scrollToBottomRunnable);
    }

    public void insertMessage(String html) {
        Element end = hdocument.getElement("end");
        try {
            hdocument.insertBeforeStart(end,html);
            SwingUtilities.invokeLater(scrollToBottomRunnable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
