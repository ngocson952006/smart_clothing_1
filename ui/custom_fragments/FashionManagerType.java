package com.example.ngocsonit.smartclothing.ui.custom_fragments;

import java.io.Serializable;

/**
 * Created by ngocsonit on 12/03/2016.
 * enums Fashion ManagerType : identify types of collections such as : SHIRTS , TROUSER , SHOES
 */
public enum FashionManagerType implements Serializable {
    SHIRTS("SHIRTS", 0),
    TROUSERS("TROUSERS", 1),
    SHOES("SHOES", 2);

    private final String type;
    private final int index;

    FashionManagerType(String type, int index) {
        this.type = type;
        this.index = index;
    }

    public final String getType() {
        return this.type;
    }

    public final int getIndex() {
        return this.index;
    }

}
