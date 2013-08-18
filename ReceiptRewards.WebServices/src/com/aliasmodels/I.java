package com.aliasmodels;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class I {

    private int i1;
    private String i2;
    private List<J> i3;

    public I() {
        this.i1 = 0;
        this.i2 = "";
        this.i3 = new ArrayList<J>();
    }

    public I(int i1, List<J> i3) {
        this.i1 = i1;
        this.i2 = i3.get(0).getJ4();
        this.i3 = i3;
    }
    
    public I(int i1, String i2, List<J> i3) {
        this.i1 = i1;
        this.i2 = i2;
        this.i3 = i3;
    }

    public int getI1() {
        return this.i1;
    }

    public void setI1(int i1) {
        this.i1 = i1;
    }

    public String getI2() {
        return this.i2;
    }

    public void setI2(String i2) {
        this.i2 = i2;
    }
    
    public List<J> getI3() {
        return this.i3;
    }

    public void setI3(List<J> i3) {
        this.i3 = i3;
    }
    
    @Override
    public String toString() {
        // TODO: Implement
        return "";
    }
}
