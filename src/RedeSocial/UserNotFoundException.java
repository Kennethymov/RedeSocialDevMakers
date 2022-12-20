package RedeSocial;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("Usuario não encontrado!");
    }
    public UserNotFoundException(Throwable t) {
        super(t);
    }
}
