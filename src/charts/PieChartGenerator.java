package charts;

import java.util.ArrayList;
import java.util.List;

import CPT.AnimeData;
import javafx.collections.FXCollections;
import javafx.scene.chart.PieChart;

public class PieChartGenerator {

    private PieChart genrePieChart;
    
    public PieChartGenerator() {

        genrePieChart = new PieChart();
        genrePieChart.setTitle("Genre Distribution");
  
    }

    public PieChart getPieChart() {
        return genrePieChart;
    }
    
    public void updateGenrePieChart(List<AnimeData> userAnimeList) {

        List<PieChart.Data> genreData = new ArrayList<>();

        for (AnimeData anime : userAnimeList) {
            for (String genre : anime.getGenres()) {
                boolean genreExists = false;
                for (PieChart.Data data : genreData) {
                    if (data.getName().equalsIgnoreCase(genre)) {
                        data.setPieValue(data.getPieValue() + 1);
                        genreExists = true;
                        break;
                    }
                }
                if (!genreExists) {
                    genreData.add(new PieChart.Data(genre, 1));
                }
            }
        }
        genrePieChart.setData(FXCollections.observableArrayList(genreData));
    }
}