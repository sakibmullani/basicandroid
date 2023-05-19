package com.example.myapplication;

import java.util.List;

public class nestedRV_parentModelClass {

    String title;
    List<nestedRV_childModelClass>nestedRV_childModelClasses;

    public nestedRV_parentModelClass(String title, List<nestedRV_childModelClass> nestedRV_childModelClasses) {
        this.title = title;
        this.nestedRV_childModelClasses = nestedRV_childModelClasses;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<nestedRV_childModelClass> getNestedRV_childModelClasses() {
        return nestedRV_childModelClasses;
    }

    public void setNestedRV_childModelClasses(List<nestedRV_childModelClass> nestedRV_childModelClasses) {
        this.nestedRV_childModelClasses = nestedRV_childModelClasses;
    }
}
