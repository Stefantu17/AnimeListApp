package basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.chart.XYChart;

public class ReadData {
    
    private ArrayList<AnimeData> data;

    public ReadData() {
        this.data = new ArrayList<>();
    }

    public void processData() {
        AnimeDataSet data = new AnimeDataSet();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/basic/animes.csv"))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                String newGenre = row[3].replace("[", "");
                newGenre = newGenre.replace("]", "");
                newGenre = newGenre.replace("'", "");
                String[] newGenreList = newGenre.split(", ");
                ArrayList<String> genres = new ArrayList<>();
                genres = (ArrayList<String>) Arrays.asList(newGenreList);
                AnimeData animeData = new AnimeData(row[1], row[2], genres, row[4], Integer.parseInt(row[5]), Integer.parseInt(row[6]), Integer.parseInt(row[7]), Integer.parseInt(row[8]), Integer.parseInt(row[9]));
                data.addAnimeData(animeData);
            }
        } 
        
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
