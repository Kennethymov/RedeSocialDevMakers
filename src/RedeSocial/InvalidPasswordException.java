package RedeSocial;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException() {
        super("Senha inválida!!!");
    }
    public InvalidPasswordException(Throwable t) {
        super(t);
    }
}
