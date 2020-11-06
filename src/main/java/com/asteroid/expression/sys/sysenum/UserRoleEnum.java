package com.asteroid.expression.sys.sysenum;

/**
 * @author: YuSai
 * @date: 2020-10-23 17:49
 */
public enum UserRoleEnum {

    NORMAL(2,  "ROLE_USER","普通用户"),

    SYS_ADMIN(1, "ROLE_ADMIN", "系统管理员");


    private int id;

    private String enName;

    private String roleName;

    UserRoleEnum(int id, String enName, String roleName) {
        this.id = id;
        this.enName = enName;
        this.roleName = roleName;
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // get set 方法
    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    // get set 方法
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
