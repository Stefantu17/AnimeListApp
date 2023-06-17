package basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.chart.XYChart;

public class ReadData {
    
    private static ArrayList<AnimeData> data;

    public ReadData() {
        data = new ArrayList<>();
    }

    public static void processData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/basic/animes.csv"))) {
            String line = reader.readLine();
            for (int i = 0; i < 1; i++) {
                line = reader.readLine();
                int UID = Integer.parseInt(line.substring(0, line.indexOf(",")));
                line = line.substring(line.indexOf(",") + 1);
                String title = line.substring(0, line.indexOf(","));
                line = line.substring(line.indexOf(",") + 1);
                String synopsis = "";
                while (line.charAt(0) != '[') {
                    synopsis += line;
                    line = reader.readLine();
                }
                line = line.substring(line.indexOf(",") + 1);
                String strGenres = line.substring(0, line.indexOf(","));
                line = line.substring(line.indexOf(",") + 1);
                System.out.println(newGenre);
                String newGenre = strGenres.replace("[", "");
                newGenre = newGenre.replace("]", "");
                newGenre = newGenre.replace("'", "");
                String[] newGenreList = newGenre.split(", ");
                System.out.println(line);
                ArrayList<String> genres = new ArrayList<>(Arrays.asList(newGenreList));
                String aired = line.substring(0, line.indexOf(","));
                line = line.substring(line.indexOf(",") + 1);
                int episodes = Integer.parseInt(line.substring(0, line.indexOf(",")));
                line = line.substring(line.indexOf(",") + 1);
                int members = Integer.parseInt(line.substring(0, line.indexOf(",")));
                line = line.substring(line.indexOf(",") + 1);
                int popularity = Integer.parseInt(line.substring(0, line.indexOf(",")));
                line = line.substring(line.indexOf(",") + 1);
                System.out.println(line);
                int rank = Integer.parseInt(line.substring(0, line.indexOf(",")));
                line = line.substring(line.indexOf(",") + 1);
                double score = Double.parseDouble(line.substring(0, line.indexOf(",")));
                line = line.substring(line.indexOf(",") + 1);
                String imageLink = line.substring(0, line.indexOf(","));
                line = line.substring(line.indexOf(",") + 1);
                String animeLink = line.substring(0, line.indexOf(","));
                line = line.substring(line.indexOf(",") + 1);
                AnimeData animeData = new AnimeData(UID, title, synopsis, genres, aired, episodes, members, popularity, rank, score, imageLink, animeLink);
                data.add(animeData);
    
            }
        } 
         
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<AnimeData> getData() {
        return data;
    }
}
