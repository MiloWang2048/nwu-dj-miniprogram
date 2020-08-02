package cn.milolab.dj.error.exception;

/**
 * @author milowang
 */
public class UnauthenticatedException extends BaseRestException {
    public UnauthenticatedException(String message) {
        super(message);
    }

    @Override
    public Integer getStatus() {
        return 401;
    }

    @Override
    public String getError() {
        return "Unauthorized";
    }
}
