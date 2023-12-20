package com.example.smarthome.aesutils;

import com.tozny.crypto.android.AesCbcWithIntegrity;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

//AES加密算法工具类
//AES128,CBC,PKCS5填充
public class aesUtils {
    //一次性六位临时密码生成器
    public static String oncePassword(){
        Set<Integer> set = new HashSet<>();
        while (set.size() < 6) {
            set.add((int) (Math.random() * 10));
        }
        return set.stream().map(String::valueOf).collect(Collectors.joining());
    }

    //随机密钥生成器
    public static AesCbcWithIntegrity.SecretKeys generateKeys(){
        AesCbcWithIntegrity.SecretKeys keys= null;
        try {
            keys = AesCbcWithIntegrity.generateKey();
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
        return  keys;
    }

    //利用一次性密码生成器和随机密钥生成器，生成加密后的密文
    public static String desc(){
        AesCbcWithIntegrity.CipherTextIvMac cipherTextIvMac= null;
        try {
            cipherTextIvMac = AesCbcWithIntegrity.encrypt(oncePassword(),generateKeys());
        } catch (UnsupportedEncodingException | GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
        return cipherTextIvMac.toString();
    }

    //利用密钥解密
    public static String decode(String ciphertextString,AesCbcWithIntegrity.SecretKeys keys){
        AesCbcWithIntegrity.CipherTextIvMac cipherTextIvMac=new AesCbcWithIntegrity.CipherTextIvMac(ciphertextString);
        String plainText= null;
        try {
            plainText = AesCbcWithIntegrity.decryptString(cipherTextIvMac,keys);
        } catch (UnsupportedEncodingException | GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
        return plainText;
    }
}
