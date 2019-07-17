package com.liyan.pojo;

public class Log {
    private Integer id;
    private String accOut;
    private String accIn;
    private Double money;

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", accOut='" + accOut + '\'' +
                ", accIn='" + accIn + '\'' +
                ", money=" + money +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccOut() {
        return accOut;
    }

    public void setAccOut(String accOut) {
        this.accOut = accOut;
    }

    public String getAccIn() {
        return accIn;
    }

    public void setAccIn(String accIn) {
        this.accIn = accIn;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
