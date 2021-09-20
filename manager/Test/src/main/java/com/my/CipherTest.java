package com.my;

import org.junit.Test;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @program:
 * @description:
 * @author: liang.liu
 * @create: 2021-09-11 10:21
 */
public class CipherTest {
    private static final int LOOP = 50000;

    public static String ALGORITHM_DES = "DES";
    public static String ALGORITHM_3DES = "DESede"; // 3DES
    public static String ALGORITHM_BLOWFISH = "Blowfish";
    public static String ALGORITHM_AES = "AES";

    public static Key keyGenerator(String algorithm, int n) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        keyGenerator.init(n);
        return keyGenerator.generateKey();
    }

    // 加密方法
    public static byte[] encrypt(Key key, String text, String algorithm) throws Exception {
        // ECB是分组模式，PKCS5Padding 是补全策略
        Cipher cipher = Cipher.getInstance(algorithm + "/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(text.getBytes());
    }

    // 解密方法
    public static byte[] decrypt(Key key, byte[] data, String algorithm) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm + "/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    // 测试加密速度
    public static void testEncrypt(String text, String algorithm, int bit) throws Exception {
        Key key = keyGenerator(algorithm, bit);
        long startTs = System.currentTimeMillis();

        for (int i = 0; i < LOOP; i++) {
            encrypt(key, text, algorithm);
        }
        long endTs = System.currentTimeMillis();
        System.out.println(algorithm + " (" + bit + "位秘钥) 加密平均耗时 " + ((endTs - startTs) / (double) LOOP) + " ms");
    }

    public static void testDecrypt(String text, String algorithm, int bit) throws Exception {
        Key key = keyGenerator(algorithm, bit);
        long startTs = System.currentTimeMillis();

        byte[] encrypted = encrypt(key, text, algorithm);

        for (int i = 0; i < LOOP; i++) {
            decrypt(key, encrypted, algorithm);
        }
        long endTs = System.currentTimeMillis();
        System.out.println(algorithm + " (" + bit + "位秘钥) 解密平均耗时 " + ((endTs - startTs) / (double) LOOP) + " ms");
    }

    public static void main(String[] args) throws Exception {
        String passwordText = "这是我的信用卡账号 5555 5555 5555 5555";

        // DES 算法仅支持56位定长秘钥
        testEncrypt(passwordText, ALGORITHM_DES, 56);
        testDecrypt(passwordText, ALGORITHM_DES, 56);

        // 3DES 算法仅支持112位和168位秘钥
        testEncrypt(passwordText, ALGORITHM_3DES, 112);
        testDecrypt(passwordText, ALGORITHM_3DES, 112);

        testEncrypt(passwordText, ALGORITHM_3DES, 168);
        testDecrypt(passwordText, ALGORITHM_3DES, 168);

        // Blowfish 算法支持32 到 448位的变长秘钥
        testEncrypt(passwordText, ALGORITHM_BLOWFISH, 32);
        testDecrypt(passwordText, ALGORITHM_BLOWFISH, 32);

        testEncrypt(passwordText, ALGORITHM_BLOWFISH, 256);
        testDecrypt(passwordText, ALGORITHM_BLOWFISH, 256);

        testEncrypt(passwordText, ALGORITHM_BLOWFISH, 448);
        testDecrypt(passwordText, ALGORITHM_BLOWFISH, 448);

        // AES 算法支持 128，192，256 三种定长秘钥
        testEncrypt(passwordText, ALGORITHM_AES, 128);
        testDecrypt(passwordText, ALGORITHM_AES, 128);

        testEncrypt(passwordText, ALGORITHM_AES, 192);
        testDecrypt(passwordText, ALGORITHM_AES, 192);

        testEncrypt(passwordText, ALGORITHM_AES, 256);
        testDecrypt(passwordText, ALGORITHM_AES, 256);
    }

    @Test
    public void aesEncrypt() {

        try {
            String password = "1111111111111111";

            String content = "dsds";
            KeyGenerator kgen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者

            kgen.init(128, new SecureRandom(password.getBytes()));// 利用用户密码作为随机数初始化出
            // 128位的key生产者
            //加密没关系，SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以解密只要有password就行

            SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥

            byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥，如果此密钥不支持编码，则返回
            // null。

            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥

            Cipher cipher = Cipher.getInstance("AES");// 创建密码器

            byte[] byteContent = content.getBytes("utf-8");

            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化为加密模式的密码器

            byte[] result = cipher.doFinal(byteContent);// 加密

            byte[] encodeBytes = Base64.getEncoder().encode(result);
            System.out.println(new String(encodeBytes));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void aesDecrypt() {
        try {
            String password="11";
            byte[] content=Base64.getDecoder().decode("w1jDY+b8m8jdAfqm1rb6pVZNGMy/8XDQsUrwBlwLMhM=");
            KeyGenerator kgen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥
            byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化为解密模式的密码器
            byte[] result = cipher.doFinal(content);
            System.out.println(new String(result));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // 加密
    @Test
    public void Encrypt() throws Exception {

        String content="dsds";
        String key="1111111111111111";
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("utf-8"), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/pkcs5padding");//"算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(content.getBytes("utf-8"));
        String result= new BASE64Encoder().encode(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
        System.out.println(new String(result));
    }


    /**
     * AES 加密操作

     * @return 返回Base64转码后的加密数据
     */
    @Test
    public  void encrypt() {

        String content="dsds";
        String sKey="1111111111111111";

        try {
            /*
             * 新建一个密码编译器的实例，由三部分构成，用"/"分隔，分别代表如下
             * 1. 加密的类型(如AES，DES，RC2等)
             * 2. 模式(AES中包含ECB，CBC，CFB，CTR，CTS等)
             * 3. 补码方式(包含nopadding/PKCS5Padding等等)
             * 依据这三个参数可以创建很多种加密方式
             */
            Cipher cipher = Cipher.getInstance("AES/CBC/pkcs5padding");

            //偏移量
            IvParameterSpec zeroIv = new IvParameterSpec("1234567890abcdef".getBytes("utf-8"));

            byte[] byteContent = content.getBytes("utf-8");

            //使用加密秘钥
//            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("utf-8"), AES);
            SecretKeySpec skeySpec = new SecretKeySpec(sKey.getBytes(), "AES");
//            SecretKeySpec skeySpec = getSecretKey(key);

            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, zeroIv);// 初始化为加密模式的密码器

            byte[] result = cipher.doFinal(byteContent);// 加密

            String resultstr= new BASE64Encoder().encode(result);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
            System.out.println(new String(resultstr));

        } catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }


}
