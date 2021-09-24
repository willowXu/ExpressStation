package pers.xuliushen.expressstation_exception.pojo;

import java.util.Objects;

/**
 * 快递
 */
public class Express {
    private String number;//单号
    private String company;//公司
    private int code;//取件码


    public Express(){

    }

    @Override
    public String toString() {
        return "快递信息{" +
                "快递号:" + number +
                ", 快递公司:" + company +
                ", 取件码:" + code +
                '}';
    }

    /**
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

}
