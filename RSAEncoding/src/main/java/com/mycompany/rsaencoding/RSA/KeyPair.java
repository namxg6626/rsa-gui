/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rsaencoding.RSA;

import com.mycompany.rsaencoding.RSA.PrivateKey;
import com.mycompany.rsaencoding.RSA.PublicKey;

/**
 *
 * @author namxg
 */
public class KeyPair {

    private PublicKey publicKey;
    private PrivateKey privateKey;
    
    public PublicKey getPublicKey() {
        return publicKey;
    }
    
    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }
    
    public PrivateKey getPrivateKey() {
        return privateKey;
    }
    
    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }
    
    public KeyPair() {
    }

    public KeyPair(PublicKey pubKey, PrivateKey pvtKey) {
        this.setPrivateKey(pvtKey);
        this.setPublicKey(pubKey);
    }
}
