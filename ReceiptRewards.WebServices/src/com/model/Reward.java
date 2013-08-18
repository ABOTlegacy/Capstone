package com.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Reward {
    
    private int rewardId;
    private Code code;
    private Form form;

    public Reward() {
        this.rewardId = 0;
        this.code = null;
        this.form = new Form();
    }

    public Reward(int rewardId, Code code, Form form) {
        this();
        this.rewardId = rewardId;
        this.code = code;
        this.form = form;
    }

    public int getRewardId() {
        return rewardId;
    }

    public void setRewardId(int rewardId) {
        this.rewardId = rewardId;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }
    
    @Override
    public String toString() {
        // TODO: Implement
        return "";
    }
}