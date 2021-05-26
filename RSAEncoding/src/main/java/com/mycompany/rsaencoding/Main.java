/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rsaencoding;
import com.mycompany.rsaencoding.RSAGUI;
import com.mycompany.rsaencoding.RSA.RSA;
import com.mycompany.rsaencoding.RSA.KeyPair;

/**
 *
 * @author namxg
 */
public class Main {
    public static void main(String[] args) {
        RSA rsa = new RSA();
        KeyPair keyPair = rsa.generateRandomKeyPair();
        rsa.setKeyPair(keyPair);
        System.out.println("keyPair ", rsa.getKeyPair());
    }
}
