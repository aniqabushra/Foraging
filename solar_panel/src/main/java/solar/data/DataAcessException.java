package solar.data;

public class DataAcessException extends Exception{
    public DataAcessException(String message, Throwable rootcause){
        super(message,rootcause); //override the constructor
    }
}
