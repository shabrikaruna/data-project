package com.mountblue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class YearAndWins {

    public static void main(String[] args) {

        String matchesPath = "/Users/shabrikaruna/Desktop/01_sabarinathan_data_project/ipl/matches.csv";
        BufferedReader matchReader = null;

        HashMap<String,Integer> yearAndWins = new HashMap<>();

        try {
            String line = "";
            String delimiter = ","; // Delimiter for each row

            matchReader = new BufferedReader(new FileReader(matchesPath));
            matchReader.readLine();

            // Read's the lines unitl it is null
            while ((line = matchReader.readLine()) != null) {

                String[] matchesString = line.split(delimiter);
                final int SEASON_INDEX = 1; // To access index of matchesString[1] - we get column season

                // To check if key is already present and increment by 1 to the existing key
                if(yearAndWins.containsKey(matchesString[SEASON_INDEX])){
                    yearAndWins.put( matchesString[SEASON_INDEX] , yearAndWins.get(matchesString[SEASON_INDEX]) + 1 );
                }else{ // else create a new key and append the value to 1
                    yearAndWins.put( matchesString[SEASON_INDEX] , 1);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (matchReader != null) {
                try {
                    matchReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // To print the key and values of yearAndWins
        yearAndWins.forEach(( k, v) -> System.out.println(k +" : "+ v));
    }
}
