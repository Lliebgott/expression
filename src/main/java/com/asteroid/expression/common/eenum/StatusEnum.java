package com.asteroid.expression.common.eenum;

/**
 * @author: YuSai
 * @date: 2020-09-24 15:15
 */
public enum StatusEnum {

    EFFECTIVE("有效", 1),
    INVALID("无效", 0);

    private int id;

    private String name;

    StatusEnum(String name, int id) {
        this.name = name;
        this.id = id;
    }

    // get set 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static void main(String[] args) {
        System.out.println(StatusEnum.EFFECTIVE.getId());
    }

}
