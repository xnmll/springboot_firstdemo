package cn.xnmll.demo_2020.exception;

/**
 * @author xnmll
 * @create 2021-05-2021/5/16  20:53
 */

public enum  CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND("你找到问题不在了，要不换个试试");

    private String message;

    @Override
    public String getMessage(){
        return message;
    }


    CustomizeErrorCode(String message) {
        this.message = message;
    }
}
