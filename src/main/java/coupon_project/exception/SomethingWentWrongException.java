package coupon_project.exception;

public class SomethingWentWrongException extends Exception{


    public SomethingWentWrongException() {
        super();
    }

    public SomethingWentWrongException(String message) {
        super(message);
    }

    public SomethingWentWrongException(String message, Throwable cause) {
        super(message, cause);
    }

    public SomethingWentWrongException(Throwable cause) {
        super(cause);
    }

    protected SomethingWentWrongException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
