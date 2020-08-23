package cn.milolab.dj.error.exception;

/**
 * @author milowang
 */
public class UnauthorizedException extends BaseRestException {
    public UnauthorizedException(String message) {
        super(message);
    }

    @Override
    public Integer getStatus() {
        return 403;
    }

    @Override
    public String getError() {
        return "Forbidden";
    }
}
