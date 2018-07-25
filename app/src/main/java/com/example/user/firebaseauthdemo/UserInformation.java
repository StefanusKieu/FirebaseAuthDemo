package com.example.user.firebaseauthdemo;

public class UserInformation {

    public String address,uID;
    public String name;
    //public String name="Not entered";
    public UserInformation(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public UserInformation(String name, String address, String uID) {
        this.name = name;
        this.address = address;
        this.uID = uID;
    }
}
