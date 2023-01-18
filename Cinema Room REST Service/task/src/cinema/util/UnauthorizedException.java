package cinema.util;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super( message );
    }

    public UnauthorizedException() {
        super();
    }

    public UnauthorizedException(String message, Throwable cause) {
        super( message, cause );
    }

    public UnauthorizedException(Throwable cause) {
        super( cause );
    }
}
