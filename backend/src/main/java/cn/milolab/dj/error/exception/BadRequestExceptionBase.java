package cn.milolab.dj.error.exception;

/**
 * @author milowang
 */
public class BadRequestExceptionBase extends BaseRestException {
    public BadRequestExceptionBase(String message) {
        super(message);
    }

    @Override
    public Integer getStatus() {
        return 400;
    }

    @Override
    public String getError() {
        return "Bad Request";
    }
}
