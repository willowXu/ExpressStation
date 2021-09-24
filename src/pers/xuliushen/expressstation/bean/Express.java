package pers.xuliushen.expressstation.bean;

import java.util.Objects;

/**
 * 快递
 */
public class Express {
    private String number;//单号
    private String company;//公司
    private int code;//取件码
    private int x;//存储位置
    private  int y;

    public Express(){

    }

      /*
        只要快递单号相同就是同一个快递
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Express express = (Express) o;
        return Objects.equals(number, express.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
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

    public void setPosition(int x,int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
