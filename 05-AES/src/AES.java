import javax.crypto.spec.IvParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.security.MessageDigest;


public class AES{

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private final static String CLAU ="";

    public static byte[] xifraAES(String msg, String password) throws Exception{
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
        byte[] bIv = ivps.getIV();
        byte[]ivEncrypted = new byte[encrypted.length+bIv.length];
        // return iv+msgxifrat
        return new byte[0];
    }

    public static String desxifraAES(byte[] bMsgXifrat, String password) throws Exception{
        // Extreure l'IV
        // Extreure la part xifrada.
        // Fer hash de la clau
        // Desxifrar
        // return String desxifrat
        return new String();
    }

    public static void obteIv(){
        SecureRandom rnd = new SecureRandom();
        rnd.nextBytes(iv);
    }

    public static void main(String[] args) {
        String msgs[] = {"Lorem ipsum dicet","Hola Andrés cómo está tu cuñado","Àgora ïlla Ôtto"};
        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];

            byte[] bXifrats = null;
            String desxifrat = "";
            try{
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifrats, CLAU);
            }
            catch (Exception e){
                System.err.println("Error de xifrat: "
                + e.getLocalizedMessage());
            }
            System.out.println("----------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifrats));
            System.out.println("DEC: " + desxifrat);
        }
    }
}