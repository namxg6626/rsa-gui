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

    public KeyPair generateRandomKeyPair() {
        // step 1: choose 2 prime numbers // private side
        int q = this.getRandomPrimeNumber();
        int p = this.getRandomPrimeNumber();

        // private side
        // step 2: calculate the phi 
        int phi = (p - 1) * (q - 1);

        // public side
        // step 3: multiple 2 prime numbers => the modulus
        int N = p * q;

        // public side
        // step 4: find the e 
        int e = this.findE(N, phi);
        if (e == -1) {
            return this.generateRandomKeyPair();
        }

        PublicKey pub = new PublicKey(e, N);
        
        int d = this.findModuloInverse(e, phi);
        PrivateKey pvt = new PrivateKey(p, q, d);
        
        // step 3: 
        KeyPair keyPair = new KeyPair(pub, pvt);
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
        int num = 0;
        Random rand = new Random(); // generate a random number
        num = rand.nextInt(1000) + 1;

        while (!isPrimeNumber(num)) {
            num = rand.nextInt(1000) + 1;
        }

        return num;
    }

    // result = a^-1 mod b
    private int findModuloInverse(int a, int b) {
        for (int i = 1; i < b; i++) {
            if (((long) a * i) % b == 1) {
                return i;
            }
        }
        
        return -1;
    }

    private int findE(int modulus, int phi) {
        for (int i = 2; i < phi; i++) {
            if (this.gcd(i, phi) == 1 && this.gcd(i, modulus) == 1) {
                return i;
            }
        }

        return -1;
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
