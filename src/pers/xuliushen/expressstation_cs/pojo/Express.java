package pers.xuliushen.expressstation_cs.pojo;

import java.io.Serializable;

/**
 * 快递
 */
public class Express implements Serializable{
    private String number;//单号
    private String company;//公司
    private int code;//取件码
    private Integer x;
    private Integer y;

    public Express(){

    }

    public Express(String number, String company, int code, Integer x, Integer y) {
        this.number = number;
        this.company = company;
        this.code = code;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "快递信息{" +
                "快递号:" + number +
                ", 快递公司:" + company +
                ", 取件码:" + code +
                ",行数:" + x +
                "列数:" + y +
                '}';
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}
