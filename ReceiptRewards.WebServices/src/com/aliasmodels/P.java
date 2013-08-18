package com.aliasmodels;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class P {

    private int p1;
    private int p2;
    private Q p3;
    private String p4;

    public P() {
        this.p1 = 0;
        this.p2 = 0;
        this.p3 = null;
        this.p4 = "";
    }
    
    public P(int p1, int p2, Q p3, String p4) {
        this();
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
    }

    public int getP1() {
        return p1;
    }

    public void setP1(int p1) {
        this.p1 = p1;
    }

    public int getP2() {
        return p2;
    }

    public void setP2(int p2) {
        this.p2 = p2;
    }

    public Q getP3() {
        return p3;
    }

    public void setP3(Q p3) {
        this.p3 = p3;
    }

    public String getP4() {
        return p4;
    }

    public void setP4(String p4) {
        this.p4 = p4;
    }
    
    @Override
    public String toString() {
    	// TODO: Implement
        return "";
    }
}
