package iticbcn.xifratge;
import java.util.*;
public class XifradorMonoalfabetic implements Xifrador {
    static final char[] ALFABET = "aàáäbcçdeèéëfghiìíïjklmnñoòóöpqrstuùúüvwxyz".toUpperCase().toCharArray();
    private static final char[] PERMUTAT = permutaAlfabet(ALFABET);

    private static char[] permutaAlfabet(char[] alfabet){
        char[] alfabetPermutat = new char[alfabet.length];
        ArrayList<String> llistaAlfabet= new ArrayList<String>();
        for (int i = 0; i < alfabet.length; i++) {
            llistaAlfabet.add(""+alfabet[i]);
        }
        Collections.shuffle(llistaAlfabet);
        for (int i = 0; i < alfabet.length; i++) {
            alfabetPermutat[i]= llistaAlfabet.get(i).charAt(0);
        }
        return alfabetPermutat;
    }

    public String xifraODesxifra(String cadena, boolean xifra){
        
        StringBuilder xifrada = new StringBuilder();
        for (int i = 0; i < cadena.length(); i++) {
            xifrada.append(retornaCaracterXifratODesxifrat(cadena.charAt(i), xifra));
        }
        return xifrada.toString();
    }

    public TextXifrat xifra(String cadena, String clau) throws ClauNoSuportada{
        return new TextXifrat(xifraODesxifra(cadena, true));
    }
    public String desxifra(TextXifrat cadena, String clau){
        return xifraODesxifra(new String(cadena.getBytes()), false);
    }

    public char retornaCaracterXifratODesxifrat(char caracter, boolean xifra){
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

}