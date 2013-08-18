package com.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FormElement {

    private int formElementId;
    private Code code;
    private List<FormElementOption> formElementOptions;
    private List<FormElementAttribute> formElementAttributes;

    public FormElement() {
        this.formElementId = 0;
        this.code = null;
        this.formElementOptions = new ArrayList<FormElementOption>();
        this.formElementAttributes = new ArrayList<FormElementAttribute>();
    }
    
    public FormElement(int formElementId, Code code, List<FormElementOption> formElementOptions, List<FormElementAttribute> formElementAttributes) {
        this();
        this.formElementId = formElementId;
        this.code = code;
        this.formElementOptions = formElementOptions;
        this.formElementAttributes = formElementAttributes;
    }
    
    public int getFormElementId() {
        return this.formElementId;
    }
    
    public void setFormElementId(int formElementId) {
        this.formElementId = formElementId;
    }
    
    public Code getCode() {
        return this.code;
    }
    
    public void setCode(Code code) {
        this.code = code;
    }
    
    public List<FormElementOption> getFormElementOptions() {
        return this.formElementOptions;
    }
    
    public void setFormElementOptions(List<FormElementOption> formElementOptions) {
        this.formElementOptions = formElementOptions;
    }
    
    public List<FormElementAttribute> getFormElementAttributes() {
        return this.formElementAttributes;
    }
    
    public void setFormElementAttributes(List<FormElementAttribute> formElementAttributes) {
        this.formElementAttributes = formElementAttributes;
    }
    
    @Override
    public String toString() {
        // TODO: Implement
        return "";
    }
}
