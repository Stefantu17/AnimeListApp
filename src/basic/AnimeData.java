package basic;

import java.util.ArrayList;

public class AnimeData {
    
    private String title;
    private String synopsis;
    private ArrayList<String> genre;
    private String aired;
    private int episodes;
    private int members; 
    private int popularity;
    private int rank;
    private double score;

    public AnimeData(String title, String synopsis, ArrayList<String> genre, String aired, int episodes, int members, int popularity, int rank, double score) {
        this.title = title;
        this.synopsis = synopsis;
        this.genre = genre;
        this.aired = aired;
        this.episodes = episodes;
        this.members = members;
        this.popularity = popularity;
        this.rank = rank;
        this.score = score;
    }

    public String getTitle() {
        return title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public ArrayList<String> getGenre() {
        return genre;
    } 

    public String getAired() {
        return aired;
    }

    public int getEpisodes() {
        return episodes;
    }

    public int getMembers() {
        return members;
    }

    public int getPopularity() {
        return popularity;
    }

    public int getRank() {
        return rank;
    }

    public double getScore() {
        return score;
    }
}
