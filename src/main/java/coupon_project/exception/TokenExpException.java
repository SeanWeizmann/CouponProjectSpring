package coupon_project.exception;

public class TokenExpException extends Throwable {
    public TokenExpException() {
        super();
    }

    public TokenExpException(String message) {
        super(message);
    }

    public TokenExpException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenExpException(Throwable cause) {
        super(cause);
    }

    protected TokenExpException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
