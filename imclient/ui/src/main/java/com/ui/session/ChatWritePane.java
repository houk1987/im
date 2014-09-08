package com.ui.session;


import com.imService.message.FontStyle;
import com.imService.message.SessionMessage;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.io.IOException;

/**
 * Created by a on 2014/8/22.
 * ��Ϣ�������
 */
public class ChatWritePane extends JScrollPane {
    private HTMLDocument document;
    public final JTextPane chatWriteTextPane = new JTextPane();
    private final HTMLEditorKit editorKit;
    private SessionMessage sessionMessage; //������Ϣ
    private FontStyle fontStyle;

    public ChatWritePane(SessionMessage sessionMessage) {
        System.setProperty("java.awt.im.style", "on-the-spot");
        this.sessionMessage = sessionMessage;
        this.fontStyle =  this.sessionMessage.getFontStyle();
        editorKit = new WrapLetterHTMLEditorKit();
        this.document = (HTMLDocument) editorKit.createDefaultDocument();
        this.chatWriteTextPane.setEditorKitForContentType("text/html", editorKit);
        this.chatWriteTextPane.setEditorKit(editorKit);
        this.chatWriteTextPane.setEditable(true);
        this.chatWriteTextPane.setDragEnabled(true);
        this.chatWriteTextPane.updateUI();
        this.setWheelScrollingEnabled(true);
        this.setViewportView(chatWriteTextPane);
        this.setBorder(null);
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        startNewDocument();
        try {
            int chatAreaSize = 60;
            Dimension writeMessagePanelDefaultSize = new Dimension(500, (chatAreaSize > 0) ? chatAreaSize : 28);
            Dimension writeMessagePanelMinSize = new Dimension(500, 28);
            Dimension writeMessagePanelMaxSize = new Dimension(500, 100);
            setMinimumSize(writeMessagePanelMinSize);
            setMaximumSize(writeMessagePanelMaxSize);
            setPreferredSize(writeMessagePanelDefaultSize);
            this.chatWriteTextPane.setCaretPosition(0);
            this.chatWriteTextPane.requestFocus();
            this.chatWriteTextPane.setCursor(new Cursor(Cursor.TEXT_CURSOR));
              applyNewCSS();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ��ʼ����һ���µ��ĵ�
     */
    public void startNewDocument() {
        document = (HTMLDocument) editorKit.createDefaultDocument();
        chatWriteTextPane.setDocument(document);
        applyNewCSS();
    }

    /**
     * Ӧ���µ�CSS��ʽ
     */
    public void applyNewCSS() {
        String css = getCSS().substring(0, getCSS().length() - 6);
        try {
            document.insertAfterStart(document.getDefaultRootElement(), css);
        } catch (BadLocationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.chatWriteTextPane.setDocument(document);
    }

    /**
     * ��ȡCSS�ַ���
     *
     * @return
     */
    private String getCSS() {
        StringBuffer css = new StringBuffer();
        css.append("<style type='text/css'>");//��<head>ȥ������ʽ���������˴˱�ǩ���޸�����ʱ���ݻ���\n dzc 20121008
        css.append(getlineHeightStyle());
        css.append("<body>");
        return css.toString();
    }

    private String getlineHeightStyle() {
        StringBuffer css = new StringBuffer();
        css.append("p{color:#");
        css.append(fontStyle.getColor());
        css.append(";font-size:");
        css.append(fontStyle.getSize());
        css.append("pt;font-family:");
        css.append(fontStyle.getFontStyleName());
        css.append(";");
        css.append("line-height:50px;vertical-align:middle;");
        if(fontStyle.isBold()){
            css.append(getBoldCSS(true));
            css.append(getItalicCSS(false));
        }else{
            css.append(getBoldCSS(false));
            css.append(getItalicCSS(false));
        }

        if(fontStyle.isItalic()){
            css.append(getBoldCSS(false));
            css.append(getItalicCSS(true));
        }else{
            css.append(getBoldCSS(false));
            css.append(getItalicCSS(false));
        }
        css.append("}");
        return css.toString();
    }

    // ��ȡ����CSS
    private String getBoldCSS(boolean isBold) {
        return isBold ? "font-weight:bold;" : "font-weight:normal;";
    }

    // ��ȡб��CSS
    private String getItalicCSS(boolean isItalic) {
        return isItalic ? "font-style:italic;" : "font-style:none;";
    }

    /**
     * ��ȡ��Ϣ������еĴ��ı�
     *
     * @return
     */
    public String getPlainText() {
        try {
            Document doc = chatWriteTextPane.getDocument();
            return doc.getText(0, doc.getLength());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        return null;
    }


    public SessionMessage getSessionMessage() {
        return sessionMessage;
    }

    /**
     * �������¸���ķ�����Ŀ����Ϊ����ʾ���ù�������λ�ã���Ȼ����������ʾ��ȫ
     * zbq 2013-4-25 �޸�7898bug:�Ự���ڱ༭��������ַ�����������һ��û����ʾ����
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (this.getVerticalScrollBar().isVisible())
            this.getVerticalScrollBar().setValue(this.getVerticalScrollBar().getMaximum());
    }




}
