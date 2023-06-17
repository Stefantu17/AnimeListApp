package basic;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnimeListApp extends Application {

    private ListView<AnimeData> animeListView;
    private ListView<AnimeData> userAnimeListView;
    private List<AnimeData> animeList;
    private List<AnimeData> userAnimeList;
    private PieChart genrePieChart;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Anime List App");

        ReadData.processData();
        ReadData.getData();

        ArrayList<AnimeData> animeList = new ArrayList<AnimeData>();

        for (int i = 0; i < ReadData.getData().size(); i++) {
            animeList.add((ReadData.getData()).get(i));
        }


        userAnimeList = new ArrayList<>();

        animeListView = new ListView<>();
        animeListView.setItems(FXCollections.observableArrayList(animeList));
        animeListView.setCellFactory(param -> new AnimeListCell());
        animeListView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showAnimeDetails(newValue)
        );

        userAnimeListView = new ListView<>();
        userAnimeListView.setItems(FXCollections.observableArrayList(userAnimeList));
        userAnimeListView.setCellFactory(param -> new AnimeListCell());
        userAnimeListView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showAnimeDetails(newValue)
        );

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addAnimeToUserList());

        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Tab animeListTab = new Tab("Anime List");
        animeListTab.setContent(animeListView);

        Tab userAnimeListTab = new Tab("My Anime List");
        userAnimeListTab.setContent(userAnimeListView);

        Tab genreTab = new Tab("Genre Distribution");
        genrePieChart = new PieChart();
        genrePieChart.setTitle("Genre Distribution");
        genreTab.setContent(genrePieChart);

        tabPane.getTabs().addAll(animeListTab, userAnimeListTab, genreTab);

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(tabPane, addButton);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vbox);

        Scene scene = new Scene(borderPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAnimeDetails(AnimeData anime) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Anime Details");
        alert.setHeaderText(anime.getTitle());

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(5);
        gridPane.setPadding(new Insets(10));

        gridPane.addRow(0, new Label("Genres:"), new Label(anime.getGenresAsString()));
        gridPane.addRow(1, new Label("Date Aired:"), new Label(anime.getDateAired()));
        gridPane.addRow(2, new Label("Episode Count:"), new Label(String.valueOf(anime.getEpisodes())));
        gridPane.addRow(3, new Label("Popularity Rank:"), new Label(String.valueOf(anime.getPopularity())));
        gridPane.addRow(4, new Label("People Watching:"), new Label(String.valueOf(anime.getPeopleWatching())));
        gridPane.addRow(5, new Label("Rank:"), new Label(String.valueOf(anime.getRank())));
        gridPane.addRow(6, new Label("Average Score:"), new Label(String.valueOf(anime.getAverageScore())));

        TextArea summaryTextArea = new TextArea(anime.getSynopsis());
        summaryTextArea.setEditable(false);
        summaryTextArea.setWrapText(true);
        summaryTextArea.setMaxWidth(Double.MAX_VALUE);
        summaryTextArea.setMaxHeight(Double.MAX_VALUE);
        gridPane.addRow(7, new Label("Summary:"), summaryTextArea);

        alert.getDialogPane().setContent(gridPane);
        alert.showAndWait();
    }

    private void addAnimeToUserList() {
        Anime selectedAnime = animeListView.getSelectionModel().getSelectedItem();
        if (selectedAnime != null && !userAnimeList.contains(selectedAnime)) {
            userAnimeList.add(selectedAnime);
            updateGenrePieChart();
        }
    }

    private void updateGenrePieChart() {
        List<PieChart.Data> genreData = new ArrayList<>();

        for (Anime anime : userAnimeList) {
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

    private class AnimeListCell extends ListCell<Anime> {
        @Override
        protected void updateItem(Anime anime, boolean empty) {
            super.updateItem(anime, empty);
            if (empty || anime == null) {
                setText(null);
            } else {
                setText(anime.getName());
            }
        }
    }
}
