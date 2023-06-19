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
    private XYChart.Series<Number, Number> one;
    private XYChart.Series<Number, Number> two;
    private XYChart.Series<Number, Number> three;
    private XYChart.Series<Number, Number> four;
    private XYChart.Series<Number, Number> five;
    private XYChart.Series<Number, Number> six;
    private XYChart.Series<Number, Number> seven;
    private XYChart.Series<Number, Number> eight;
    private XYChart.Series<Number, Number> nine;
    private XYChart.Series<Number, Number> ten;

    public BarChartGenerator() {

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        barChart = new BarChart<>(xAxis, yAxis);
        barChart.setCategoryGap(50);
        barChart.setTitle("Average Score Distribution for your Animes");
        //xAxis.setCategories(FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"));
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("1", 0));
        series.getData().add(new XYChart.Data<>("2", 0));
        series.getData().add(new XYChart.Data<>("3", 0));
        series.getData().add(new XYChart.Data<>("4", 0));
        series.getData().add(new XYChart.Data<>("5", 0));
        series.getData().add(new XYChart.Data<>("6", 0));
        series.getData().add(new XYChart.Data<>("7", 0));
        series.getData().add(new XYChart.Data<>("8", 0));
        series.getData().add(new XYChart.Data<>("9", 0));
        series.getData().add(new XYChart.Data<>("10", 0));

    }

    public BarChart<String, Number> getBarChart() {

        return barChart;

    }

    public void updateBarChart(AnimeData userSelectedAnime) {

        int roundedScore = (int) Math.round(userSelectedAnime.getScore());
        XYChart.Series<String, Number> series = barChart.getData().get(0);
        XYChart.Data<String, Number> data = series.getData().get(roundedScore - 1);
        data.setYValue(data.getYValue().intValue() + 1);

    }
}
