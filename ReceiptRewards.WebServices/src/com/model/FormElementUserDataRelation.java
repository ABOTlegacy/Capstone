package com.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FormElementUserDataRelation {

    private int formElementUserDataRelationId;
    private int formElementId;
    private int userDataId;

    public FormElementUserDataRelation() {
        this.formElementUserDataRelationId = 0;
        this.formElementId = 0;
        this.userDataId = 0;
    }

    public FormElementUserDataRelation(int formElementUserDataRelationId, int formElementId, int userDataId) {
        this();
        this.formElementUserDataRelationId = formElementUserDataRelationId;
        this.formElementId = formElementId;
        this.userDataId = userDataId;
    }

    public int getFormElementUserDataRelationId() {
        return formElementUserDataRelationId;
    }

    public void setFormElementUserDataRelationId(int formElementUserDataRelationId) {
        this.formElementUserDataRelationId = formElementUserDataRelationId;
    }

    public int getFormElementId() {
        return formElementId;
    }

    public void setFormElementId(int formElementId) {
        this.formElementId = formElementId;
    }

    public int getUserDataId() {
        return userDataId;
    }

    public void setUserDataId(int userDataId) {
        this.userDataId = userDataId;
    }
    
    @Override
    public String toString() {
        // TODO: Implement
        return "";
    }
}