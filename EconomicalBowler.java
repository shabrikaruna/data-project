package com.mountblue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class EconomicalBowler {

    public static void main(String[] args){

        String deliveries_path = "/Users/shabrikaruna/Desktop/01_sabarinathan_data_project/ipl/deliveries.csv";
        BufferedReader deliveriesReader = null;
        String line = "";
        String delimiter = ",";
        HashMap<String, Integer> bowlerAndRuns = new HashMap<>();
        HashMap<String, Integer> bowlerAndBalls = new HashMap<>();
        ArrayList<Integer> balls = new ArrayList<Integer>();
        ArrayList<Integer> runs = new ArrayList<Integer>();
        ArrayList<String> playerNames = new ArrayList<String>();
        HashMap<Double ,String> playerAndEconomy = new HashMap<Double,String>();

        try {

            deliveriesReader = new BufferedReader(new FileReader(deliveries_path));
            deliveriesReader.readLine();
            while ((line = deliveriesReader.readLine()) != null) {

                final int ID_INDEX = 0;
                final int BOWLER_INDEX = 8;
                final int TOTAL_RUNS_INDEX = 17;

                // use comma as separator
                String[] lines = line.split(delimiter);
                if( Integer.parseInt(lines[ID_INDEX]) >= 518 && Integer.parseInt(lines[ID_INDEX]) <= 576 ){
                    if(bowlerAndRuns.containsKey(lines[BOWLER_INDEX])){
                        bowlerAndRuns.put( lines[BOWLER_INDEX] , bowlerAndRuns.get(lines[BOWLER_INDEX])+Integer.parseInt(lines[TOTAL_RUNS_INDEX]) );
                    }else{
                        bowlerAndRuns.put( lines[BOWLER_INDEX] , Integer.parseInt(lines[TOTAL_RUNS_INDEX]));
                    }

                    if(bowlerAndBalls.containsKey(lines[BOWLER_INDEX])){
                        bowlerAndBalls.put( lines[BOWLER_INDEX] , bowlerAndBalls.get(lines[BOWLER_INDEX])+ 1 );
                    }else{
                        bowlerAndBalls.put( lines[BOWLER_INDEX] , 1 );
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (deliveriesReader != null) {
                try {
                    deliveriesReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        for(Map.Entry<String, Integer> entry: bowlerAndRuns.entrySet()) {
            runs.add(entry.getValue());
            playerNames.add(entry.getKey());
        }

        for(Map.Entry<String, Integer> entry: bowlerAndBalls.entrySet()) {
            balls.add(entry.getValue());
        }

        double economy;
        for( int i=0 ; i<playerNames.size() ; i++ ){
            economy = ((float)runs.get(i) / (float)balls.get(i)) * 6;
            playerAndEconomy.put(economy ,playerNames.get(i));
        }

        TreeMap<Double, String> sortedMap = new TreeMap<Double,String>(playerAndEconomy);

        String first = sortedMap.firstEntry().getValue();
        Double firstOther = sortedMap.firstEntry().getKey();
        System.out.println("Min Economy Player : Economy");
        System.out.println(first + " : "+ firstOther);
    }
}
