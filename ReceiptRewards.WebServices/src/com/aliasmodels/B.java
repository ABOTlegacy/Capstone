package com.aliasmodels;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class B {

    private int b1;
    private int b2;
    private Q b3;
    private int b4;

    public B() {
        this.b1 = 0;
        this.b2 = 0;
        this.b3 = null;
        this.b4 = 0;
    }
    
    public B(int b1, int b2, Q b3, int b4) {
        this();
        this.b1 = b1;
        this.b2 = b2;
        this.b3 = b3;
        this.b4 = b4;
    }

    public int getB1() {
        return b1;
    }

    public void setB1(int b1) {
        this.b1 = b1;
    }

    public int getB2() {
        return b2;
    }

    public void setB2(int b2) {
        this.b2 = b2;
    }

    public Q getB3() {
        return b3;
    }

    public void setB3(Q b3) {
        this.b3 = b3;
    }

    public int getB4() {
        return b4;
    }

    public void setB4(int b4) {
        this.b4 = b4;
    }
    
    @Override
    public String toString() {
    	// TODO: Implement
        return "";
    }
}
