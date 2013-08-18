package com.automation;

import java.util.ArrayList;
import java.util.List;

public class Element {
    
    private int elementId;
    private String type;
    private String value;
    private List<Identifier> identifiers;
    

    public Element() {
        this.elementId = 0;
        this.type = "";
        this.value = "";
        this.identifiers = new ArrayList<Identifier>();
    }
    
    public Element(int elementId, String type, String value, List<Identifier> identifiers) {
        this.elementId = elementId;
        this.type = type;
        this.value = value;
        this.identifiers = identifiers;
    }

    public int getElementId() {
        return elementId;
    }

    public void setElementId(int elementId) {
        this.elementId = elementId;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Identifier> getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(List<Identifier> identifiers) {
        this.identifiers = identifiers;
    }
}