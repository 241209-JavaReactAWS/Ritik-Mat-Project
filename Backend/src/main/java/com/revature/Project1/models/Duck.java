package com.revature.Project1.models;

import jakarta.persistence.*;

@Entity
@Table(name = "ducks")
public class Duck {
    /*
    id,
    reference_id
    rank
    nickname
     */
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="referenceId")
    private int referenceId;

    private String rank;
    private String nickname;

    public Duck(){

    }

    public Duck(String rank) {
        this.rank = rank;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReference_id() {
        return referenceId;
    }

    public void setReference_id(int reference_id) {
        this.referenceId = reference_id;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
