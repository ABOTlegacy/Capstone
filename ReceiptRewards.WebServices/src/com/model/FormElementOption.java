
package com.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FormElementOption {

    private int formElementOptionId;
    private int formElementId;
    private DisplayText displayText;
    private String value;

    public FormElementOption() {
        this.formElementOptionId = 0;
        this.formElementId = 0;
        this.displayText = new DisplayText();
        this.value = "";
    }

    public FormElementOption(int formElementOptionId, int formElementId, DisplayText displayText, String value) {
        this();
        this.formElementOptionId = formElementOptionId;
        this.formElementId = formElementId;
        this.displayText = displayText;
        this.value = value;
    }

    public int getFormElementOptionId() {
        return formElementOptionId;
    }

    public void setFormElementOptionId(int formElementOptionId) {
        this.formElementOptionId = formElementOptionId;
    }

    public int getFormElementId() {
        return formElementId;
    }

    public void setFormElementId(int formElementId) {
        this.formElementId = formElementId;
    }

    public DisplayText getDisplayText() {
        return displayText;
    }

    public void setDisplayText(DisplayText displayText) {
        this.displayText = displayText;
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