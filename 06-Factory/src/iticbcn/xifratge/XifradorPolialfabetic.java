package iticbcn.xifratge;
import java.util.*;


public class XifradorPolialfabetic {
    static final char[] ALFABET = "aàáäbcçdeèéëfghiìíïjklmnñoòóöpqrstuùúüvwxyz".toUpperCase().toCharArray();
    char [] PERMUTAT;
    static private final long CLAU = 1234;
    private Random random;

    public void initRandom(long clau){
        random = new Random(CLAU);
    }

    public String xifraPoliAlfa(String msg){
        return xifraDesxifra(msg, true);
    }

    public String desxifraPoliAlfa(String msg){
        return xifraDesxifra(msg, false);
    }

    public String xifraDesxifra(String msg, boolean xifra){
        StringBuilder xifrada = new StringBuilder();
        PERMUTAT = permutaAlfabet(ALFABET);
        for (int i = 0; i < msg.length(); i++) {
            char c = msg.charAt(i);
            xifrada.append(retornaCaracterXifratODesxifrat(c, xifra, PERMUTAT));
            PERMUTAT = permutaAlfabet(PERMUTAT);
        }
        return xifrada.toString();
    }

    public char retornaCaracterXifratODesxifrat(char caracter, boolean xifra, char[] PERMUTAT){
        char[] alfabetOriginal =new char[ALFABET.length];
        char[] alfabetTraduit =new char[ALFABET.length];
        if (xifra){
            alfabetOriginal = ALFABET;
            alfabetTraduit = PERMUTAT;
        } else{
            alfabetOriginal = PERMUTAT;
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

    public char[] permutaAlfabet(char[] alfabet){
        char[] alfabetPERMUTAT = new char[alfabet.length];
        ArrayList<Character> llistaAlfabet= new ArrayList<Character>();
        for (Character c : alfabet){
            llistaAlfabet.add(c);
        }
        Collections.shuffle(llistaAlfabet,random);
        for (int i = 0; i < alfabet.length; i++) {
            alfabetPERMUTAT[i]= llistaAlfabet.get(i);
        }
        return alfabetPERMUTAT;
    }


     public void main(String[] args){
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
}
