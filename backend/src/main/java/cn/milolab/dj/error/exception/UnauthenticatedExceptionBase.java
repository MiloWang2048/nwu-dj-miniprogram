package cn.milolab.dj.error.exception;

/**
 * @author milowang
 */
public class UnauthenticatedExceptionBase extends BaseRestException {
    public UnauthenticatedExceptionBase(String message) {
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
