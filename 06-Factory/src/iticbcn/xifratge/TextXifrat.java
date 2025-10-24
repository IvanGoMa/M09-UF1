package iticbcn.xifratge;

public class TextXifrat {
    private byte[] xifrat;

    public TextXifrat(String text){
        xifrat = text.getBytes();
    }
    public TextXifrat(byte[] bytes){
        xifrat = bytes;
    }

    @Override
    public String toString(){
        return new String(xifrat);
    }
    public byte[] getBytes(){
        return xifrat;
    }
    

}
