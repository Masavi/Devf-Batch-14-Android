package com.masavi.datapersistence.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/*
 * Created by Masavi on 4/5/2017.
 */

//This model will be mapped to a table, using Realm
public class User extends RealmObject{

    private String name;
    private String userName;
    private int age;
    private int phone;
    private String email;
    @PrimaryKey
    private long id;

    //Empty Constructor, just as a good coding practice
    public User(){
    }

    public User(String name, String userName, int age, int phone, String email) {
        this.name = name;
        this.userName = userName;
        this.age = age;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "nombre: " + name;
    }
}
