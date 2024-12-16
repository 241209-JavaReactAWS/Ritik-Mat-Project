package com.revature.Project1.models;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "worlds")
public class World {
    /*
        id,
        f_rank
        e_rank
        d_rank
        c_rank
        b_rank
        a_rank
        s_rank
        ss_rank
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int f_rank;
    private int e_rank;
    private int d_rank;
    private int c_rank;
    private int b_rank;
    private int a_rank;
    private int s_rank;
    private int ss_rank;

    public World() {
        this.f_rank = 0;
        this.e_rank = 0;
        this.d_rank = 0;
        this.c_rank = 0;
        this.b_rank = 0;
        this.a_rank = 0;
        this.s_rank = 0;
        this.ss_rank = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getF_rank() {
        return f_rank;
    }

    public void setF_rank(int f_rank) {
        this.f_rank = f_rank;
    }

    public int getE_rank() {
        return e_rank;
    }

    public void setE_rank(int e_rank) {
        this.e_rank = e_rank;
    }

    public int getD_rank() {
        return d_rank;
    }

    public void setD_rank(int d_rank) {
        this.d_rank = d_rank;
    }

    public int getC_rank() {
        return c_rank;
    }

    public void setC_rank(int c_rank) {
        this.c_rank = c_rank;
    }

    public int getB_rank() {
        return b_rank;
    }

    public void setB_rank(int b_rank) {
        this.b_rank = b_rank;
    }

    public int getA_rank() {
        return a_rank;
    }

    public void setA_rank(int a_rank) {
        this.a_rank = a_rank;
    }

    public int getS_rank() {
        return s_rank;
    }

    public void setS_rank(int s_rank) {
        this.s_rank = s_rank;
    }

    public int getSs_rank() {
        return ss_rank;
    }

    public void setSs_rank(int ss_rank) {
        this.ss_rank = ss_rank;
    }

    public Map<String,Integer> convertToMap(){
        Map<String,Integer> finalMap = new HashMap<>();
        finalMap.put("F",this.f_rank);
        finalMap.put("E",this.e_rank);
        finalMap.put("D",this.d_rank);
        finalMap.put("C",this.c_rank);
        finalMap.put("B",this.b_rank);
        finalMap.put("A",this.a_rank);
        finalMap.put("S",this.s_rank);
        finalMap.put("SS",this.ss_rank);
        return finalMap;
    }
}
