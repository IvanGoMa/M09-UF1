package iticbcn.xifratge;

public class AlgorismePolialfabetic extends AlgorismeFactory{
    
    @Override
    public Xifrador creaXifrador(){
        Xifrador poli = new XifradorPolialfabetic();
        return poli;
    }
}
