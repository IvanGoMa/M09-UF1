package iticbcn.xifratge;

public class AlgorismeRotX extends AlgorismeFactory{
    
    @Override
    public Xifrador creaXifrador(){
        Xifrador rotx = new XifradorRotX();
        return rotx;
    }
}
