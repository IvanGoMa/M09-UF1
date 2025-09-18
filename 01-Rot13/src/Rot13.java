public class Rot13{

    static String stringMinuscules = "aàáäbcçdeèéëfghiìíïjklmnñoòóöpqrstuùúüvwxyz";
    static char[] minuscules = stringMinuscules.toCharArray();
    static char[] majuscules = stringMinuscules.toUpperCase().toCharArray();
    
    public static String xifraRot13(String cadena){
        
        String resultat = "";
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            int novaPosicio = posicioTrasposada(c, minuscules, true);
            if (novaPosicio>=0){
                resultat += minuscules[novaPosicio];
            }
            else {
                novaPosicio = posicioTrasposada(c, majuscules,true);
                    if (novaPosicio>=0){
                        resultat += majuscules[novaPosicio];
                    }
                    else {
                        resultat += c;
                    }
            }
            
        }
        return resultat;
    }
    public static String desxifraRot13(String cadena){
        String resultat = "";
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            int novaPosicio = posicioTrasposada(c, minuscules, false);
            if (novaPosicio>=0){
                resultat += minuscules[novaPosicio];
            }
            else {
                novaPosicio = posicioTrasposada(c, majuscules,false);
                    if (novaPosicio>=0){
                        resultat += majuscules[novaPosicio];
                    }
                    else {
                        resultat += c;
                    }
            }

            
        }
        return resultat;
    }

    // Troba la posició del caràcter pel que hem de canviar l'original. Boolean codifica indica si es codifica o es descodifica.
    public static int posicioTrasposada(char c,char[] lletres,boolean codifica){
        int posicio = -1;
        for (int j = 0; j < lletres.length; j++) {
            if (c == lletres[j]){
                // Codificar
                if (codifica){
                    posicio = (j+13)%lletres.length;
                }
                // Descodificar
                else{
                    posicio = (j+(lletres.length-13))%lletres.length;
                }
                
                break;
            }
        }
        return posicio;

    }
    public static void main(String[] args) {
        System.out.println(xifraRot13("Hola, Mr.calçot"));
        System.out.println(desxifraRot13("Óvug, Ùà.ïgujvä"));
    }
}