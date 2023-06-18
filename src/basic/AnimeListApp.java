package basic;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnimeListApp extends Application {

    private ListView<AnimeData> animeListView;
    private ListView<AnimeData> userAnimeListView;
    private List<AnimeData> userAnimeList;
    private PieChart genrePieChart;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Anime List App");

        ArrayList<AnimeData> animeList = new ArrayList<>(); 
        try (BufferedReader reader = new BufferedReader(new FileReader("src/basic/animesShort.csv"))) {
            String line = reader.readLine();
            for (int i = 0; i < 900; i++) {
                line = reader.readLine();
                int UID = Integer.parseInt(line.substring(0, line.indexOf(",")));
                line = line.substring(line.indexOf(",") + 1);
                String title = line.substring(0, line.indexOf(","));
                line = line.substring(line.indexOf(",") + 1);
                String synopsis = "";
                if (line.contains("[") == true) {
                    line = line.substring(line.indexOf("["));
                } 
                else {
                    while (line.charAt(0) != '[' && line.charAt(0) != '(') {
                        synopsis += line;
                        line = reader.readLine();
                        if (line.contains("[") == true) {
                            break;
                        }
                    }
                    line = line.substring(line.indexOf(",") + 2);
                }
                while (line.length() < 20) {
                    line = reader.readLine();
                }
                String strGenres = line.substring(0, line.indexOf("]"));
                line = line.substring(line.indexOf("]") + 4);
                String newGenres = strGenres;
                newGenres = newGenres.replace("[", "");
                newGenres = newGenres.replace("'", "");
                String[] newGenreList = newGenres.split(", ");
                ArrayList<String> genres = new ArrayList<>(Arrays.asList(newGenreList));
                String aired = line.substring(0, line.indexOf(","));
                line = line.substring(line.indexOf('"') + 2);
                double episodes = Double.parseDouble(line.substring(0, line.indexOf(",")));
                line = line.substring(line.indexOf(",") + 1);
                double members = Double.parseDouble(line.substring(0, line.indexOf(",")));
                line = line.substring(line.indexOf(",") + 1);
                double popularity = Double.parseDouble(line.substring(0, line.indexOf(",")));
                line = line.substring(line.indexOf(",") + 1);
                double rank = Double.parseDouble(line.substring(0, line.indexOf(",")));
                line = line.substring(line.indexOf(",") + 1);
                double score = Double.parseDouble(line.substring(0, line.indexOf(",")));
                
                line = line.substring(line.indexOf(",") + 1);
                String imageLink = line.substring(0, line.indexOf(","));
                line = line.substring(line.indexOf(",") + 1);
                String animeLink = line;
                line = line.substring(line.indexOf(",") + 1);
                AnimeData animeData = new AnimeData(UID, title, synopsis, genres, aired, episodes, members, popularity, rank, score, imageLink, animeLink);
                animeList.add(animeData);
            }
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

        userAnimeList = new ArrayList<>();
        ObservableList<AnimeData> observableUserAnimeList = FXCollections.observableArrayList();

        animeListView = new ListView<>();
        animeListView.setItems(FXCollections.observableArrayList(animeList));
        animeListView.setCellFactory(param -> new AnimeListCell());
        animeListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showAnimeDetails(newValue));

        userAnimeListView = new ListView<>();
        userAnimeListView.setItems(FXCollections.observableArrayList(userAnimeList));
        userAnimeListView.setCellFactory(param -> new AnimeListCell());
        userAnimeListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showAnimeDetails(newValue));

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addAnimeToUserList(observableUserAnimeList));

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

        CheckBox nsfwFilterCheckBox = new CheckBox("NSFW Filter");
        nsfwFilterCheckBox.setOnAction(event -> updateAnimeListView(nsfwFilterCheckBox, animeList));

        tabPane.getTabs().addAll(animeListTab, userAnimeListTab, genreTab);

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(tabPane, addButton);
        vbox.getChildren().add(nsfwFilterCheckBox);

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

        gridPane.addRow(0, new Label("Genres:"), new Label(anime.getGenresString()));
        gridPane.addRow(1, new Label("Date Aired:"), new Label(anime.getAired()));
        gridPane.addRow(2, new Label("Episode Count:"), new Label(String.valueOf(anime.getEpisodes())));
        gridPane.addRow(3, new Label("Popularity Rank:"), new Label(String.valueOf(anime.getPopularity())));
        gridPane.addRow(4, new Label("People Watching:"), new Label(String.valueOf(anime.getMembers())));
        gridPane.addRow(5, new Label("Rank:"), new Label(String.valueOf(anime.getRank())));
        gridPane.addRow(6, new Label("Average Score:"), new Label(String.valueOf(anime.getScore())));

        TextArea summaryTextArea = new TextArea(anime.getSynopsis());
        summaryTextArea.setEditable(false);
        summaryTextArea.setWrapText(true);
        summaryTextArea.setMaxWidth(Double.MAX_VALUE);
        summaryTextArea.setMaxHeight(Double.MAX_VALUE);
        gridPane.addRow(7, new Label("Summary:"), summaryTextArea);

        alert.getDialogPane().setContent(gridPane);
        alert.showAndWait();
    }

    private void addAnimeToUserList(ObservableList<AnimeData> observableUserAnimeList) {
        AnimeData selectedAnime = animeListView.getSelectionModel().getSelectedItem();
        

        if (selectedAnime != null && !userAnimeList.contains(selectedAnime)) {
            userAnimeList.add(selectedAnime);
            observableUserAnimeList.add(selectedAnime);
            userAnimeListView.setItems(observableUserAnimeList);
            updateGenrePieChart();
        }

    }



    private void updateGenrePieChart() {
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

    private void updateAnimeListView(CheckBox nsfwFilterCheckBox, ArrayList<AnimeData> animeList) {

        ObservableList<AnimeData> filteredAnimeList = FXCollections.observableArrayList();

        boolean nsfwFilterEnabled = nsfwFilterCheckBox.isSelected();

        for (AnimeData anime : animeList) {
            if (nsfwFilterEnabled) {
                boolean isNsfw = anime.getGenres().contains("Hentai") || anime.getGenres().contains("Ecchi") || anime.getGenres().contains("Harem");
                if (!isNsfw) {
                    filteredAnimeList.add(anime);
                }
            } 
            else {
                filteredAnimeList.add(anime);
            }
        }
        animeListView.setItems(filteredAnimeList);
    }

    private class AnimeListCell extends ListCell<AnimeData> {
        @Override
        protected void updateItem(AnimeData anime, boolean empty) {
            super.updateItem(anime, empty);
            if (empty || anime == null) {
                setText(null);
            } else {
                setText(anime.getTitle());
            }
        }
    }
}