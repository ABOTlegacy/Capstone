package com.aliasmodels;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class N {

    private int n1;
    private int n2;
    private I n3;
    private String n4;

    public N() {
        this.n1 = 0;
        this.n2 = 0;
        this.n3 = new I();
        this.n4 = "";
    }

    public N(int n1, int n2, I n3, String n4) {
        this();
        this.n1 = n1;
        this.n2 = n2;
        this.n3 = n3;
        this.n4 = n4;
    }

    public int getN1() {
        return n1;
    }

    public void setN1(int n1) {
        this.n1 = n1;
    }

    public int getN2() {
        return n2;
    }

    public void setN2(int n2) {
        this.n2 = n2;
    }

    public I getN3() {
        return n3;
    }

    public void setN3(I n3) {
        this.n3 = n3;
    }

    public String getN4() {
        return n4;
    }

    public void setN4(String n4) {
        this.n4 = n4;
    }
    
    @Override
    public String toString() {
        // TODO: Implement
        return "";
    }
}