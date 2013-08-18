package com.aliasmodels;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class J {
    
    private int j1;
    private int j2;
    private C j3;
    private String j4;
    
    public J() {
        this.j1 = 0;
        this.j2 = 0;
        this.j3 = null;
        this.j4 = "";
    }
    
    public J(int j1, int j2, C j3, String j4) {
        this.j1 = j1;
        this.j2 = j2;
        this.j3 = j3;
        this.j4 = j4;
    }
    
    public int getJ1() {
        return j1;
    }
    
    public void setJ1(int j1) {
        this.j1 = j1;
    }
    
    public int getJ2() {
        return j2;
    }
    
    public void setJ2(int j2) {
        this.j2 = j2;
    }
    
    public C getJ3() {
        return j3;
    }
    
    public void setJ3(C j3) {
        this.j3 = j3;
    }
    
    public String getJ4() {
        return j4;
    }
    
    public void setJ4(String j4) {
        this.j4 = j4;
    }
    
    @Override
    public String toString() {
        // TODO: Implement
        return "";
    }
}