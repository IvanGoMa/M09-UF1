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

    public String xifraRotX(String cadena, int desplaçament){
        return xifraDesxifra(cadena, desplaçament, true);
    }
    public String desxifraRotX(String cadena, int desplaçament){
        return xifraDesxifra(cadena, desplaçament, false);
    }
    public void forcaBrutaRotX(String cadenaXifrada){
        for (int i = 0; i < MAJUSCULES.length; i++) {
            System.out.println("(" + i +")->" +desxifraRotX(cadenaXifrada, i));
        }
    }
    public void main(String[] args) {
        System.out.println(desxifraRotX("ZAÀ",2));
        

        String [] proves = {"ABC","XYZ","Hola, Mr. calçot","Perdó, per tú què és?"};
        String [] xifrades = new String[proves.length];
        String [] desxifrades = new String[proves.length];

        System.out.println("Xifrat");
        System.out.println("------");
        for (int i = 0; i < proves.length; i++) {
            xifrades[i] = xifraRotX(proves[i], i*2);
            System.out.printf("(%d)-%s\t\t\t\t=> %s\n",i*2,proves[i],xifrades[i]);
        }
        System.out.println();
        System.out.println("Desxifrat");
        System.out.println("---------");
        for (int i = 0; i < xifrades.length; i++) {
            desxifrades[i] = desxifraRotX(xifrades[i], i*2);
            System.out.printf("(%d)-%s\t\t\t\t=> %s\n",i*2,xifrades[i],desxifrades[i]);
        }

        String missatgeXifrat = xifraRotX(proves[proves.length-1], 13);
        System.out.println();
        System.out.println("Missatge xifrat: " + missatgeXifrat);
        System.out.println("----------------");
        forcaBrutaRotX(missatgeXifrat);
        
    }
}