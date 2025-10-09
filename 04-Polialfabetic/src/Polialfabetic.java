import java.util.*;


public class Polialfabetic {
    static final char[] ALFABET = "aàáäbcçdeèéëfghiìíïjklmnñoòóöpqrstuùúüvwxyz".toUpperCase().toCharArray();
    static private final long CLAU = 1234;
    static private Random random;

    public static void initRandom(long clau){
        random = new Random(CLAU);
    }

    public static String xifraPoliAlfa(String msg){
        return xifraDesxifra(msg, true);
    }

    public static String desxifraPoliAlfa(String msg){
        return xifraDesxifra(msg, false);
    }

    public static void main(String[] args){
        String msgs[] = {"Test 01 àrbritre, coixí, Perímetre",
        "Test 02 Taüll, DÍA, año",
        "Test 03 Peça, Òrrius, Bòvila"};
        String msgsXifrats[] = new String[msgs.length];
        System.out.println("Xifratge:\n--------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(CLAU);
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);

        }
        System.out.println("Desxifratge:\n-----------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(CLAU);
            String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
            
        }
    
    }

        public static String xifraDesxifra(String msg, boolean xifra){
            StringBuilder xifrada = new StringBuilder();
            char[] permutat = permutaAlfabet(ALFABET);
            for (int i = 0; i < msg.length(); i++) {
                char c = msg.charAt(i);
                xifrada.append(retornaCaracterXifratODesxifrat(c, xifra, permutat));
                permutat = permutaAlfabet(permutat);
            }
            return xifrada.toString();
        }

    public static char retornaCaracterXifratODesxifrat(char caracter, boolean xifra, char[] permutat){
        char[] alfabetOriginal =new char[ALFABET.length];
        char[] alfabetTraduit =new char[ALFABET.length];
        if (xifra){
            alfabetOriginal = ALFABET;
            alfabetTraduit = permutat;
        } else{
            alfabetOriginal = permutat;
            alfabetTraduit = ALFABET;
        }
        for (int i = 0; i < alfabetOriginal.length; i++) {
            if(caracter == alfabetOriginal[i] || Character.toUpperCase(caracter) == alfabetOriginal[i]){
                if (Character.isUpperCase(caracter)){
                    return alfabetTraduit[i];
                }
                else{
                    return Character.toLowerCase(alfabetTraduit[i]);
                }
                
            }
        }
        return caracter;
    }

    public static char[] permutaAlfabet(char[] alfabet){
        char[] alfabetPermutat = new char[alfabet.length];
        ArrayList<Character> llistaAlfabet= new ArrayList<Character>();
        for (Character c : alfabet){
            llistaAlfabet.add(c);
        }
        Collections.shuffle(llistaAlfabet,random);
        for (int i = 0; i < alfabet.length; i++) {
            alfabetPermutat[i]= llistaAlfabet.get(i);
        }
        return alfabetPermutat;
    }
}
