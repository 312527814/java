package com.my.pojo;

public class flower {
    private Integer id;

    private String name;

    private Double price;

    private String production;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private user _user;

    public user get_user() {
        return _user;
    }

    public void set_user(user _user) {
        this._user = _user;
    }

    @Override
    public String toString() {
        return "flower{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", production='" + production + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production == null ? null : production.trim();
    }
}