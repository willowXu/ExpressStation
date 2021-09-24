package pers.xuliushen.expressstation_list.exception;

/**
 * 自定义异常类：数字超出规定范围
 * @author xuliushhen
 * @Time 20217/13 15:32
 */
public class OutNumberBoundException extends Throwable {
    public OutNumberBoundException(String s) {
        super(s);
    }
}
