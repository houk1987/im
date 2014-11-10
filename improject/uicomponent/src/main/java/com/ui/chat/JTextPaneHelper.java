package com.ui.chat;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Created by hq on 2014/11/1.
 */
public class JTextPaneHelper {
    private static final Pattern URL_PATTERN
            = Pattern.compile(
            "("
                    + "((http|ftp|https)://[a-zA-Z_0-9]*\\.([a-zA-Z_0-9[:&=;?/\\.%-]])*)" // wwwURL
                    + "|"
                    + "((http|ftp|https)://[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}[:a-zA-Z_0-9[&=;?/\\.%-]]*)"
//         + "|"+
//         + "(\\b\\w+://[^\\s<>\"]+/*[?#]*(\\w+[&=;?]\\w+)*\\b)" // protocolURL
                    + ")");
    /**
     *
     * ȡJEditorPane�����е�HTML���롣
     * Ҫ��JEditorPane�����ݱ�����"text/html"��ʽ��������δ���и�ʽ�жϣ�������ǣ����������ת����
     * @param editor JEditorPane����
     * @return html�ַ���
     * @throws java.io.IOException
     * @throws javax.swing.text.BadLocationException
     */
    public static String getHtml(JTextPane editor){
        // ȡ�ĵ�����������Ϊһ����HTML��ʽ�ĵ���
        HTMLDocument hdoc = (HTMLDocument)editor.getDocument();
        // �����ڴ�������������HTML���ݡ�
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        OutputStream os = new BufferedOutputStream(baos);
        // ȡJDK��HTMLEditor�Ĺ��߶��󣬲��������
        HTMLEditorKit editorKit = (HTMLEditorKit)editor.getEditorKit();
        try{
            editorKit.write(os, hdoc, 0, hdoc.getLength());
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return baos.toString();
    }
    public static String getHtml(JEditorPane editor, Element element){
        // ȡ�ĵ�����������Ϊһ����HTML��ʽ�ĵ���
        HTMLDocument hdoc = (HTMLDocument)editor.getDocument();
        // �����ڴ�������������HTML���ݡ�
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        OutputStream os = new BufferedOutputStream(baos);
        // ȡJDK��HTMLEditor�Ĺ��߶��󣬲��������
        HTMLEditorKit editorKit = (HTMLEditorKit)editor.getEditorKit();
        try {
            editorKit.write(os, hdoc, element.getStartOffset(), element.getEndOffset() - element.getStartOffset());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return baos.toString().replaceAll("<html>", "").replaceAll("</html>", "").replaceAll("<body>", "").replaceAll("</body>", "");
    }
    public static String getText(Element element){
        // ȡ�ĵ�����������Ϊһ����HTML��ʽ�ĵ���
        HTMLDocument hdoc = (HTMLDocument)element.getDocument();
        try {
            return hdoc.getText(element.getStartOffset(), element.getEndOffset() - element.getStartOffset());
        } catch (BadLocationException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String getText(JEditorPane editor, String id) throws BadLocationException{
        // ȡ�ĵ�����������Ϊһ����HTML��ʽ�ĵ���
        HTMLDocument hdoc = (HTMLDocument)editor.getDocument();
        Element element = hdoc.getElement(id);
        if (element == null){ // δ�ҵ�Ҫ���
            return null;
        }
        return getText(element);
    }

    /**����ʶ���û���Ϣ�е�URL����
     * @param message
     * @param processHTMLChars
     * @return
     */
    public static String processLinksAndHTMLChars(String message,boolean processHTMLChars) {
        Matcher m = URL_PATTERN.matcher(message);
        StringBuffer msgBuffer = new StringBuffer();
        int prevEnd = 0;

        while (m.find()) {
            String fromPrevEndToStart = message.substring(prevEnd, m.start());

            if (processHTMLChars)
                fromPrevEndToStart = processHTMLChars(fromPrevEndToStart);
            msgBuffer.append(fromPrevEndToStart);
            prevEnd = m.end();
            String url = m.group().trim();
            if(m.group().endsWith("&"))
                url = m.group().trim().substring(0, m.group().trim().length()-1);
            msgBuffer.append("<font color =\"blue\"><A id=\"userdeflink\" href=\"");
            if (url.startsWith("www"))
                msgBuffer.append("http://");
            msgBuffer.append(url);
            msgBuffer.append("\">");
            msgBuffer.append(url);
            msgBuffer.append("</A></font>");
            if(m.group().endsWith("&"))
                msgBuffer.append("&");
        }
        String fromPrevEndToEnd = message.substring(prevEnd);
        if (processHTMLChars)
            fromPrevEndToEnd = processHTMLChars(fromPrevEndToEnd);
        msgBuffer.append(fromPrevEndToEnd);
//		System.out.println(msgBuffer.toString());
        return msgBuffer.toString();
    }
    /**
     * �÷�����Ҫ�Իظ���ַ����a��ǩ����
     * @param message
     * @return
     */
    public static String transerUrl(String message) {
        Matcher m = URL_PATTERN.matcher(message);
        StringBuffer msgBuffer = new StringBuffer();
        int prevEnd = 0;
        while (m.find()) {
            String fromPrevEndToStart = message.substring(prevEnd, m.start());
            if(fromPrevEndToStart.contains("<A id=\"userdeflink\" href=\"")){
                fromPrevEndToStart = fromPrevEndToStart.replaceAll("<A id=\"userdeflink\" href=\"", "");
                msgBuffer.append(fromPrevEndToStart);
                prevEnd = m.end();
                continue;
            }
            String url = m.group().trim();
            prevEnd = m.end();
            msgBuffer.append(url);
        }
        String fromPrevEndToEnd = message.substring(prevEnd);
        msgBuffer.append(fromPrevEndToEnd);
        return msgBuffer.toString();
    }
    /**
     * ֻ��ʶ��URL���ܱ���html��ʽ����
     * @param message
     * @return
     */
    public static String processLinksAndHTMLChars(String message) {
        Matcher m = URL_PATTERN.matcher(message);
        StringBuffer msgBuffer = new StringBuffer();
        int prevEnd = 0;
        msgBuffer.append("<xmp>");
        while (m.find()) {
            String fromPrevEndToStart = message.substring(prevEnd, m.start());
            msgBuffer.append(fromPrevEndToStart);
            prevEnd = m.end();
            String url = m.group().trim();
            if(m.group().endsWith("&"))
                url = m.group().trim().substring(0, m.group().trim().length()-1);
            msgBuffer.append("</xmp>");
            msgBuffer.append("<font color =\"blue\"><A id=\"userdeflink\" href=\"");
            if (url.startsWith("www"))
                msgBuffer.append("http://");
            msgBuffer.append(url);
            msgBuffer.append("\">");
            msgBuffer.append(url);
            msgBuffer.append("</A></font>");
            if(m.group().endsWith("&"))
                msgBuffer.append("&");
            msgBuffer.append("<xmp>");
        }
        String fromPrevEndToEnd = message.substring(prevEnd);
        msgBuffer.append(fromPrevEndToEnd);
        msgBuffer.append("</xmp>");
        return msgBuffer.toString();
    }

    private static  String processHTMLChars(String message) {
        return
                message
                        .replace("&", "&amp;")
                        .replace("<", "&lt;")
                        .replace(">", "&gt;")
                        .replace("\"", "&quot;");
    }
}
