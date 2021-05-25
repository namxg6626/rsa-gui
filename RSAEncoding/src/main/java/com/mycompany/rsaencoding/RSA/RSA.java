/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rsaencoding.RSA;

import com.mycompany.rsaencoding.RSA.KeyPair;
import com.mycompany.rsaencoding.RSA.PrivateKey;
import com.mycompany.rsaencoding.RSA.PublicKey;
import java.util.Random;

/**
 *
 * @author namxg
 */
public class RSA {

    private KeyPair keyPair = null;

    public KeyPair getKeyPair() {
        return keyPair;
    }

    public void setKeyPair(KeyPair keyPair) {
        this.keyPair = keyPair;
    }
    
    public RSA() {
    }

    public KeyPair generateKeyPair() {
        // step 1: choose 2 prime numbers // private side
//        int q = this.getRandomPrimeNumber();
//        int p = this.getRandomPrimeNumber();
        
        int p = 7;
        int q = 2;
        
        System.out.println("q " + q);
        System.out.println("p " + p);

        // private side
        // step 2: calculate the phi 
        int phi = (p - 1) * (q - 1);
        
        // public side
        // step 3: multiple 2 prime numbers => the modulus
        int modulus = p * q; 
        
        // public side
        // step 4: find the e 
        int e = this.findE(modulus, phi);
        System.out.println("e " + e);
        if (e == 0) {
            return null;
        }
        
        PublicKey pub = new PublicKey(e, modulus);
        
        // step 3: 
        KeyPair keyPair = new KeyPair();
        return keyPair;
    }

    public String encrypt(PublicKey pubKey, String plainMsg) {
        String encryptedMsg = plainMsg;
        return encryptedMsg;
    }

    public String decrypt(PrivateKey pvtKey, String encryptedMsg) {
        String decryptedMsg = encryptedMsg;
        return decryptedMsg;
    }

    private int getRandomPrimeNumber() {
        Random ran = new Random();
        int num = 0;

        return num;
    }
    
    private int findE(int modulus, int phi) {
        for (int i = 2; i < phi; i++) {
            if (this.gcd(i, phi) == 1 && this.gcd(i, modulus) == 1) {
                return i;
            }
        }
        
        return 0;
    }

    private boolean isPrimeNumber(int num) {
        int sqrtNum = (int) Math.sqrt(num) + 1;

        for (int i = 2; i < sqrtNum; i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }
    
    private int gcd(int a, int b) {
        if (a < b) {
            a = a + b;
            b = a - b;
            a = a - b;
        }
        
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        
        return a;
    }
}
