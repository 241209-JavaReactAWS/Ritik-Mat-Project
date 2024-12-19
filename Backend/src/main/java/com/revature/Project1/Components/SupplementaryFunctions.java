package com.revature.Project1.Components;


import com.revature.Project1.models.Duck;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SupplementaryFunctions {


    private int multiplier;
    private static final String[] ranks = {"F","E","D","C","B","A","S","SS"};
    private static Map<String,Double> duckValueMap = new HashMap<>();
    private static final Double[] mapCosts = {0.00, 10.00, 100.00, 5000.00, 10000.00, 50000.00, 100000.00};
    private static final Double[] duckValues = {0.01,0.1,1.0,10.0,100.0,1000.0,10000.0,100000.0};
    public static final Integer[] duckChances = {60000, 70000, 100000,10000,1000,100,10,1};


    public SupplementaryFunctions() {
        for(int i = 0; i < ranks.length; i++) {
            duckValueMap.put(ranks[i], duckValues[i]);
        }
    }

    public Integer rollForDuck(){
        int denominator = getDenominator();
        long randomValue = Math.round(Math.random() * denominator);
        return (int)randomValue;
    }

    public Integer getDenominator(){
        int denominator = 0;
        for(int i : duckChances) {
            denominator += i;
        }
        return denominator;
    }

    public Integer rollForDuck(int advantage){
        long totalRoll = 0L;
        for(int i = 0; i < advantage; i++){
            long randomValue = Math.round(Math.random() * getDenominator());
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

    public Double getTotalAmount(List<Duck> ducks, int multiplier){
        Double totalAmount = 0.0;
        for(Duck i : ducks){
            totalAmount += duckValueMap.getOrDefault(i.getRank(),0.0);
        }
        return totalAmount * multiplier;
    }

    public Boolean duckAvailable(String rank, Map<String,Integer> DuckAmounts){
        if(rank.equals("F")) return true;
        if(rank.equals("E")) return true;
        if(rank.equals("D")) return true;
        if(rank.equals("C")) return true;
        if(DuckAmounts.getOrDefault(rank,0) != 0) return true;
        return false;
    }

}

