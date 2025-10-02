
import java.util.*;
public class Monoalfabetic {
    static final char[] ALFABET = "aàáäbcçdeèéëfghiìíïjklmnñoòóöpqrstuùúüvwxyz".toUpperCase().toCharArray();
    static final char[] PERMUTAT = permutaAlfabet(ALFABET);

    public static char[] permutaAlfabet(char[] alfabet){
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

    public static String xifraODesxifra(String cadena, boolean xifra){
        
        StringBuilder xifrada = new StringBuilder();
        for (int i = 0; i < cadena.length(); i++) {
            xifrada.append(retornaCaracterXifratODesxifrat(cadena.charAt(i), xifra));
        }
        return xifrada.toString();
    }

    public static String xifraMonoAlfa(String cadena){
        return xifraODesxifra(cadena, true);
    }
    public static String desxifraMonoAlfa(String cadena){
        return xifraODesxifra(cadena, false);
    }

    public static char retornaCaracterXifratODesxifrat(char caracter, boolean xifra){
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

    public static void main(String[] args) {
        System.out.println(ALFABET);
        System.out.println(PERMUTAT);
        System.out.println(xifraMonoAlfa("Hola, Mr. Calçot!"));
        System.out.println(desxifraMonoAlfa(xifraMonoAlfa("Hola, Mr. Calçot!")));
    }
}
