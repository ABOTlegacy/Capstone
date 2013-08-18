package com.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FormFlow {

    private int formFlowId;
    private int formId;
    private Question question;
    private String value;

    public FormFlow() {
        this.formFlowId = 0;
        this.formId = 0;
        this.question = null;
        this.value = "";
    }
    
    public FormFlow(int formFlowId, int formId, Question question, String value) {
        this();
        this.formFlowId = formFlowId;
        this.formId = formId;
        this.question = question;
        this.value = value;
    }

    public int getFormFlowId() {
        return formFlowId;
    }

    public void setFormFlowId(int formFlowId) {
        this.formFlowId = formFlowId;
    }

    public int getFormId() {
        return formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
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
