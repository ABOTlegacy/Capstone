package com.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Code {

    private int codeId;
    private String code;
    private String name;
    private String description;
    private CodeType codeType;

    public Code() {
        this.codeId = 0;
        this.code = "";
        this.name = "";
        this.description = "";
        this.codeType = null;
    }

    public Code(int codeId) {
        this();
        this.codeId = codeId;
    }

    public Code(int codeId, String code, String name, String description, CodeType codeType) {
        this(codeId);
        this.code = code;
        this.name = name;
        this.description = description;
        this.codeType = codeType;
    }

    public int getCodeId() {
        return codeId;
    }

    public void setCodeId(int codeId) {
        this.codeId = codeId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CodeType getCodeType() {
        return this.codeType;
    }

    public void setCodeType(CodeType codeType) {
        this.codeType = codeType;
    }
    
    @Override
    public String toString() {
        // TODO: Implement
        return "";
    }
}
