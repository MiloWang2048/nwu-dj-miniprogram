package cn.milolab.dj.error.exception;

/**
 * @author milowang
 */
public class InternalServerErrorExceptionBase extends BaseRestException {
    public InternalServerErrorExceptionBase(String message) {
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
