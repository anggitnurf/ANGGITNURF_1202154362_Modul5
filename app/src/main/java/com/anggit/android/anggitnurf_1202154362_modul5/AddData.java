package com.anggit.android.anggitnurf_1202154362_modul5;

/**
 * Created by Anggit Nur on 3/25/2018.
 */

public class AddData {
    //declare variable
    String todo, desc, prior;

    //constructor
    public AddData(String todo, String desc, String prior){
        this.todo=todo;
        this.desc=desc;
        this.prior=prior;
    }

    //setter getter method for to do activity, description, and priority
    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrior() {
        return prior;
    }

    public void setPrior(String prior) {
        this.prior = prior;
    }
}
