package com.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DisplayTextTranslation {
    
    private int displayTextTranslationId;
    private int displayTextId;
    private Code code;
    private String value;
    
    public DisplayTextTranslation() {
        this.displayTextTranslationId = 0;
        this.displayTextId = 0;
        this.code = null;
        this.value = "";
    }
    
    public DisplayTextTranslation(int displayTextTranslationId, int displayTextId, Code code, String value) {
        this.displayTextTranslationId = displayTextTranslationId;
        this.displayTextId = displayTextId;
        this.code = code;
        this.value = value;
    }
    
    public int getDisplayTextTranslationId() {
        return displayTextTranslationId;
    }
    
    public void setDisplayTextTranslationId(int displayTextTranslationId) {
        this.displayTextTranslationId = displayTextTranslationId;
    }
    
    public int getDisplayTextId() {
        return displayTextId;
    }
    
    public void setDisplayTextId(int displayTextId) {
        this.displayTextId = displayTextId;
    }
    
    public Code getCode() {
        return code;
    }
    
    public void setCode(Code code) {
        this.code = code;
    }
    
    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        // TODO: Implement
        return "";
    }
}