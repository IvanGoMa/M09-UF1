package iticbcn.xifratge;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.security.MessageDigest;


public class XifradorAES implements Xifrador{

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    private static final int MIDA_IV = 16;
    private final static String CLAU ="1234";

    private byte[] iv = new byte[MIDA_IV];

    public TextXifrat xifra(String msg, String password) throws ClauNoSuportada, Exception{
        // Obtenir els bytes de l'String
        byte[] bMsg = msg.getBytes();
        // Genera IvParameterSpec
        obteIv();
        IvParameterSpec ivps = new IvParameterSpec(iv);
        // Genera hash
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hash = digest.digest(password.getBytes("UTF-8"));
        SecretKeySpec key = new SecretKeySpec(hash, ALGORISME_XIFRAT);
        // Encrypt.
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE,key,ivps);
        byte[] encrypted = cipher.doFinal(bMsg);
        // Combinar IV i part xifrada
        //byte[] bIv = ivps.getIV();
        byte[] bIv = iv;
        byte[] tot = new byte[bIv.length + encrypted.length];
        for (int i = 0; i < bIv.length; i++) {
            tot[i] = bIv[i];
        }
        for (int i = 0; i < encrypted.length; i++) {
            tot[bIv.length+i] = encrypted[i];
        }
        // return iv+msgxifrat
        return new TextXifrat(tot);
    }

    public String desxifra(TextXifrat bMsgXifrat, String password) throws Exception{
        // Extreure l'IV
        byte[] bIv = new byte[MIDA_IV];
        for (int i = 0; i < bIv.length; i++) {
            bIv[i] = bMsgXifrat.getBytes()[i];
        }
        // Extreure la part xifrada.
        byte[] missatge = new byte[bMsgXifrat.getBytes().length - MIDA_IV];
        for (int i = 0; i < missatge.length; i++) {
            missatge[i] = bMsgXifrat.getBytes()[MIDA_IV+i];
        } 
        // Fer hash de la clau
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hash = digest.digest(password.getBytes("UTF-8"));
        SecretKeySpec key = new SecretKeySpec(hash, ALGORISME_XIFRAT);
        // Desxifrar
        IvParameterSpec ivps = new IvParameterSpec(bIv);
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE,key,ivps);
        byte[] decrypted = cipher.doFinal(missatge);

        // return String desxifrat
        return new String(decrypted);
    }

    public void obteIv(){
        SecureRandom rnd = new SecureRandom();
        rnd.nextBytes(iv);
        System.out.println(iv);
    }

}