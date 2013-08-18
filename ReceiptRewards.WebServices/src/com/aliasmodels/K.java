package com.aliasmodels;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class K {

    private int k1;
    private List<L> k2;
    private List<P> k3;

    public K() {
        this.k1 = 0;
        this.k2 = new ArrayList<L>();
        this.k3 = new ArrayList<P>();
    }

    public K(int k1, List<L> k2, List<P> k3) {
        this();
        this.k1 = k1;
        this.k2 = k2;
        this.k3 = k3;
    }

    public int getK1() {
        return k1;
    }

    public void setK1(int k1) {
        this.k1 = k1;
    }
    
    public List<L> getK2() {
        return k2;
    }

    public void setK2(List<L> k2) {
        this.k2 = k2;
    }

    public List<P> getK3() {
        return k3;
    }

    public void setK3(List<P> k3) {
        this.k3 = k3;
    }
    
    @Override
    public String toString() {
        // TODO: Implement
        return "";
    }
}