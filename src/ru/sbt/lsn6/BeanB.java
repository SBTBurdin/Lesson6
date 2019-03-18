package ru.sbt.lsn6;

public class BeanB {
    private String name;
    private Integer number;
    private Boolean flag;

    public BeanB(String name, Integer number, Boolean flag) {
        this.name = name;
        this.number = number;
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    private void setNumber(Integer number) {
        this.number = number;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "BeanB{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", flag=" + flag +
                '}';
    }
}
