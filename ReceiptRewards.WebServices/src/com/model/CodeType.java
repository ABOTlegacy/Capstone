package com.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CodeType {

    private int codeTypeId;
    private String type;
    private String description;

    public CodeType() {
        this.codeTypeId = 0;
        this.type = "";
        this.description = "";
    }

    public CodeType(int codeTypeId) {
        this();
        this.codeTypeId = codeTypeId;
    }

    public CodeType(int codeTypeId, String type) {
        this(codeTypeId);
        this.type = type;
    }
    
    public CodeType(int codeTypeId, String type, String description) {
        this(codeTypeId, type);
        this.description = description;
    }

    public int getCodeTypeId() {
        return codeTypeId;
    }

    public void setCodeTypeId(int codeTypeId) {
        this.codeTypeId = codeTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
	public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("CodeTypeId: ");
    	sb.append(this.codeTypeId);
    	sb.append(" Type: ");
    	sb.append(this.type);
    	sb.append(" Description: ");
    	sb.append(this.description);
    	return sb.toString();
    }
}
