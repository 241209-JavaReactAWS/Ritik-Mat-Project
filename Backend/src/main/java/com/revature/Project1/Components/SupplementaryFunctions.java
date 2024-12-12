package com.revature.Project1.Components;


import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SupplementaryFunctions {


    private int multiplier;
    private static final String[] ranks = {"F","E","D","C","B","A","S","SS"};
    private static Map<String,Double> duckValueMap = new HashMap<>();
    private static final Double[] mapCosts = {0.00, 10.00, 100.00, 5000.00, 10000.00, 50000.00, 100000.00};
    private static final Double[] duckValues = {0.01,0.1,1.0,10.0,100.0,1000.0,10000.0,100000.0};
    private static final Integer[] duckChances = {60000, 70000, 100000,10000,1000,100,10,1};


    public SupplementaryFunctions() {
        for(int i = 0; i < ranks.length; i++) {
            duckValueMap.put(ranks[i], duckValues[i]);
        }
    }

    public Integer rollForDuck(int advantage, int denominator){
        long totalRoll = 0L;
        for(int i = 0; i < advantage; i++){
            long randomValue = Math.round(Math.random() * denominator);
            totalRoll = Math.max(totalRoll,randomValue);
        }
       return (int)totalRoll;
    }

    public String obtainDuckRank(int roll){
        int cumulativeValue = 0;
        for(int i = 0; i < duckChances.length; i++){
            cumulativeValue += duckChances[i];
            if(roll <= cumulativeValue) {
                return ranks[i];
            }
        }
        return null;
    }

    public Double getTotalAmount(String[] backpack, int multiplier){
        Double totalAmount = 0.0;
        for(String i : backpack){
            totalAmount += duckValueMap.getOrDefault(i,0.0);
        }
        return totalAmount * multiplier;
    }

}

