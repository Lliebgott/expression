package com.asteroid.expression.sys.sysenum;

/**
 * @author: YuSai
 * @date: 2020-09-27 12:29
 */
public enum StateEnum {

    COMMENT("评论", 1),
    PRAISE("点赞", 2);

    private int id;

    private String name;

    StateEnum(String name, int id) {
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
        System.out.println(StateEnum.COMMENT.getId());
    }

}
