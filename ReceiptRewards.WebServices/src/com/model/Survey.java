package com.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Survey {

    private int surveyId;
    private int companyId;
    private String name;
    private List<Revision> revisions;

    public Survey() {
        this.surveyId = 0;
        this.companyId = 0;
        this.name = "";
        this.revisions = new ArrayList<Revision>();
    }

    public Survey(int surveyId, int companyId, String name, List<Revision> revisions) {
        this();
        this.surveyId = surveyId;
        this.companyId = companyId;
        this.name = name;
        this.revisions = revisions;
    }

    public int getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(int surveyId) {
        this.surveyId = surveyId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Revision> getRevisions() {
        return revisions;
    }
    
    public void setRevisions(List<Revision> revisions) {
        this.revisions = revisions;
    }
    
    @Override
    public String toString() {
        // TODO: Implement
        return "";
    }
}
