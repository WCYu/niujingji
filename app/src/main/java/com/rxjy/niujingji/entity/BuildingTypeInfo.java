package com.rxjy.niujingji.entity;

/**
 * Created by AAA on 2017/9/8.
 */

public class BuildingTypeInfo {

    private String type;
    private int count;
    private int typeID;
    private int isSelector;

    public BuildingTypeInfo() {
    }

    public BuildingTypeInfo(String type, int count, int typeID, int isSelector) {
        this.type = type;
        this.count = count;
        this.typeID = typeID;
        this.isSelector = isSelector;
    }

    public int getIsSelector() {
        return isSelector;
    }

    public void setIsSelector(int isSelector) {
        this.isSelector = isSelector;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }
}
