package com.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Question {

    private int questionId;
    private int revisionId;
    private String name;
    private Form form;
    private Automation automation;

    public Question() {
        this.questionId = 0;
        this.revisionId = 0;
        this.name = "";
        this.form = null;
        this.automation = null;
    }

    public Question(int questionId) {
        this();
        this.questionId = questionId;
        this.revisionId = 0;
        this.name = "";
        this.form = null;
        this.automation = null;
    }

    public Question(int questionId, int revisionId) {
        this(questionId);
        this.revisionId = revisionId;
        this.name = "";
        this.form = null;
        this.automation = null;
    }

    public Question(int questionId, int revisionId, String name, Form form, Automation automation) {
        this(questionId, revisionId);
        this.name = name;
        this.form = form;
        this.automation = automation;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getRevisionId() {
        return revisionId;
    }

    public void setRevisionId(int revisionId) {
        this.revisionId = revisionId;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public Automation getAutomation() {
        return automation;
    }

    public void setAutomation(Automation automation) {
        this.automation = automation;
    }
    
    @Override
    public String toString() {
        // TODO: Implement
        return "";
    }
}