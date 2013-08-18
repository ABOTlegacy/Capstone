package com.aliasmodels;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class F {

    private int f1;
    private int f2;
    private int f3;

    public F() {
        this.f1 = 0;
        this.f2 = 0;
        this.f3 = 0;
    }

    public F(int f1, int f2, int f3) {
        this();
        this.f1 = f1;
        this.f2 = f2;
        this.f3 = f3;
    }

    public int getF1() {
        return f1;
    }

    public void setF1(int f1) {
        this.f1 = f1;
    }

    public int getF2() {
        return f2;
    }

    public void setF2(int f2) {
        this.f2 = f2;
    }

    public int getF3() {
        return f3;
    }

    public void setF3(int f3) {
        this.f3 = f3;
    }

    @Override
    public String toString() {
        // TODO: Implement
        return "";
    }
}