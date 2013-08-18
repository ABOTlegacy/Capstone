package com.aliasmodels;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class D {

    private int d1;
    private String d2;
    private String d3;

    public D() {
        this.d1 = 0;
        this.d2 = "";
        this.d3 = "";
    }

    public D(int d1) {
        this();
        this.d1 = d1;
    }

    public D(int d1, String d2) {
        this(d1);
        this.d2 = d2;
    }
    
    public D(int d1, String d2, String d3) {
        this(d1, d2);
        this.d3 = d3;
    }

    public int getD1() {
        return d1;
    }

    public void setD1(int d1) {
        this.d1 = d1;
    }

    public String getD2() {
        return d2;
    }

    public void setD2(String d2) {
        this.d2 = d2;
    }

    public String getD3() {
        return d3;
    }

    public void setD3(String d3) {
        this.d3 = d3;
    }
    
    @Override
	public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("CodeTypeId: ");
    	sb.append(this.d1);
    	sb.append(" Type: ");
    	sb.append(this.d2);
    	sb.append(" Description: ");
    	sb.append(this.d3);
    	return sb.toString();
    }
}
