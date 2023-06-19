package charts;

import java.util.ArrayList;
import java.util.List;

import basic.AnimeData;
import javafx.collections.FXCollections;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class BarChartGenerator {
    
    private BarChart<String, Number> barChart;
    private ArrayList<Double> scores = new ArrayList<Double>();

    public BarChartGenerator() {

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        barChart = new BarChart<>(xAxis, yAxis);
        barChart.setCategoryGap(50);
        barChart.setTitle("Average Score Distribution for your Animes");
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Values");
        series.getData().add(new XYChart.Data<>("0", 0));
        series.getData().add(new XYChart.Data<>("1", 0));
        series.getData().add(new XYChart.Data<>("2", 0));
        series.getData().add(new XYChart.Data<>("3", 0));
        series.getData().add(new XYChart.Data<>("4", 0));
        series.getData().add(new XYChart.Data<>("5", 0));
        series.getData().add(new XYChart.Data<>("6", 0));
        series.getData().add(new XYChart.Data<>("7", 0));
        series.getData().add(new XYChart.Data<>("8", 0));
        series.getData().add(new XYChart.Data<>("9", 0));
        barChart.getData().add(series);

    }

    public BarChart<String, Number> getBarChart() {

        return barChart;

    }

    public void addToBarChart(AnimeData userSelectedAnime) {

        int roundedScore = (int) Math.round(userSelectedAnime.getScore());

        XYChart.Series<String, Number> series = barChart.getData().get(0);
        for (XYChart.Data<String, Number> data : series.getData()) {
            if (data.getXValue().equals(Integer.toString(roundedScore))) {
                scores.add(userSelectedAnime.getScore());
                int newValue = data.getYValue().intValue() + 1;
                data.setYValue(newValue);
                break;
            }
        }
        
    }

    public void removeFromBarChart(AnimeData userSelectedAnime) {

        int roundedScore = (int) Math.round(userSelectedAnime.getScore());

        XYChart.Series<String, Number> series = barChart.getData().get(0);
        for (XYChart.Data<String, Number> data : series.getData()) {
            if (data.getXValue().equals(Integer.toString(roundedScore))) {
                scores.remove(userSelectedAnime.getScore());
                int newValue = data.getYValue().intValue() - 1;
                data.setYValue(newValue);
                break;
            }
        }
        
    }

    public double getScoreAverage() {
        double average = 0;
        if (scores.isEmpty() == true) {
            return 0;
        }
        for (double num : scores) {
            average += num;
        }
        return Math.round(average/scores.size() * 100.0) / 100.0;
    }

    public double getStandardDeviation() {
        double sum = 0.0;
        if (scores.isEmpty() == true) {
            return 0;
        }
        for (double i : scores) {
            sum += i;
        }


        int length = scores.size();
        double mean = sum / length;

        double standardDeviation = 0.0;
        for (double num : scores) {
            standardDeviation += Math.pow(num - mean, 2);
        }

        return Math.round(Math.sqrt(standardDeviation / length) * 100.0) / 100.0;
    }

    public int getAnimeCount() {

        int count = 0;

        if (scores.isEmpty() == true) {
            return 0;
        }
        for (double i : scores) {
            count += 1;
        }
        return count;
    }
}
