package iticbcn.xifratge;

public class XifradorRotX implements Xifrador{
    static final char[] MINUSCULES = "aàáäbcçdeèéëfghiìíïjklmnñoòóöpqrstuùúüvwxyz".toCharArray();
    static final char[] MAJUSCULES = "aàáäbcçdeèéëfghiìíïjklmnñoòóöpqrstuùúüvwxyz".toUpperCase().toCharArray();

    public int posicioTrasposada(char c,char[] lletres, int desplaçament, boolean codifica){
        int posicio = -1;
        for (int j = 0; j < lletres.length; j++) {
            if (c == lletres[j]){
                // Codificar
                if (codifica){
                    posicio = (j+desplaçament)%lletres.length;
                }
                // Descodificar
                else{
                    posicio = (j-desplaçament);
                    if (posicio <0){
                        posicio = lletres.length + posicio%lletres.length;
                    }
                }
                
                return posicio;
            }
        }
        return posicio;

    }

    public String xifraDesxifra(String cadena, int desplaçament, boolean xifra){
        StringBuilder resultat = new StringBuilder();
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            int novaPosicio = posicioTrasposada(c, MINUSCULES, desplaçament,xifra);
            if (novaPosicio>=0){
                resultat.append(MINUSCULES[novaPosicio]);
            }
            else {
                novaPosicio = posicioTrasposada(c, MAJUSCULES, desplaçament, xifra);
                    if (novaPosicio>=0){
                        resultat.append(MAJUSCULES[novaPosicio]);
                    }
                    else {
                        resultat.append(c);
                    }
            }

            
        }
        return resultat.toString();
    }

    public TextXifrat xifra(String cadena, String desplaçament){
        return new TextXifrat(xifraDesxifra(cadena, Integer.parseInt(desplaçament), true));
    }
    public String desxifra(TextXifrat cadena, String desplaçament){
        return xifraDesxifra(new String(cadena.getBytes()), Integer.parseInt((desplaçament)), false);
    }
}