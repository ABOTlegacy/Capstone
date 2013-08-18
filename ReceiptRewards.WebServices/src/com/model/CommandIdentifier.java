package com.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CommandIdentifier {

    private int commandIdentifierId;
    private int commandElementId;
    private Code code;
    private String value;

    public CommandIdentifier() {
        this.commandIdentifierId = 0;
        this.code = null;
        this.value = "";
    }

    public CommandIdentifier(int commandIdentifierId) {
        this();
        this.commandIdentifierId = commandIdentifierId;
    }
    
    public CommandIdentifier(int commandIdentifierId, int commandElementId, Code code, String value) {
        this(commandIdentifierId);
        this.commandElementId = commandElementId;
        this.code = code;
        this.value = value;
    }

    public int getCommandIdentifierId() {
        return commandIdentifierId;
    }

    public void setCommandIdentifierId(int commandIdentiferId) {
        this.commandIdentifierId = commandIdentiferId;
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
