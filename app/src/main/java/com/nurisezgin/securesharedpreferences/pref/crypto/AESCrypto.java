package com.nurisezgin.securesharedpreferences.pref.crypto;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by nurisezgin on 08/02/2017.
 * This is sample class for how to implementation "Cryptography" for your own SharedPreferences
 * Provider.
 */

public class AESCrypto implements ICryptography {

    private final String KEY = "?!sampleKey4580dsd";

    @Override
    public String encode(String raw) {
        try {
            byte[] encoded = encrypt(raw);
            byte[] base64Encoded = Base64.encode(encoded, Base64.NO_WRAP);
            return new String(base64Encoded);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String decode(String encoded) {
        try {
            byte[] base64Decoded = Base64.decode(encoded, Base64.NO_WRAP);
            byte[] decoded = decrypt(base64Decoded);
            return new String(decoded);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private byte[] encrypt(String raw) throws Exception {
        byte[] k = KEY.getBytes();
        SecretKeySpec keySpec = new SecretKeySpec(k, 0, 16, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        return cipher.doFinal(raw.getBytes());
    }

    private byte[] decrypt(byte[] encoded) throws Exception {
        byte[] k = KEY.getBytes();
        SecretKeySpec keySpec = new SecretKeySpec(k, 0, 16, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        return cipher.doFinal(encoded);
    }

}
