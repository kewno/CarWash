package com.example.carwash.RetrofitSecond;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PutUserAdd {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("job")
    @Expose
    private String job;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
