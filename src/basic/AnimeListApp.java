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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import charts.BarChartGenerator;
import charts.PieChartGenerator;

public class AnimeListApp extends Application {

    private ListView<AnimeData> animeListView;
    private ListView<AnimeData> userAnimeListView;
    private List<AnimeData> userAnimeList;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Anime List App");

        ArrayList<AnimeData> animeList = new ArrayList<>(); 

        try (BufferedReader reader = new BufferedReader(new FileReader("src/basic/animes - copy.csv"))) {
            String line = reader.readLine();
            for (int i = 0; i < 40000; i++) {
                line = reader.readLine();

                int UID = Integer.parseInt(line.substring(0, line.indexOf(","))); // good
                
                System.out.println(line);
                System.out.println(1);
                
                line = line.substring(line.indexOf(",") + 1); // good


                System.out.println(line);
                System.out.println(2);
                String title = "";

                if (line.charAt(0) == '"') {
                    line = line.substring(1);
                    title = line.substring(0, line.indexOf('"')); // good
                    line = line.substring(line.indexOf('"') + 2); // good
                    System.out.println(line);
                }
                else {
                    title = line.substring(0, line.indexOf(',')); // good
                    line = line.substring(line.indexOf(',') + 1); // good
                    System.out.println(line);
                }

                System.out.println(line);
                System.out.println(3);

                String synopsis = "";


                if (line.contains("https") == true) {
                    if (line.contains("['") == true) {
                        line = line.substring(line.indexOf("['"));
                    }
                    else {
                        line = line.substring(line.indexOf("["));
                    }
                } 

                else {
                    while (line.contains("https") == false) {
                        synopsis += line;
                        line = reader.readLine();
     
                    }
        
                    line = line.substring(line.indexOf('"') + 3);
  
                }

                while (line.length() < 90) {
                    line = reader.readLine();
                }

                System.out.println(line);
                System.out.println(77);

                String strGenres = line.substring(0, line.indexOf("]"));
                
                line = line.substring(line.indexOf("]") + 1);
                if (line.charAt(0) == '"') {
                    line = line.substring(2);

                    System.out.println(line);
                    if (line.charAt(0) == '"') {
                        line = line.substring(1);
                        System.out.println(line);
                    }
                }
                else {
                    line = line.substring(1);
                    System.out.println(line);
                    if (line.charAt(0) == '"') {
                        line = line.substring(1);
                        System.out.println(line);
                    }
                }

                System.out.println(line);
                System.out.println(5);

                String newGenres = strGenres;
                newGenres = newGenres.replace("[", "");
                newGenres = newGenres.replace("'", "");
                String[] newGenreList = newGenres.split(", ");

                System.out.println(line);
                System.out.println(6);


                ArrayList<String> genres = new ArrayList<>(Arrays.asList(newGenreList));
                String aired = "";
                if (line.contains("Not available") == true) {
                    aired = "Not available";
                    line = line.substring(line.indexOf(',') + 1);
                }
                else if (line.charAt(0) == '1' || line.charAt(0) == '2') {
                    if (line.contains(", ") == true) {
                        aired = line.substring(0, line.indexOf('"') + 2);
                        line = line.substring(line.indexOf('"') + 2);
                    }
                    else {
                    aired = line.substring(0, line.indexOf(',') + 1);
                    line = line.substring(line.indexOf(',') + 1);
                    }
                }
                else {
                    aired = line.substring(0, line.indexOf('"'));
                    line = line.substring(line.indexOf('"') + 2);
                }

                double episodes = 0;
                if (line.charAt(0) == ',') {
                    line = line.substring(line.indexOf(",") + 1);
                }
                else {
                    episodes = Double.parseDouble(line.substring(0, line.indexOf(",")));
                    line = line.substring(line.indexOf(",") + 1);
                }
       
                double members = 0;
                if (line.charAt(0) == ',') {

                    line = line.substring(line.indexOf(",") + 1);
                }
                else {
                    members = Double.parseDouble(line.substring(0, line.indexOf(",")));
                    line = line.substring(line.indexOf(",") + 1);
                }

                double popularity = 0;
                if (line.charAt(0) == ',') {

                    line = line.substring(line.indexOf(",") + 1);
                }
                else {
                    popularity = Double.parseDouble(line.substring(0, line.indexOf(",")));
                    line = line.substring(line.indexOf(",") + 1);
                }

                double rank = 0;
                if (line.charAt(0) == ',') {

                    line = line.substring(line.indexOf(",") + 1);
                }
                else {
                    rank = Double.parseDouble(line.substring(0, line.indexOf(",")));
                    line = line.substring(line.indexOf(",") + 1);
                }
  
                double score = 0;
                if (line.charAt(0) == ',') {
                    line = line.substring(line.indexOf(",") + 1);
                }
                else {
                    score = Double.parseDouble(line.substring(0, line.indexOf(",")));
                    line = line.substring(line.indexOf(",") + 1);
                }
                System.out.println(line);
                System.out.println(6);
                String imageLink = "";
                if (line.charAt(0) == ',') {
                    line = line.substring(line.indexOf(",") + 1);
                }
                else {
                    imageLink = line.substring(0, line.indexOf(","));
                    line = line.substring(line.indexOf(",") + 1);
                }
                System.out.println(line);
                System.out.println(6);
                String animeLink = "";
                if (line.charAt(0) != ',') {
                    animeLink = line;
                }
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
        animeListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                AnimeData selectedAnime = animeListView.getSelectionModel().getSelectedItem();
                if (selectedAnime != null) {
                    showAnimeDetails(selectedAnime);
                }
            }
        });

        userAnimeListView = new ListView<>();
        userAnimeListView.setItems(FXCollections.observableArrayList(userAnimeList));
        userAnimeListView.setCellFactory(param -> new AnimeListCell());
        userAnimeListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                AnimeData selectedAnime = animeListView.getSelectionModel().getSelectedItem();
                if (selectedAnime != null) {
                    showAnimeDetails(selectedAnime);
                }
            }
        });

        
        BarChartGenerator barChart = new BarChartGenerator();
        PieChartGenerator pieChart = new PieChartGenerator();

        Button addButton = new Button("Watched");
        addButton.setOnAction(e -> addAnimeToUserList(observableUserAnimeList, barChart, pieChart));
        
        Button removeButton = new Button("Remove");
        removeButton.setOnAction(e -> removeAnimeFromUserList(observableUserAnimeList, barChart, pieChart));

        CheckBox nsfwFilterCheckBox = new CheckBox("NSFW Filter");
        nsfwFilterCheckBox.setOnAction(event -> updateAnimeListView(nsfwFilterCheckBox, animeList));

        TextField genreSearchField = new TextField();

        ChoiceBox sortingChoiceBox = new ChoiceBox(FXCollections.observableArrayList("Title", "Score", "Popularity", "Members", "Episodes", "Rank"));
        sortingChoiceBox.setOnAction(e -> animeSorting(animeList, sortingChoiceBox));
        
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        HBox hboxAnimeSearch = new HBox(10);
        hboxAnimeSearch.setAlignment(Pos.TOP_LEFT);
        hboxAnimeSearch.setPadding(new Insets(10));
        hboxAnimeSearch.setSpacing(500);
        hboxAnimeSearch.getChildren().addAll(genreSearchField, sortingChoiceBox);

        VBox vboxAnimeList = new VBox(10);
        vboxAnimeList.getChildren().add(hboxAnimeSearch);
        vboxAnimeList.getChildren().add(animeListView);
        vboxAnimeList.getChildren().addAll(tabPane, addButton, nsfwFilterCheckBox);
        vboxAnimeList.setAlignment(Pos.CENTER);
        vboxAnimeList.setPadding(new Insets(10));

        VBox vboxUserAnimeList = new VBox(10);
        vboxUserAnimeList.getChildren().add(userAnimeListView);
        vboxUserAnimeList.getChildren().add(removeButton);
        vboxUserAnimeList.getChildren().add(tabPane);
        vboxUserAnimeList.setAlignment(Pos.CENTER);
        vboxUserAnimeList.setPadding(new Insets(10));

        Tab animeListTab = new Tab("Anime List");
        animeListTab.setContent(vboxAnimeList);

        Tab userAnimeListTab = new Tab("My Anime List");
        userAnimeListTab.setContent(vboxUserAnimeList);

        Tab genreTab = new Tab("Genre Distribution");
        genreTab.setContent(pieChart.getPieChart());

        Tab scoreChartTab = new Tab("Score Chart");
        scoreChartTab.setContent(barChart.getBarChart());

        tabPane.getTabs().addAll(animeListTab, userAnimeListTab, genreTab, scoreChartTab);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(tabPane);

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

    private void addAnimeToUserList(ObservableList<AnimeData> observableUserAnimeList, BarChartGenerator barChart, PieChartGenerator pieChart) {

        AnimeData selectedAnime = animeListView.getSelectionModel().getSelectedItem();
        if (selectedAnime != null && !userAnimeList.contains(selectedAnime)) {
            userAnimeList.add(selectedAnime);
            observableUserAnimeList.add(selectedAnime);
            userAnimeListView.setItems(observableUserAnimeList);
            pieChart.updateGenrePieChart(userAnimeList);
            barChart.updateBarChart(selectedAnime);
        }

    }

    private void removeAnimeFromUserList(ObservableList<AnimeData> observableUserAnimeList, BarChartGenerator barChart, PieChartGenerator pieChart) {

        AnimeData selectedAnime = userAnimeListView.getSelectionModel().getSelectedItem();

        if (selectedAnime != null && userAnimeList.contains(selectedAnime)) {
            userAnimeList.remove(selectedAnime);
            observableUserAnimeList.remove(selectedAnime);
            userAnimeListView.setItems(observableUserAnimeList);
            pieChart.updateGenrePieChart(userAnimeList);
            barChart.updateBarChart(selectedAnime);
        }

    }

    private void animeSorting(ArrayList<AnimeData> animeList, ChoiceBox sortingChoiceBox) {
            int selectedIndex = sortingChoiceBox.getSelectionModel().getSelectedIndex();
            AnimeDataSet.mergeSort(animeList, selectedIndex);
            animeListView.setItems(FXCollections.observableArrayList(animeList));
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
        if (nsfwFilterEnabled) {
            animeListView.setItems(filteredAnimeList);
        } 
        else {
            animeListView.setItems(FXCollections.observableArrayList(animeList));
        }
        
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