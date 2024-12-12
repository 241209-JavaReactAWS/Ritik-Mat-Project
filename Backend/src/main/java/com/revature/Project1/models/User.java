package com.revature.Project1.models;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    /*
     * id (Primary Key)
     * username
     * password
     * bankAccount
     * backpackSpace
     * admin
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;
    private double bank_account;
    private int backpack_space;
    private int admin;

    public User(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBank_account() {
        return bank_account;
    }

    public void setBank_account(float bank_account) {
        this.bank_account = bank_account;
    }

    public int getBackpack_space() {
        return backpack_space;
    }

    public void setBackpack_space(int backpack_space) {
        this.backpack_space = backpack_space;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
