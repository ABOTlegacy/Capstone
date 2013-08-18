package com.aliasmodels;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class M {

    private int m1;
    private int m2;
    private C m3;
    private String m4;

    public M() {
        this.m1 = 0;
        this.m2 = 0;
        this.m3 = null;
        this.m4 = "";
    }


    public M(int m1, int m2, C m3, String m4) {
        this();
        this.m1 = m1;
        this.m2 = m2;
        this.m3 = m3;
        this.m4 = m4;
    }

    public int getM1() {
        return m1;
    }

    public void setM1(int m1) {
        this.m1 = m1;
    }

    public int getM2() {
        return m2;
    }

    public void setM2(int m2) {
        this.m2 = m2;
    }

    public C getM3() {
        return m3;
    }

    public void setM3(C m3) {
        this.m3 = m3;
    }

    public String getM4() {
        return m4;
    }

    public void setM4(String m4) {
        this.m4 = m4;
    }
    
    @Override
    public String toString() {
        // TODO: Implement
        return "";
    }
}