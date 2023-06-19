package charts;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;

public class BarChartGenerator {
    
    private BarChart<String, Number> barChart;

    public BarChartGenerator() {

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();


        barChart = new BarChart<>(xAxis, yAxis);
        barChart.setCategoryGap(50);
        barChart.setTitle("Average Score Distribution for your Animes");
    }
}
