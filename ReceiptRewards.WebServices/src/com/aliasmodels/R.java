package com.aliasmodels;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class R {
    
    private int r1;
    private int r2;
    private boolean r3;
    private Date r4;
    private String r5;
    private Q r6;

    public R() {
        this.r1 = 0;
        this.r2 = 0;
        this.r3 = true;
        this.r4 = new Date();
        this.r5 = "";
        this.r6 = null;
    }

    public R(int r1, int r2, boolean r3, Date r4, String r5, Q r6) {
        this();
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
        this.r4 = r4;
        this.r5 = r5;
        this.r6 = r6;
    }

    public int getR1() {
        return r1;
    }

    public void setR1(int r1) {
        this.r1 = r1;
    }

    public int getR2() {
        return r2;
    }

    public void setR2(int r2) {
        this.r2 = r2;
    }

    public boolean isR3() {
        return r3;
    }

    public void setR3(boolean r3) {
        this.r3 = r3;
    }

    public Date getR4() {
        return r4;
    }

    public void setR4(Date r4) {
        this.r4 = r4;
    }

    public String getR5() {
        return r5;
    }

    public void setR5(String r5) {
        this.r5 = r5;
    }

    public Q getR6() {
        return r6;
    }

    public void setR6(Q r6) {
        this.r6 = r6;
    }
    
    @Override
    public String toString() {
        // TODO: Implement
        return "";
    }
}