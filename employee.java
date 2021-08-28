package com.example.employee_details;

public class employee {
    String id;
    String name;
    String address;
    String gender;
    String joinDate;

    public employee() {
    }

    public employee(String id,String  name, String address, String gender, String joinDate) {
        this.id=id;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.joinDate = joinDate;
    }
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }


    public String getId() { return id;}

    public String getName() { return name;}

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }

    public String getJoinDate() {
        return joinDate;
    }
}


