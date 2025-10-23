package iticbcn.xifratge;

public class AlgorismeMonoalfabetic extends AlgorismeFactory {
    
    @Override
    public Xifrador creaXifrador(){
        Xifrador mono = new XifradorMonoalfabetic();
        return mono;
    }
}
