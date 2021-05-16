package cn.xnmll.demo_2020.exception;

/**
 * @author xnmll
 * @create 2021-05-2021/5/16  20:39
 */

public class CustomizeException extends RuntimeException{
    private String message;

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public CustomizeException(String message) {
        this.message = message;
    }
}
