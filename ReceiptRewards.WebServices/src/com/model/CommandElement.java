package com.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CommandElement {

    private int commandElementId;
    private Code code;
    private List<CommandIdentifier> commandIdentifiers;
    private List<CommandFormElementRelation> formElements;
    private String testData;

    public CommandElement() {
        this.commandElementId = 0;
        this.code = null;
        this.commandIdentifiers = new ArrayList<CommandIdentifier>();
        this.formElements = new ArrayList<CommandFormElementRelation>();
        this.testData = "";
    }

    public CommandElement(int commandElementId) {
        this();
        this.commandElementId = commandElementId;
    }

    public CommandElement(int commandElementId, Code code, List<CommandIdentifier> commandIdentifiers, List<CommandFormElementRelation> formElements, String testData) {
        this(commandElementId);
        this.code = code;
        this.commandIdentifiers = commandIdentifiers;
        this.formElements = formElements;
        this.testData = testData;
    }

    public int getCommandElementId() {
        return commandElementId;
    }

    public void setCommandElementId(int commandElementId) {
        this.commandElementId = commandElementId;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public List<CommandIdentifier> getCommandIdentifiers() {
        return commandIdentifiers;
    }

    public void setCommandIdentifiers(List<CommandIdentifier> commandIdentifiers) {
        this.commandIdentifiers = commandIdentifiers;
    }

    public List<CommandFormElementRelation> getFormElements() {
        return formElements;
    }

    public void setFormElements(List<CommandFormElementRelation> formElements) {
        this.formElements = formElements;
    }

    public String getTestData() {
        return testData;
    }

    public void setTestData(String testData) {
        this.testData = testData;
    }
    
    @Override
    public String toString() {
        // TODO: Implement
        return "";
    }
}