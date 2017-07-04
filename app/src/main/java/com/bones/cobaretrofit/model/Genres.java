package com.bones.cobaretrofit.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lenovo ip on 22/06/2017.
 */

public class Genres {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
