package com.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DisplayText {

    private int displayTextId;
    private String displayTextTranslation;
    private List<DisplayTextTranslation> displayTextTranslations;

    public DisplayText() {
        this.displayTextId = 0;
        this.displayTextTranslation = "";
        this.displayTextTranslations = new ArrayList<DisplayTextTranslation>();
    }

    public DisplayText(int displayTextId, List<DisplayTextTranslation> displayTextTranslations) {
        this.displayTextId = displayTextId;
        this.displayTextTranslation = displayTextTranslations.get(0).getValue();
        this.displayTextTranslations = displayTextTranslations;
    }
    
    public DisplayText(int displayTextId, String displayTextTranslation, List<DisplayTextTranslation> displayTextTranslations) {
        this.displayTextId = displayTextId;
        this.displayTextTranslation = displayTextTranslation;
        this.displayTextTranslations = displayTextTranslations;
    }

    public int getDisplayTextId() {
        return this.displayTextId;
    }

    public void setDisplayTextId(int displayTextId) {
        this.displayTextId = displayTextId;
    }

    public String getDisplayTextTranslation() {
        return this.displayTextTranslation;
    }

    public void setDisplayTextTranslation(String displayTextTranslation) {
        this.displayTextTranslation = displayTextTranslation;
    }
    
    public List<DisplayTextTranslation> getDisplayTextTranslations() {
        return this.displayTextTranslations;
    }

    public void setDisplayTextTranslations(List<DisplayTextTranslation> displayTextTranslations) {
        this.displayTextTranslations = displayTextTranslations;
    }
    
    @Override
    public String toString() {
        // TODO: Implement
        return "";
    }
}
