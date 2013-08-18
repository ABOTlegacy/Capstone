package com.aliasmodels;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class L {

    private int l1;
    private C l2;
    private List<N> l3;
    private List<M> l4;

    public L() {
        this.l1 = 0;
        this.l2 = null;
        this.l3 = new ArrayList<N>();
        this.l4 = new ArrayList<M>();
    }
    
    public L(int l1, C l2, List<N> l3, List<M> l4) {
        this();
        this.l1 = l1;
        this.l2 = l2;
        this.l3 = l3;
        this.l4 = l4;
    }
    
    public int getL1() {
        return this.l1;
    }
    
    public void setL1(int l1) {
        this.l1 = l1;
    }
    
    public C getL2() {
        return this.l2;
    }
    
    public void setL2(C l2) {
        this.l2 = l2;
    }
    
    public List<N> getL3() {
        return this.l3;
    }
    
    public void setL3(List<N> l3) {
        this.l3 = l3;
    }
    
    public List<M> getL4() {
        return this.l4;
    }
    
    public void setL4(List<M> l4) {
        this.l4 = l4;
    }
    
    @Override
    public String toString() {
        // TODO: Implement
        return "";
    }
}
