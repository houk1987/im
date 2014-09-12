package com.imService.message;

import java.awt.*;

/**
 * Created by a on 2014/9/4.
 *
 */
public class FontStyle {
    private Color color; //������ɫ
    private int size=10; //�����С
    private boolean bold; //�Ƿ����
    private boolean italic;//�Ƿ�б��
    private String fontStyleName="����"; //������ʽ����

    public FontStyle(){

    }

    public FontStyle setColor(Color color){
        this.color = color;
        return this;
    }

    public FontStyle setSize(int size){
        this.size = size;
        return this;
    }

    public FontStyle setBold(boolean isBold){
        this.bold = isBold;
        return this;
    }

    public FontStyle setItalic(boolean isItalic){
        this.italic = isItalic;
        return this;
    }

    public Color getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }

    public boolean isBold() {
        return bold;
    }

    public boolean isItalic() {
        return italic;
    }

    public String getFontStyleName() {
        return fontStyleName;
    }

    public FontStyle setFontStyleName(String fontStyleName) {
        this.fontStyleName = fontStyleName;
        return this;
    }
}
