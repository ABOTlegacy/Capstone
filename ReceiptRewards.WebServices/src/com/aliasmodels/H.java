package com.aliasmodels;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class H {

    private int h1;
    private String h2;
    private List<U> h3;

    public H() {
        this.h1 = 0;
        this.h2 = "";
        this.h3 = new ArrayList<U>();
    }

    public H(int h1, String h2, List<U> h3) {
        this.h1 = h1;
        this.h2 = h2;
        this.h3 = h3;
    }

    public int getH1() {
        return h1;
    }

    public void setH1(int h1) {
        this.h1 = h1;
    }

    public String getH2() {
        return h2;
    }

    public void setH2(String h2) {
        this.h2 = h2;
    }

    public List<U> getH3() {
        return h3;
    }

    public void setH3(List<U> h3) {
        this.h3 = h3;
    }
    
    @Override
    public String toString() {
        // TODO: Implement
        return "";
    }
}
