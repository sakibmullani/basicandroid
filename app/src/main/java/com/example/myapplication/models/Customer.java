package com.example.myapplication.models;

import java.io.Serializable;

public class Customer  implements Serializable {

    String name,city, number;

    public Customer(String name, String city, String number) {
        this.name = name;
        this.city = city;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
