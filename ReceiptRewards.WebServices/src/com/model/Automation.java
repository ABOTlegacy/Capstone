package com.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Automation {

    private int automationId;
    private List<CommandElement> commandElements;
    private List<AutomationFlow> automationFlows;

    public Automation() {
        this.automationId = 0;
        this.commandElements = new ArrayList<CommandElement>();
        this.automationFlows = new ArrayList<AutomationFlow>();
    }

    public Automation(int automationId, List<CommandElement> commandElements, List<AutomationFlow> automationFlows) {
        this();
        this.automationId = automationId;
        this.commandElements = commandElements;
        this.automationFlows = automationFlows;
    }

    public int getAutomationId() {
        return automationId;
    }

    public void setAutomationId(int automationId) {
        this.automationId = automationId;
    }
    
    public List<CommandElement> getCommandElements() {
        return commandElements;
    }

    public void setCommandElements(List<CommandElement> commandElements) {
        this.commandElements = commandElements;
    }

    public List<AutomationFlow> getAutomationFlows() {
        return automationFlows;
    }

    public void setAutomationFlows(List<AutomationFlow> automationFlows) {
        this.automationFlows = automationFlows;
    }
    
    @Override
    public String toString() {
        // TODO: Implement
        return "";
    }
}