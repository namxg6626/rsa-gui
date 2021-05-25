/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rsaencoding.RSA;

/**
 *
 * @author namxg
 */
public class PrivateKey {

    private int p;
    private int q;
    private int d;

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public int getQ() {
        return q;
    }

    public void setQ(int q) {
        this.q = q;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public PrivateKey(int p, int q, int d) {
        this.p = p;
        this.q = q;
        this.d = d;
    }
}
