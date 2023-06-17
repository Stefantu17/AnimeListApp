package basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.chart.XYChart;

public class ReadData {
    
    public static void processData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/basic/animes.csv"))) {
            String line = reader.readLine();
            for (int i = 0; i < 2; i++) {
                line = reader.readLine();
                int UID = Integer.parseInt(line.substring(0, line.indexOf(",")));
                line = line.substring(line.indexOf(",") + 1);
                String title = line.substring(0, line.indexOf(","));
                line = line.substring(line.indexOf(",") + 2);
                String synopsis = "";
                while (line.charAt(0) != '[') {
                    synopsis += line;
                    line = reader.readLine();
                }
                line = line.substring(line.indexOf(",") + 2);
                String strGenres = line.substring(0, line.indexOf("]"));
                line = line.substring(line.indexOf("]") + 4);
                String newGenre = strGenres;
                newGenre = newGenre.replace("[", "");
                newGenre = newGenre.replace("'", "");
                String[] newGenreList = newGenre.split(", ");
                ArrayList<String> genres = new ArrayList<>(Arrays.asList(newGenreList));
                String aired = line.substring(0, line.indexOf(","));
                line = line.substring(line.indexOf(",") + 1);
                line = line.substring(line.indexOf(",") + 1);
                line = line.substring(line.indexOf(",") + 1);
                double episodes = Double.parseDouble(line.substring(0, line.indexOf(",")));
                line = line.substring(line.indexOf(",") + 1);
                int members = Integer.parseInt(line.substring(0, line.indexOf(",")));
                line = line.substring(line.indexOf(",") + 1);
                int popularity = Integer.parseInt(line.substring(0, line.indexOf(",")));
                line = line.substring(line.indexOf(",") + 1);
                double rank = Double.parseDouble(line.substring(0, line.indexOf(",")));
                line = line.substring(line.indexOf(",") + 1);
                double score = Double.parseDouble(line.substring(0, line.indexOf(",")));
                line = line.substring(line.indexOf(",") + 1);
                String imageLink = line.substring(0, line.indexOf(","));
                line = line.substring(line.indexOf(",") + 1);
                String animeLink = line.substring(0, line.indexOf(","));
                line = line.substring(line.indexOf(",") + 1);
                AnimeData animeData = new AnimeData(UID, title, synopsis, genres, aired, episodes, members, popularity, rank, score, imageLink, animeLink);
                System.out.println(animeData.getTitle());
                System.out.println(animeData.getSynopsis());
                System.out.println(animeData.getGenreString());
                System.out.println(animeData.getAired());
                System.out.println(animeData.getEpisodes());
                System.out.println(animeData.getPopularity());
                System.out.println(animeData.getMembers());
                System.out.println(animeData.getRank());
                System.out.println(animeData.getScore());
            }
        } 
         
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
