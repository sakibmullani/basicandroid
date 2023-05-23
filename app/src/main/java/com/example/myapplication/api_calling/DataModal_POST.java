package com.example.myapplication.api_calling;

public class DataModal_POST {

    // string variables for our name and job
    private String name;
    private String job;

    public DataModal_POST(String name, String job) {
        this.name = name;
        this.job = job;
    }

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
