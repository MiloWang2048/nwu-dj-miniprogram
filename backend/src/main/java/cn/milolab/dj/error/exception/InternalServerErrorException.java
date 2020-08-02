package cn.milolab.dj.error.exception;

/**
 * @author milowang
 */
public class InternalServerErrorException extends BaseRestException {
    public InternalServerErrorException(String message) {
        super(message);
    }

    @Override
    public Integer getStatus() {
        return 500;
    }

    @Override
    public String getError() {
        return "Internal Server Error";
    }
}
