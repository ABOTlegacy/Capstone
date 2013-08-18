package com.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Company {

    private int companyId;
    private String name;
    private List<Survey> surveys;

    public Company() {
        this.companyId = 0;
        this.name = "";
        this.surveys = new ArrayList<Survey>();
    }

    public Company(int companyId, String name, List<Survey> surveys) {
        this.companyId = companyId;
        this.name = name;
        this.surveys = surveys;
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

    public List<Survey> getSurveys() {
        return surveys;
    }

    public void setSurveys(List<Survey> surveys) {
        this.surveys = surveys;
    }
    
    @Override
    public String toString() {
        // TODO: Implement
        return "";
    }
}
