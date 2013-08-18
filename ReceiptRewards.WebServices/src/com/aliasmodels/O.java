package com.aliasmodels;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class O {

    private int o1;
    private int o2;
    private int o3;

    public O() {
        this.o1 = 0;
        this.o2 = 0;
        this.o3 = 0;
    }

    public O(int o1, int o2, int o3) {
        this();
        this.o1 = o1;
        this.o2 = o2;
        this.o3 = o3;
    }

    public int getO1() {
        return o1;
    }

    public void setO1(int o1) {
        this.o1 = o1;
    }

    public int getO2() {
        return o2;
    }

    public void setO2(int o2) {
        this.o2 = o2;
    }

    public int getO3() {
        return o3;
    }

    public void setO3(int o3) {
        this.o3 = o3;
    }
    
    @Override
    public String toString() {
        // TODO: Implement
        return "";
    }
}