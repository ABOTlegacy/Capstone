package com.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SubmissionReward {
    
    private int submissionRewardId;
    private Reward reward;
    private String value;
    private boolean redeemed;

    public SubmissionReward() {
        this.submissionRewardId = 0;
        this.reward = null;
        this.value = "";
        this.redeemed = false;
    }

    public SubmissionReward(int submissionRewardId, Reward reward, String value, boolean redeemed) {
        this();
        this.submissionRewardId = submissionRewardId;
        this.reward = reward;
        this.value = value;
        this.redeemed = redeemed;
    }

    public int getSubmissionRewardId() {
        return submissionRewardId;
    }

    public void setSubmissionRewardId(int submissionRewardId) {
        this.submissionRewardId = submissionRewardId;
    }

    public Reward getReward() {
        return reward;
    }

    public void setReward(Reward reward) {
        this.reward = reward;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isRedeemed() {
        return redeemed;
    }

    public void setRedeemed(boolean redeemed) {
        this.redeemed = redeemed;
    }
    
    @Override
    public String toString() {
        // TODO: Implement
        return "";
    }
}