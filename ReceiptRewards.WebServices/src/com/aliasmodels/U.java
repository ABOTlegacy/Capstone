package com.aliasmodels;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class U {

    private int u1;
    private int u2;
    private String u3;
    private List<R> u4;

    public U() {
        this.u1 = 0;
        this.u2 = 0;
        this.u3 = "";
        this.u4 = new ArrayList<R>();
    }

    public U(int u1, int u2, String u3, List<R> u4) {
        this();
        this.u1 = u1;
        this.u2 = u2;
        this.u3 = u3;
        this.u4 = u4;
    }

    public int getU1() {
        return u1;
    }

    public void setU1(int u1) {
        this.u1 = u1;
    }

    public int getU2() {
        return u2;
    }

    public void setU2(int u2) {
        this.u2 = u2;
    }

    public String getU3() {
        return u3;
    }

    public void setU3(String u3) {
        this.u3 = u3;
    }

    public List<R> getU4() {
        return u4;
    }
    
    public void setU4(List<R> u4) {
        this.u4 = u4;
    }
    
    @Override
    public String toString() {
        // TODO: Implement
        return "";
    }
}
