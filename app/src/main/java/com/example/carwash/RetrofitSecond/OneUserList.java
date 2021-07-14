package com.example.carwash.RetrofitSecond;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OneUserList {
    @SerializedName("data")
    @Expose
    private com.example.carwash.RetrofitSecond.OneUser data;
    @SerializedName("support")

    public com.example.carwash.RetrofitSecond.OneUser getOneUser() {
        return data;
    }

    public void setData(com.example.carwash.RetrofitSecond.OneUser data) {
        this.data = data;
    }
}
