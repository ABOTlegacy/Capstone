package com.aliasmodels;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class C {

    private int c1;
    private String c2;
    private String c3;
    private String c4;
    private D c5;

    public C() {
        this.c1 = 0;
        this.c2 = "";
        this.c3 = "";
        this.c4 = "";
        this.c5 = null;
    }

    public C(int c1) {
        this();
        this.c1 = c1;
    }

    public C(int c1, String c2, String c3, String c4, D c5) {
        this(c1);
        this.c2 = c2;
        this.c3 = c3;
        this.c4 = c4;
        this.c5 = c5;
    }

    public int getC1() {
        return c1;
    }

    public void setC1(int c1) {
        this.c1 = c1;
    }

    public String getC2() {
        return c2;
    }

    public void setC2(String c2) {
        this.c2 = c2;
    }

    public String getC3() {
        return c3;
    }

    public void setC3(String c3) {
        this.c3 = c3;
    }

    public String getC4() {
        return c4;
    }

    public void setC4(String c4) {
        this.c4 = c4;
    }

    public D getC5() {
        return this.c5;
    }

    public void setC5(D c5) {
        this.c5 = c5;
    }
    
    @Override
    public String toString() {
        // TODO: Implement
        return "";
    }
}
