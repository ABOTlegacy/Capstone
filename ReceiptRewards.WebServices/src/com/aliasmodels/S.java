package com.aliasmodels;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class S {

    private int s1;
    private int s2;
    private Date s3;
    private Date s4;
    private List<T> s5;

    public S() {
        this.s1 = 0;
        this.s2 = 0;
        this.s3 = new Date();
        this.s4 = new Date();
        this.s5 = new ArrayList<T>();
    }

    public S(int s1, int s2, Date s3, Date s4, List<T> s5) {
        this();
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;
        this.s5 = s5;
    }

    public int getS1() {
        return s1;
    }

    public void setS1(int s1) {
        this.s1 = s1;
    }

    public int getS2() {
        return s2;
    }

    public void setS2(int s2) {
        this.s2 = s2;
    }

    public Date getS3() {
        return s3;
    }

    public void setS3(Date s3) {
        this.s3 = s3;
    }

    public Date getS4() {
        return s4;
    }

    public void setS4(Date s4) {
        this.s4 = s4;
    }

    public List<T> getS5() {
        return s5;
    }

    public void setS5(List<T> s5) {
        this.s5 = s5;
    }
    
    @Override
    public String toString() {
        // TODO: Implement
        return "";
    }
}