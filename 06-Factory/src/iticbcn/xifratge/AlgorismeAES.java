package iticbcn.xifratge;

public class AlgorismeAES extends AlgorismeFactory {

    @Override
    public Xifrador creaXifrador(){
        Xifrador aes = new XifradorAES();
        return aes;
    }
    
}
