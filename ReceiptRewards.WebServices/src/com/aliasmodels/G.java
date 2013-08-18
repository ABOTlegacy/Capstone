package com.aliasmodels;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class G {

    private int g1;
    private int g2;
    private C g3;
    private String g4;

    public G() {
        this.g1 = 0;
        this.g2 = 0;
        this.g3 = null;
        this.g4 = "";
    }

    public G(int g1) {
        this();
        this.g1 = g1;
    }
    
    public G(int g1, int g2, C g3, String g4) {
        this(g1);
        this.g2 = g2;
        this.g3 = g3;
        this.g4 = g4;
    }

    public int getG1() {
        return g1;
    }

    public void setG1(int g1) {
        this.g1 = g1;
    }

    public int getG2() {
        return g2;
    }

    public void setG2(int g2) {
        this.g2 = g2;
    }

    public C getG3() {
        return g3;
    }

    public void setG3(C g3) {
        this.g3 = g3;
    }

    public String getG4() {
        return g4;
    }

    public void setG4(String g4) {
        this.g4 = g4;
    }
    
    @Override
    public String toString() {
    	// TODO: Implement
        return "";
    }
}
