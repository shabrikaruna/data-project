import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class TeamsAndWinsPerYear{

    public static void main(String[] args) {

        String matchesPath = "/Users/shabrikaruna/Desktop/01_sabarinathan_data_project/ipl/matches.csv";
        BufferedReader matchReader = null;

        HashMap<String,Integer> teamsAndWinsYearly = new HashMap<>();
        ArrayList<Integer> yearSeason = new ArrayList<Integer>();

        try {
            String line = "";
            String delimiter = ","; // Delimiter for each row

            matchReader = new BufferedReader(new FileReader(matchesPath));
            matchReader.readLine();


            while ((line = matchReader.readLine()) != null) {
                String[] yearString = line.split(delimiter);
                yearSeason.add(Integer.parseInt(yearString[1]));

            }
            line = "";
            delimiter = ","; // Delimiter for each row

            matchReader = new BufferedReader(new FileReader(matchesPath));
            matchReader.readLine();

            TreeSet<Integer> uniqueYears = new TreeSet<Integer>(yearSeason);


            // Read's the lines unitl it is null

                for(int unique : uniqueYears) {
                    while ((line = matchReader.readLine()) != null) {
                        String[] matchesString = line.split(delimiter);
                        final int SEASON_INDEX = 1; // To access index of matchesString[1] - we get column season
                        final int WINNER_INDEX = 10; // To access index of matchesString[10] - we get column winner

                // To check if key is already present and increment by 1 to the existing key

                        if (Integer.parseInt(matchesString[SEASON_INDEX]) == unique) {
                            if (teamsAndWinsYearly.containsKey(matchesString[WINNER_INDEX])) {
                                teamsAndWinsYearly.put(matchesString[WINNER_INDEX], teamsAndWinsYearly.get(matchesString[WINNER_INDEX]) + 1);
                            } else { // else create a new key and append the value to 1
                                teamsAndWinsYearly.put(matchesString[WINNER_INDEX], 1);
                            }
                        }
                    }
                    System.out.println("YEAR : "+unique);
                    System.out.println();
                    teamsAndWinsYearly.forEach(( k , v) -> System.out.println(k +" : "+ v));
                    System.out.println();
                    teamsAndWinsYearly.clear();
                    line = "";
                    delimiter = ","; // Delimiter for each row
                    matchReader = new BufferedReader(new FileReader(matchesPath));
                    matchReader.readLine();
                }
            }
        
        catch (FileNotFoundException e) {
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
    }
}


