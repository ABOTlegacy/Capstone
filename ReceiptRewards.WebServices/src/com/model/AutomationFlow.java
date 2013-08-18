package com.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AutomationFlow {

    private int automationFlowId;
    private int automationId;
    private Question question;
    private int weight;

    public AutomationFlow() {
        this.automationFlowId = 0;
        this.automationId = 0;
        this.question = null;
        this.weight = 0;
    }
    
    public AutomationFlow(int automationFlowId, int automationId, Question question, int weight) {
        this();
        this.automationFlowId = automationFlowId;
        this.automationId = automationId;
        this.question = question;
        this.weight = weight;
    }

    public int getAutomationFlowId() {
        return automationFlowId;
    }

    public void setAutomationFlowId(int automationFlowId) {
        this.automationFlowId = automationFlowId;
    }

    public int getAutomationId() {
        return automationId;
    }

    public void setAutomationId(int automationId) {
        this.automationId = automationId;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
    
    @Override
    public String toString() {
    	// TODO: Implement
        return "";
    }
}
