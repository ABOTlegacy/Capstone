package com.aliasmodels;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class A {

    private int a1;
    private List<E> a2;
    private List<B> a3;

    public A() {
        this.a1 = 0;
        this.a2 = new ArrayList<E>();
        this.a3 = new ArrayList<B>();
    }

    public A(int a1, List<E> a2, List<B> a3) {
        this();
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
    }

    public int getA1() {
        return a1;
    }

    public void setA1(int a1) {
        this.a1 = a1;
    }
    
    public List<E> getA2() {
        return a2;
    }

    public void setA2(List<E> a2) {
        this.a2 = a2;
    }

    public List<B> getA3() {
        return a3;
    }

    public void setA3(List<B> a3) {
        this.a3 = a3;
    }
    
    @Override
    public String toString() {
        // TODO: Implement
        return "";
    }
}