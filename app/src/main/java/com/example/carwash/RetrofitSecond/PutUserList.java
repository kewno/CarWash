package com.example.carwash.RetrofitSecond;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PutUserList {
    @SerializedName("data")
    @Expose
    private PutUser data;
    @SerializedName("support")

    public PutUser getData() {
        return data;
    }

    public void setData(PutUser data) {
        this.data = data;
    }
}
