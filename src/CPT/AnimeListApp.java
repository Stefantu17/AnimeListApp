package CPT;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import charts.BarChartGenerator;
import charts.PieChartGenerator;

/**
 * A class that runs the AnimeList program
 * @author: S. Tuczynski & G. Lui
 * 
 */
public class AnimeListApp extends Application {

    // Instance variables
    private TableView<AnimeData> mainTable;
    private TableView<AnimeData> userTable;
    private List<AnimeData> userAnimeList;

    /**
     * Main method
     * 
     * @param args  String[] args
     * 
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    /**
     * Method that runs the program
     * 
     * @param primaryStage  primary stage
     * 
     */
    public void start(Stage primaryStage) {

        // Set title
        primaryStage.setTitle("AnimeList");


        // Processes data from CSV file, File I/O
        ArrayList<AnimeData> animeList = new ArrayList<>(); 
        try (BufferedReader reader = new BufferedReader(new FileReader("src/CPT/animes.csv"))) {

            String line = reader.readLine();

            // Runs while there are still entries
            while((line = reader.readLine()) != null) {

                ArrayList<String> genres = new ArrayList<>();
                AnimeData animeData = new AnimeData(0, "", "", genres, "", 0, 0, 0, 999999, 0, "", "");
                animeData.setUID(Integer.parseInt(line.substring(0, line.indexOf(","))));
     
                line = line.substring(line.indexOf(",") + 1);

                if (line.charAt(0) == '"' && line.charAt(1) != '"') {
                    line = line.substring(1);
                    animeData.setTitle(line.substring(0, line.indexOf('"')));
                    line = line.substring(line.indexOf('"') + 2); 
                }
                else {
                    animeData.setTitle(line.substring(0, line.indexOf(','))); 
                    line = line.substring(line.indexOf(',') + 1); 
                }

                animeData.setTitle(animeData.getTitle().replace("\"", ""));
       
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
                        animeData.setSynopsis(animeData.getSynopsis() + line);
                        line = reader.readLine();
     
                    }
                    
                    if (line.contains("['") == true) {
                        animeData.setSynopsis(animeData.getSynopsis() + line.substring(0, line.indexOf("['")));
                        line = line.substring(line.indexOf("['"));
                    }
                    else {
                        animeData.setSynopsis(animeData.getSynopsis() + line.substring(0, line.indexOf("[")));
                        line = line.substring(line.indexOf("["));
                    }
  
                }
      
                while (line.length() < 90 && line.contains("https") != true) {
                    line = reader.readLine();
                }
           
                if (animeData.getSynopsis() != "") {
                    if (animeData.getSynopsis().charAt(animeData.getSynopsis().length()-1) == '"') {
          
                        animeData.setSynopsis(animeData.getSynopsis().substring(0, animeData.getSynopsis().length()-2));
                    }
                    else if (animeData.getSynopsis().charAt(animeData.getSynopsis().length()-1) == ',') {
     
                        animeData.setSynopsis(animeData.getSynopsis().substring(0, animeData.getSynopsis().length()-1));
                    }
                }   
                String strGenres = line.substring(0, line.indexOf("]"));
                
                line = line.substring(line.indexOf("]") + 1);
                if (line.charAt(0) == '"') {
                    line = line.substring(2);


                    if (line.charAt(0) == '"') {
                        line = line.substring(1);

                    }
                }
                else {
                    line = line.substring(1);
   
                    if (line.charAt(0) == '"') {
                        line = line.substring(1);
 
                    }
                }
          
                strGenres= strGenres.replace("[", "");
                strGenres = strGenres.replace("'", "");
                String[] genreList = strGenres.split(", ");
    
                animeData.setGenre(new ArrayList<>(Arrays.asList(genreList)));


                if (line.contains("Not available") == true) {
                    animeData.setAired("Not available");
                    line = line.substring(line.indexOf(',') + 1);
                }
                else if (line.charAt(0) == '1' || line.charAt(0) == '2') {

                    if (line.contains(", ") == true) {
                        animeData.setAired(line.substring(0, line.indexOf('"') + 2));
                        line = line.substring(line.indexOf('"') + 2);
                    }

                    else {
                        animeData.setAired(line.substring(0, line.indexOf(',') + 1));
                        line = line.substring(line.indexOf(',') + 1);
                    }

                }
                else {
                    animeData.setAired(line.substring(0, line.indexOf('"')));
                    line = line.substring(line.indexOf('"') + 2);
                }
    
                if (animeData.getAired() != "") {

                    if (animeData.getAired().charAt(animeData.getAired().length()-1) == ',') {
                        animeData.setAired(animeData.getAired().substring(0, animeData.getAired().indexOf(',')));
                    }
                }
                
                if (line.charAt(0) == ',') {
                    line = line.substring(line.indexOf(",") + 1);
                }

                else {
                    animeData.setEpisodes((int) Double.parseDouble(line.substring(0, line.indexOf(","))));
                    line = line.substring(line.indexOf(",") + 1);
                }
       
           
                if (line.charAt(0) == ',') {

                    line = line.substring(line.indexOf(",") + 1);
                }

                else {

                    animeData.setMembers((int) Double.parseDouble(line.substring(0, line.indexOf(","))));
                    line = line.substring(line.indexOf(",") + 1);
                }

       
                if (line.charAt(0) == ',') {

                    line = line.substring(line.indexOf(",") + 1);
                }
                else {
                    
                    animeData.setPopularity((int) Double.parseDouble(line.substring(0, line.indexOf(","))));
                    line = line.substring(line.indexOf(",") + 1);
                }

     
                if (line.charAt(0) == ',') {

                    line = line.substring(line.indexOf(",") + 1);
                }

                else {

                    animeData.setRank((int) Double.parseDouble(line.substring(0, line.indexOf(","))));
                    line = line.substring(line.indexOf(",") + 1);
                }
           
    
                if (line.charAt(0) == ',') {
                    line = line.substring(line.indexOf(",") + 1);
                }

                else {

                    animeData.setScore(Double.parseDouble(line.substring(0, line.indexOf(","))));
                    line = line.substring(line.indexOf(",") + 1);
                }
  

                if (line.charAt(0) == ',') {

                    line = line.substring(line.indexOf(",") + 1);
                }

                else {

                    animeData.setImageLink(line.substring(0, line.indexOf(",")));
                    line = line.substring(line.indexOf(",") + 1);
                }
   

                if (line.charAt(0) != ',') {
                    animeData.setAnimeLink(line);
                }

                animeList.add(animeData);

            }
            
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

        mainTable = new TableView<AnimeData>();
        userTable = new TableView<AnimeData>();

        userAnimeList = new ArrayList<>();
        ObservableList<AnimeData> observableUserAnimeList = FXCollections.observableArrayList();

        mainTable.setEditable(true);
        mainTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        TableColumn<AnimeData, String>  animeTitle = new TableColumn<AnimeData, String>("Name");
        animeTitle.setCellValueFactory(new PropertyValueFactory<AnimeData, String>("title"));

        TableColumn<AnimeData, String> animeScore = new TableColumn<AnimeData, String>("Score");
        animeScore.setCellValueFactory(new PropertyValueFactory<AnimeData, String>("score"));

        TableColumn<AnimeData, String> animePopularity = new TableColumn<AnimeData, String>("Popularity");
        animePopularity.setCellValueFactory(new PropertyValueFactory<AnimeData, String>("popularity"));

        TableColumn<AnimeData, String> animeRank = new TableColumn<AnimeData, String>("Rank");
        animeRank.setCellValueFactory(new PropertyValueFactory<AnimeData, String>("rank"));

        TableColumn<AnimeData, String> animeViews = new TableColumn<AnimeData, String>("Views");
        animeViews.setCellValueFactory(new PropertyValueFactory<AnimeData, String>("members"));

        TableColumn<AnimeData, String> animeEpisodes = new TableColumn<AnimeData, String>("Episodes");
        animeEpisodes.setCellValueFactory(new PropertyValueFactory<AnimeData, String>("episodes"));

        mainTable.setItems(FXCollections.observableArrayList(animeList));
        mainTable.getColumns().addAll(animeTitle, animeScore, animePopularity, animeRank, animeViews, animeEpisodes);
        
        userTable.setEditable(true);
        userTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        TableColumn<AnimeData, String> userAnimeTitle = new TableColumn<AnimeData, String>("Name");
        userAnimeTitle.setCellValueFactory(new PropertyValueFactory<AnimeData, String>("title"));

        TableColumn<AnimeData, String> userAnimeScore = new TableColumn<AnimeData, String>("Score");
        userAnimeScore.setCellValueFactory(new PropertyValueFactory<AnimeData, String>("score"));

        TableColumn<AnimeData, String> userAnimePopularity = new TableColumn<AnimeData, String>("Popularity");
        userAnimePopularity.setCellValueFactory(new PropertyValueFactory<AnimeData, String>("popularity"));

        TableColumn<AnimeData, String> userAnimeRank = new TableColumn<AnimeData, String>("Rank");
        userAnimeRank.setCellValueFactory(new PropertyValueFactory<AnimeData, String>("rank"));

        TableColumn<AnimeData, String> userAnimeViews = new TableColumn<AnimeData, String>("Views");
        userAnimeViews.setCellValueFactory(new PropertyValueFactory<AnimeData, String>("members"));

        TableColumn<AnimeData, String> userAnimeEpisodes = new TableColumn<AnimeData, String>("Episodes");
        userAnimeEpisodes.setCellValueFactory(new PropertyValueFactory<AnimeData, String>("episodes"));

        userTable.getColumns().addAll(userAnimeTitle, userAnimeScore, userAnimePopularity, userAnimeRank, userAnimeViews, userAnimeEpisodes);

        mainTable.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2) {
                        AnimeData selectedAnime = (AnimeData) mainTable.getSelectionModel().getSelectedItem(); 
                        if (selectedAnime != null) {
                            showAnimeDetails(selectedAnime);
                        }   
                    }
                });

        userTable.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2) {
                        AnimeData selectedAnime = (AnimeData) mainTable.getSelectionModel().getSelectedItem(); 
                        if (selectedAnime != null) {
                            showAnimeDetails(selectedAnime);
                        }   
                    }
                });
        
        BarChartGenerator barChart = new BarChartGenerator();
        PieChartGenerator pieChart = new PieChartGenerator();
            
        Text title = new Text(10, 50, "AnimeList.net");
        title.setFont(new Font(20));

        Text averageScore = new Text(10, 50, "Average score: " + barChart.getScoreAverage());
        averageScore.setFont(new Font(20));

        Text standardDeviationScore = new Text(10, 50, "Standard Deviation Score: " + barChart.getStandardDeviation());
        standardDeviationScore.setFont(new Font(20));

        Text animeCount = new Text(10, 50, "Anime Count: " + barChart.getAnimeCount());
        animeCount.setFont(new Font(20));

        Text maxScore = new Text(10, 50, "Maximum Score: " + barChart.getScoreMax());
        maxScore.setFont(new Font(20));

        Text minScore = new Text(10, 50, "Minimum Score: " + barChart.getScoreMin());
        minScore.setFont(new Font(20));

        Text medianScore = new Text(10, 50, "Score Median: " + barChart.getScoreMedian());
        medianScore.setFont(new Font(20));

        Button addButton = new Button("Watched");
        addButton.setOnAction(e -> addAnimeToUserList(observableUserAnimeList, barChart, pieChart, averageScore, standardDeviationScore, animeCount, maxScore, minScore, medianScore));

        Button removeButton = new Button("Remove");
        removeButton.setOnAction(e -> removeAnimeFromUserList(observableUserAnimeList, barChart, pieChart, averageScore, standardDeviationScore, animeCount, maxScore, minScore, medianScore));

        CheckBox nsfwFilterCheckBox = new CheckBox("NSFW Filter");
        nsfwFilterCheckBox.setOnAction(event -> updateAnimeListView(nsfwFilterCheckBox, animeList));

        ChoiceBox sortingChoiceBox = new ChoiceBox(FXCollections.observableArrayList("Name", "Score", "Popularity", "Rank", "Views", "Episodes"));
        sortingChoiceBox.setValue("Name");
        animeSorting(animeList, sortingChoiceBox);
        sortingChoiceBox.setOnAction(e -> animeSorting(animeList, sortingChoiceBox));

        TextField searchField = new TextField();
        searchField.setPromptText("Search for Anime");
        searchField.setOnAction(e -> animeSearch(animeList, searchField));

       
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        HBox hboxAnimeSearch = new HBox(10);
        hboxAnimeSearch.setAlignment(Pos.TOP_CENTER);
        hboxAnimeSearch.setPadding(new Insets(10));
        hboxAnimeSearch.setSpacing(600);
        hboxAnimeSearch.getChildren().addAll(searchField, sortingChoiceBox);

        VBox vboxAnimeList = new VBox(10);
        vboxAnimeList.getChildren().add(hboxAnimeSearch);
        vboxAnimeList.getChildren().add(mainTable);
        vboxAnimeList.getChildren().addAll(tabPane, addButton, nsfwFilterCheckBox);
        vboxAnimeList.setAlignment(Pos.CENTER);
        vboxAnimeList.setPadding(new Insets(10));

        VBox vboxUserAnimeList = new VBox(10);
        vboxUserAnimeList.getChildren().add(userTable);
        vboxUserAnimeList.getChildren().add(removeButton);
        vboxUserAnimeList.getChildren().add(tabPane);
        vboxUserAnimeList.setAlignment(Pos.CENTER);
        vboxUserAnimeList.setPadding(new Insets(10));

        HBox hboxUserAnimeData1 = new HBox(10);
        hboxUserAnimeData1.getChildren().addAll(averageScore, standardDeviationScore, animeCount);
        hboxUserAnimeData1.setAlignment(Pos.CENTER);
        hboxUserAnimeData1.setPadding(new Insets(10));

        HBox hboxUserAnimeData2 = new HBox(10);
        hboxUserAnimeData2.getChildren().addAll(maxScore, minScore, medianScore);
        hboxUserAnimeData2.setAlignment(Pos.CENTER);
        hboxUserAnimeData2.setPadding(new Insets(10));

        HBox hboxTitle = new HBox(10);
        hboxTitle.getChildren().add(title);
        hboxTitle.setAlignment(Pos.CENTER);
        hboxTitle.setPadding(new Insets(10));

        VBox vboxBarGraphTab = new VBox(10);
        vboxBarGraphTab.getChildren().add(barChart.getBarChart());
        vboxBarGraphTab.getChildren().add(hboxUserAnimeData1);
        vboxBarGraphTab.getChildren().add(hboxUserAnimeData2);
        vboxBarGraphTab.setAlignment(Pos.CENTER);
        vboxBarGraphTab.setPadding(new Insets(10));

        Tab animeListTab = new Tab("Anime List");
        animeListTab.setContent(vboxAnimeList);

        Tab userAnimeListTab = new Tab("My Anime List");
        userAnimeListTab.setContent(vboxUserAnimeList);

        Tab genreTab = new Tab("Genre Distribution");
        genreTab.setContent(pieChart.getPieChart());

        Tab scoreChartTab = new Tab("Score Chart");
        scoreChartTab.setContent(vboxBarGraphTab);

        tabPane.getTabs().addAll(animeListTab, userAnimeListTab, genreTab, scoreChartTab);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(hboxTitle);
        borderPane.setCenter(tabPane);

        Scene scene = new Scene(borderPane, 850, 600);
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

        gridPane.addRow(0, new Label("UID:"), new Label(String.valueOf(anime.getUID())));
        gridPane.addRow(1, new Label("Genre(s):"), new Label(anime.getGenresString()));
        gridPane.addRow(2, new Label("Date Aired:"), new Label(anime.getAired()));
        gridPane.addRow(3, new Label("Episode Count:"), new Label(String.valueOf(anime.getEpisodes())));
        gridPane.addRow(4, new Label("Popularity Rank:"), new Label(String.valueOf(anime.getPopularity())));
        gridPane.addRow(5, new Label("People Watching:"), new Label(String.valueOf(anime.getMembers())));
        gridPane.addRow(6, new Label("Rank:"), new Label(String.valueOf(anime.getRank())));
        gridPane.addRow(7, new Label("Average Score:"), new Label(String.valueOf(anime.getScore())));

        Hyperlink hyperLink = new Hyperlink(anime.getAnimeLink());

        hyperLink.setOnAction(e -> {
            getHostServices().showDocument(anime.getAnimeLink());
        });

        TextArea summaryTextArea = new TextArea(anime.getSynopsis());
        summaryTextArea.setEditable(false);
        summaryTextArea.setWrapText(true);
        summaryTextArea.setMaxWidth(Double.MAX_VALUE);
        summaryTextArea.setMaxHeight(Double.MAX_VALUE);
        gridPane.addRow(8, new Label("Summary:"), summaryTextArea);

        if (anime.getImageLink() != ""  && !anime.getGenres().contains("Hentai") && !anime.getGenres().contains("Harem") && !anime.getGenres().contains("Ecchi")) {
            Group root = new Group();
            Image image = new Image(anime.getImageLink());
            ImageView imageView = new ImageView(image);
            root.getChildren().add(imageView);
            HBox animeInfo = new HBox(); 
            animeInfo.getChildren().add(gridPane);
            animeInfo.getChildren().add(root);
            animeInfo.setAlignment(Pos.CENTER);
            animeInfo.setPadding(new Insets(10));
            VBox animePage = new VBox();
            animePage.getChildren().add(animeInfo);
            animePage.getChildren().add(hyperLink);
            animePage.setAlignment(Pos.CENTER);
            animeInfo.setPadding(new Insets(10));
            alert.getDialogPane().setContent(animePage);
            alert.showAndWait();
        }

        else {

            alert.getDialogPane().setContent(gridPane);
            alert.showAndWait();

        }

    }

    private void addAnimeToUserList(ObservableList<AnimeData> observableUserAnimeList, BarChartGenerator barChart, PieChartGenerator pieChart, Text averageScore, Text standardDeviationScore, Text animeCount, Text maxScore, Text minScore, Text medianScore) {

        AnimeData selectedAnime = (AnimeData) mainTable.getSelectionModel().getSelectedItem();

        if (selectedAnime != null && !userAnimeList.contains(selectedAnime)) {
            userAnimeList.add(selectedAnime);
            observableUserAnimeList.add(selectedAnime);
            userTable.setItems(FXCollections.observableArrayList(userAnimeList));
            pieChart.updateGenrePieChart(userAnimeList);
            barChart.addToBarChart(selectedAnime);

            averageScore.setText("Average score: " + barChart.getScoreAverage());
            standardDeviationScore.setText("Standard Deviation: " + barChart.getStandardDeviation());
            animeCount.setText("Anime Count: " + barChart.getAnimeCount());
            maxScore.setText("Maximum Score: " + barChart.getScoreMax());
            minScore.setText("Minimum Score: " + barChart.getScoreMin());
            medianScore.setText("Median Score: " + barChart.getScoreMedian());
            
        }
    }

    private void removeAnimeFromUserList(ObservableList<AnimeData> observableUserAnimeList, BarChartGenerator barChart, PieChartGenerator pieChart, Text averageScore, Text standardDeviationScore, Text animeCount, Text maxScore, Text minScore, Text medianScore) {

        AnimeData selectedAnime = (AnimeData) userTable.getSelectionModel().getSelectedItem();

        if (selectedAnime != null && userAnimeList.contains(selectedAnime)) {
            userAnimeList.remove(selectedAnime);
            observableUserAnimeList.remove(selectedAnime);
            userTable.setItems(FXCollections.observableArrayList(userAnimeList));
            pieChart.updateGenrePieChart(userAnimeList);
            barChart.removeFromBarChart(selectedAnime);

            averageScore.setText("Average score: " + barChart.getScoreAverage());
            standardDeviationScore.setText("Standard Deviation: " + barChart.getStandardDeviation());
            animeCount.setText("Anime Count: " + barChart.getAnimeCount());
            maxScore.setText("Maximum Score: " + barChart.getScoreMax());
            minScore.setText("Minimum Score: " + barChart.getScoreMin());
            medianScore.setText("Median Score: " + barChart.getScoreMedian());
        }

    }

    private void animeSearch(ArrayList<AnimeData> animeList, TextField searchField) {
        String searchText = searchField.getText().toLowerCase();
        ArrayList<AnimeData> searchResults = new ArrayList<>();
        AnimeSorting.linearSearch(animeList, searchField, searchText, searchResults);
        ObservableList<AnimeData> observableAnimeList = FXCollections.observableArrayList(searchResults);
        mainTable.setItems(observableAnimeList);
    }

    
    private void animeSorting(ArrayList<AnimeData> animeList, ChoiceBox sortingChoiceBox) {
        int selectedIndex = sortingChoiceBox.getSelectionModel().getSelectedIndex();
        AnimeSorting.mergeSort(animeList, selectedIndex);
        mainTable.setItems(FXCollections.observableArrayList(animeList));
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
            mainTable.setItems(filteredAnimeList);
        } 
        else {
            mainTable.setItems(FXCollections.observableArrayList(animeList));
        }
        
    }

}