package com.mountblue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

class RunsConcededIn2016 {

    public static void main(String[] args){

        String csvFile = "/Users/shabrikaruna/Desktop/01_sabarinathan_data_project/ipl/deliveries.csv";
        BufferedReader deliveriesReader = null;
        String line = "";
        String delimiter = ",";
        HashMap<String, Integer> teamNameAndExtras = new HashMap<>();

        try {

            deliveriesReader = new BufferedReader(new FileReader(csvFile));
            deliveriesReader.readLine();
            while ((line = deliveriesReader.readLine()) != null) {

                final int ID_INDEX = 0;
                final int BOWLING_TEAM_INDEX = 3;
                final int EXTRAS_INDEX = 16;

                // use comma as separator
                String[] lines = line.split(delimiter);
                if(Integer.parseInt(lines[ID_INDEX]) >= 577){
                    if(teamNameAndExtras.containsKey(lines[BOWLING_TEAM_INDEX])){
                        teamNameAndExtras.put( lines[BOWLING_TEAM_INDEX] , teamNameAndExtras.get(lines[BOWLING_TEAM_INDEX])+Integer.parseInt(lines[EXTRAS_INDEX]) );
                    }else{
                        teamNameAndExtras.put( lines[BOWLING_TEAM_INDEX] , Integer.parseInt(lines[EXTRAS_INDEX]));
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
        System.out.println("TEAM NAME : EXTRAS");
        System.out.println();
        teamNameAndExtras.forEach((k, v) -> System.out.println(k +" : "+ v));
    }
}
