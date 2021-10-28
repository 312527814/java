package com.my.pojo;

import java.io.Serializable;
import java.util.Date;

public class Card implements Serializable {
    private Integer id;
    private Long cardId;
    private String password;
    private Integer cardState;
    private Integer async;
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getCardState() {
        return cardState;
    }

    public void setCardState(Integer cardState) {
        this.cardState = cardState;
    }

    public Integer getAsync() {
        return async;
    }

    public void setAsync(Integer async) {
        this.async = async;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}