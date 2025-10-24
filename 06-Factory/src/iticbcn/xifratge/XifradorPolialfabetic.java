package iticbcn.xifratge;
import java.util.*;


public class XifradorPolialfabetic implements Xifrador{
    static final char[] ALFABET = "aàáäbcçdeèéëfghiìíïjklmnñoòóöpqrstuùúüvwxyz".toUpperCase().toCharArray();
    char [] PERMUTAT;
    static private final long CLAU = 1234;
    private Random random;

    public void initRandom(long clau){
        random = new Random(CLAU);
    }

    public TextXifrat xifra(String msg, String clau){
        return new TextXifrat(xifraDesxifra(msg, true));
    }

    public String desxifra(TextXifrat msg, String clau){
        return xifraDesxifra(new String(msg.getBytes()), false);
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


}
