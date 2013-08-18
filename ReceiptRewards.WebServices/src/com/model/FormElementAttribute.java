package com.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FormElementAttribute {

    private int formElementAttributeId;
    private int formElementId;
    private Code code;
    private String value;

    public FormElementAttribute() {
        this.formElementAttributeId = 0;
        this.formElementId = 0;
        this.code = null;
        this.value = "";
    }


    public FormElementAttribute(int formElementAttributeId, int formElementId, Code code, String value) {
        this();
        this.formElementAttributeId = formElementAttributeId;
        this.formElementId = formElementId;
        this.code = code;
        this.value = value;
    }

    public int getFormElementAttributeId() {
        return formElementAttributeId;
    }

    public void setFormElementAttributeId(int formElementAttributeId) {
        this.formElementAttributeId = formElementAttributeId;
    }

    public int getFormElementId() {
        return formElementId;
    }

    public void setFormElementId(int formElementId) {
        this.formElementId = formElementId;
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