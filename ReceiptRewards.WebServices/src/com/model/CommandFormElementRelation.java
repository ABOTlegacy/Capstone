package com.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CommandFormElementRelation {

    private int commandFormElementRelationId;
    private int commandElementId;
    private int formElementId;

    public CommandFormElementRelation() {
        this.commandFormElementRelationId = 0;
        this.commandElementId = 0;
        this.formElementId = 0;
    }

    public CommandFormElementRelation(int commandFormElementRelationId, int commandElementId, int formElementId) {
        this();
        this.commandFormElementRelationId = commandFormElementRelationId;
        this.commandElementId = commandElementId;
        this.formElementId = formElementId;
    }

    public int getCommandFormElementRelationId() {
        return commandFormElementRelationId;
    }

    public void setCommandFormElementRelationId(int commandFormElementRelationId) {
        this.commandFormElementRelationId = commandFormElementRelationId;
    }

    public int getCommandElementId() {
        return commandElementId;
    }

    public void setCommandElementId(int commandElementId) {
        this.commandElementId = commandElementId;
    }

    public int getFormElementId() {
        return formElementId;
    }

    public void setFormElementId(int formElementId) {
        this.formElementId = formElementId;
    }

    @Override
    public String toString() {
        // TODO: Implement
        return "";
    }
}