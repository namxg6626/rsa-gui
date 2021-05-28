/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rsaencoding.RSA;

import java.math.BigInteger;
import java.util.ArrayList;
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
//        int q = 13;
//        int p = 17;

        // private side
        // step 2: calculate the phi 
        int phi = (p - 1) * (q - 1);
        System.out.println("phi " + phi);

        // public side
        // step 3: multiple 2 prime numbers => the modulus
        int N = p * q;
        System.out.println("N " + N);

        // public side
        // step 4: find the e 
        int e = this.findE(N, phi);
        System.out.println("e " + e);
        if (e == -1) {
            return this.generateRandomKeyPair();
        }

        PublicKey pub = new PublicKey(e, N);

        int d = this.findModuloInverse(e, phi);
        System.out.println("d " + d);
        PrivateKey pvt = new PrivateKey(p, q, d);

        KeyPair keyPair = new KeyPair(pub, pvt);
        return keyPair;
    }
    
    public String encrypt(PublicKey pubKey, String plainMsg) {
        String lwPlainMsg = plainMsg.toUpperCase();
        ArrayList<Integer> lwPlainMsgInts = this.convertStringToInts(lwPlainMsg);
        ArrayList<Integer> lwEncryptedMsgInts = new ArrayList<>();

        System.out.println("lwPlainMsgInts " + lwPlainMsgInts);

        for (Integer plainInt : lwPlainMsgInts) {
            Integer encrypted = ((int) Math.pow(plainInt, pubKey.getE())) % pubKey.getN();
            if (encrypted >= 26) {
                encrypted = encrypted % 26;
            }
            lwEncryptedMsgInts.add(encrypted);
        }

        System.out.println("lwEncryptedMsgInts " + lwEncryptedMsgInts);
        String encryptedMsg = this.convertIntsToString(lwEncryptedMsgInts);
        return encryptedMsg;
    }

    public String decrypt(PrivateKey pvtKey, String encryptedMsg) {
        String lwEncryptedMsg = encryptedMsg.toUpperCase();
        ArrayList<Integer> lwEncryptedInts = this.convertStringToInts(encryptedMsg);
        ArrayList<Integer> lwDecryptedInts = new ArrayList<>();
        Integer N = pvtKey.getP() * pvtKey.getQ();
        Integer d = pvtKey.getD();

        for (Integer encryptedInt : lwEncryptedInts) {
            Integer decrypted = this.fastModPower(encryptedInt, d, N);
            System.out.println("decrypted " + decrypted);
            if (decrypted >= 26) {
                decrypted = (decrypted) % 26;
            }
            lwDecryptedInts.add(decrypted);
        }

        System.out.println("lwDecryptedInts " + lwDecryptedInts);
        String decryptedMsg = this.convertIntsToString(lwDecryptedInts);
        return decryptedMsg;
    }

    public String encrypt(String plainMsg) {
        return this.encrypt(this.getKeyPair().getPublicKey(), plainMsg);
    }

    public String decrypt(String encryptedMsg) {
        return this.decrypt(this.getKeyPair().getPrivateKey(), encryptedMsg);
    }

    public ArrayList<Integer> convertStringToInts(String str) {
        ArrayList<Integer> ints = new ArrayList<>();

        for (int i = 0; i < str.length(); i++) {
            int ascii = (int) str.charAt(i);
            ints.add(ascii - 65);
        }

        return ints;
    }

    public String convertIntsToString(ArrayList<Integer> ints) {
        String str = "";
        for (int i = 0; i < ints.size(); i++) {
            str += Character.toString(ints.get(i) + 65);
        }

        return str;
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
            if (this.gcd(i, phi) == 1) {
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

    private int fastModPower(Integer base, Integer exponent, Integer modulus) {
        String bitsOfExponentStr = Integer.toBinaryString(exponent);
        System.out.println("bitsOfExponentStr " + bitsOfExponentStr);
        Integer r = base;

        for (int i = 1; i < bitsOfExponentStr.length(); i++) {
            Integer bit = Integer.parseInt(String.valueOf(bitsOfExponentStr.charAt(i)));
            r = (r * r) % modulus;
            if (bit == 1) {
                r = (r * base) % modulus;
            }
        }

        return exponent == 0 ? 1 : r;
    }
}
