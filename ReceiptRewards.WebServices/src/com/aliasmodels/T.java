package com.aliasmodels;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class T {

    private int t1;
    private int t2;
    private int t3;
    private int t4;
    private String t5;

    public T() {
        this.t1 = 0;
        this.t2 = 0;
        this.t3 = 0;
        this.t4 = 0;
        this.t5 = "";
    }

    public T(int t1, int t2, int t3, int t4, String t5) {
        this();
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.t4 = t4;
        this.t5 = t5;
    }

    public int getT1() {
        return t1;
    }

    public void setT1(int t1) {
        this.t1 = t1;
    }

    public int getT2() {
        return t2;
    }

    public void setT2(int t2) {
        this.t2 = t2;
    }

    public int getT3() {
        return t3;
    }

    public void setT3(int t3) {
        this.t3 = t3;
    }

    public int getT4() {
        return t4;
    }

    public void setT4(int t4) {
        this.t4 = t4;
    }

    public String getT5() {
        return t5;
    }

    public void setT5(String t5) {
        this.t5 = t5;
    }
    
    @Override
    public String toString() {
        // TODO: Implement
        return "";
    }
}