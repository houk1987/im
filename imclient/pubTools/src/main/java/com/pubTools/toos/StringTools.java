package com.pubTools.toos;

/**
 * Created by a on 2014/7/14.
 */
public class StringTools {
    /**
     * ���ַ���ģʽƥ��ʱ���ؼ����а����������ַ������滻 �� $ ( ) { [�ȡ�
     *
     * @param input
     * @return
     */
    public static String escapeSpecialChar(String input) {
        char ch = ' ';
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < input.length(); i++) {
            ch = input.charAt(i);
            if (ch == '$')
                buf.append("\\$");
            else if (ch == '(')
                buf.append("\\(");
            else if (ch == ')')
                buf.append("\\)");
            else if (ch == '|')
                buf.append("\\|");
            /***********bug7261 ������ʽ�쳣, start 2013-2-27, wxb********/
            else if (ch == '{')
                buf.append("\\{");
            else if (ch == '[')
                buf.append("\\[");
            /***********bug7261 ������ʽ�쳣, end 2013-2-27, wxb*********/
            else
                buf.append(ch);
        }
        return buf.toString();
    }

    public static String nullConverToString(String str){
        if(str == null){
            return "";
        }
        return str;
    }
}
