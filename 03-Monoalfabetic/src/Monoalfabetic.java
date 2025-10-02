
import java.util.*;
public class Monoalfabetic {
    static final char[] ALFABET = "aàáäbcçdeèéëfghiìíïjklmnñoòóöpqrstuùúüvwxyz".toUpperCase().toCharArray();
    private static final char[] PERMUTAT = permutaAlfabet(ALFABET);

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
        System.out.print("Alfabet: ");
        System.out.println(ALFABET);
        System.out.print("Alfabet permutat: ");
        System.out.println(PERMUTAT);
        System.out.println();
        String[] proves = {"ABC","XYZ","Hola, Mr.Calçot","Perdó, per tú què és?"};
        String[] xifrades = new String[proves.length];
        for (int i = 0; i < proves.length; i++) {
            xifrades[i] = xifraMonoAlfa(proves[i]);
        }
        String [] desxifrades = new String[xifrades.length];
        for (int i = 0; i < xifrades.length; i++) {
            desxifrades[i] = desxifraMonoAlfa(xifrades[i]);
        }
        System.out.println("Xifrat");
        System.out.println("-----");
        System.out.println(String.format("%s --> %s",proves[0],xifrades[0]));
        System.out.println(String.format("%s --> %s",proves[1],xifrades[1]));
        System.out.println(String.format("%s --> %s",proves[2],xifrades[2]));
        System.out.println(String.format("%s --> %s",proves[3],xifrades[3]));
        System.out.println();
        System.out.println("Desxifrat");
        System.out.println("---------");
        System.out.println(String.format("%s --> %s",xifrades[0],desxifrades[0]));
        System.out.println(String.format("%s --> %s",xifrades[1],desxifrades[1]));
        System.out.println(String.format("%s --> %s",xifrades[2],desxifrades[2]));
        System.out.println(String.format("%s --> %s",xifrades[3],desxifrades[3]));

    }
}
