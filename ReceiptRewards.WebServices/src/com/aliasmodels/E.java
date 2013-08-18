package com.aliasmodels;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class E {

    private int e1;
    private C e2;
    private List<G> e3;
    private List<F> e4;
    private String e5;

    public E() {
        this.e1 = 0;
        this.e2 = null;
        this.e3 = new ArrayList<G>();
        this.e4 = new ArrayList<F>();
        this.e5 = "";
    }

    public E(int e1) {
        this();
        this.e1 = e1;
    }

    public E(int e1, C e2, List<G> e3, List<F> e4, String e5) {
        this(e1);
        this.e2 = e2;
        this.e3 = e3;
        this.e4 = e4;
        this.e5 = e5;
    }

    public int getE1() {
        return e1;
    }

    public void setE1(int e1) {
        this.e1 = e1;
    }

    public C getE2() {
        return e2;
    }

    public void setE2(C e2) {
        this.e2 = e2;
    }

    public List<G> getE3() {
        return e3;
    }

    public void setE3(List<G> e3) {
        this.e3 = e3;
    }

    public List<F> getE4() {
        return e4;
    }

    public void setE4(List<F> e4) {
        this.e4 = e4;
    }

    public String getE5() {
        return e5;
    }

    public void setE5(String e5) {
        this.e5 = e5;
    }
    
    @Override
    public String toString() {
        // TODO: Implement
        return "";
    }
}