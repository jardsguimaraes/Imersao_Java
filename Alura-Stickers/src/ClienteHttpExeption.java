
public class ClienteHttpExeption extends RuntimeException{

    public ClienteHttpExeption(String menssagem, Exception e) {
        super(menssagem, e);
    }

}
