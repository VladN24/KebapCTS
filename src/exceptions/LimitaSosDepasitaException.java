package exceptions;

public class LimitaSosDepasitaException extends RuntimeException{
    public LimitaSosDepasitaException() {
        super("Nu poți adăuga mai mult de 3 sosuri.");
    }
}
