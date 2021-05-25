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
public class PublicKey {

    private int e;
    private int n;

    public int getE() {
        return e;
    }

    public void setE(int e) {
        this.e = e;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public PublicKey(int e, int n) {
        this.e = e;
        this.n = n;
    }
}
