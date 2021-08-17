package com.my.pojo;

public enum UserStatus2 {
    LOW(30), MEDIUM(15), HIGH(7), URGENT(1);

    // Declare an instance variable
    private int levelValue;

    // Declare a private constructor
    private UserStatus2(int levelValue) {
        this.levelValue = levelValue;
    }

    public int getLevelValue() {
        return levelValue;
    }
}
