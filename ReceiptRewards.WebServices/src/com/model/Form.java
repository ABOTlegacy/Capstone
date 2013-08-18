package com.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Form {

    private int formId;
    private List<FormElement> formElements;
    private List<FormFlow> formFlows;

    public Form() {
        this.formId = 0;
        this.formElements = new ArrayList<FormElement>();
        this.formFlows = new ArrayList<FormFlow>();
    }

    public Form(int formId, List<FormElement> formElements, List<FormFlow> formFlows) {
        this();
        this.formId = formId;
        this.formElements = formElements;
        this.formFlows = formFlows;
    }

    public int getFormId() {
        return formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
    }
    
    public List<FormElement> getFormElements() {
        return formElements;
    }

    public void setFormElements(List<FormElement> formElements) {
        this.formElements = formElements;
    }

    public List<FormFlow> getFormFlows() {
        return formFlows;
    }

    public void setFormFlows(List<FormFlow> formFlows) {
        this.formFlows = formFlows;
    }
    
    @Override
    public String toString() {
        // TODO: Implement
        return "";
    }
}