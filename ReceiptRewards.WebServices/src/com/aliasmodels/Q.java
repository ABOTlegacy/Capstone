package com.aliasmodels;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Q {

    private int q1;
    private int q2;
    private String q3;
    private K q4;
    private A q5;

    public Q() {
        this.q1 = 0;
        this.q2 = 0;
        this.q3 = "";
        this.q4 = null;
        this.q5 = null;
    }

    public Q(int q1) {
        this();
        this.q1 = q1;
        this.q2 = 0;
        this.q3 = "";
        this.q4 = null;
        this.q5 = null;
    }

    public Q(int q1, int q2) {
        this(q1);
        this.q2 = q2;
        this.q3 = "";
        this.q4 = null;
        this.q5 = null;
    }

    public Q(int q1, int q2, String q3, K q4, A q5) {
        this(q1, q2);
        this.q3 = q3;
        this.q4 = q4;
        this.q5 = q5;
    }

    public int getQ1() {
        return q1;
    }

    public void setQ1(int q1) {
        this.q1 = q1;
    }

    public int getQ2() {
        return q2;
    }

    public void setQ2(int q2) {
        this.q2 = q2;
    }
    
    public String getQ3() {
        return q3;
    }

    public void setQ3(String q3) {
        this.q3 = q3;
    }

    public K getQ4() {
        return q4;
    }

    public void setQ4(K q4) {
        this.q4 = q4;
    }

    public A getQ5() {
        return q5;
    }

    public void setQ5(A q5) {
        this.q5 = q5;
    }
    
    @Override
    public String toString() {
        // TODO: Implement
        return "";
    }
}