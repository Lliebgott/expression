package com.asteroid.expression.user.model;

/**
 * @author: YuSai
 * @date: 2020-09-27 12:47
 */
public class Comment {

    private Integer id;

    private Integer content_id;

    private Integer user_id;

    private Integer frient_id;

    private Integer state;

    private String comment;

    private Integer praise;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getContent_id() {
        return content_id;
    }

    public void setContent_id(Integer content_id) {
        this.content_id = content_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getFrient_id() {
        return frient_id;
    }

    public void setFrient_id(Integer frient_id) {
        this.frient_id = frient_id;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getPraise() {
        return praise;
    }

    public void setPraise(Integer praise) {
        this.praise = praise;
    }

}
