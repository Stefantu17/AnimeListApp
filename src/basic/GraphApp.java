package basic;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class GraphApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Graph App");

        // Define the x and y axes
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();

        // Create the line chart
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Graph");
        lineChart.setCreateSymbols(false); // Disable data point symbols

        // Create a data series
        XYChart.Series<Number, Number> dataSeries = new XYChart.Series<>();
        dataSeries.setName("Data Series");

        // Add data points to the series
        dataSeries.getData().add(new XYChart.Data<>(1, 5));
        dataSeries.getData().add(new XYChart.Data<>(2, 3));
        dataSeries.getData().add(new XYChart.Data<>(3, 8));
        dataSeries.getData().add(new XYChart.Data<>(4, 6));
        dataSeries.getData().add(new XYChart.Data<>(5, 1));

        // Add the data series to the line chart
        lineChart.getData().add(dataSeries);

        // Create the scene and add the line chart
        Scene scene = new Scene(lineChart, 400, 300);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}