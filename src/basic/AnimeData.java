package basic;

import java.util.ArrayList;

public class AnimeData {
    
    private int UID;
    private String title;
    private String synopsis;
    private ArrayList<String> genres;
    private String aired;
    private double episodes;
    private int members; 
    private int popularity;
    private double rank;
    private double score;
    private String imageLink;
    private String animeLink;

    public AnimeData(int UID, String title, String synopsis, ArrayList<String> genres, String aired, double episodes, int members, int popularity, double rank, double score, String imageLink, String animeLink) {
        this.UID = UID;
        this.title = title;
        this.synopsis = synopsis;
        this.genres = genres;
        this.aired = aired;
        this.episodes = episodes;
        this.members = members;
        this.popularity = popularity;
        this.rank = rank;
        this.score = score;
        this.imageLink = imageLink;
        this.animeLink = animeLink;
    }

    public int getUID() {
        return UID;
    }
    public String getTitle() {
        return title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public ArrayList<String> getGenre() {
        return genres;
    } 

    public String getGenreString() {
        String str = "";
        for (int i = 0; i < genres.size(); i++) {
            str += genres.get(i) + ", ";
        }
        return str;

    }

    public String getAired() {
        return aired;
    }

    public double getEpisodes() {
        return episodes;
    }

    public int getMembers() {
        return members;
    }

    public int getPopularity() {
        return popularity;
    }

    public double getRank() {
        return rank;
    }

    public double getScore() {
        return score;
    }

    public String getImageLink() {
        return imageLink;
    }

    public String getAnimeLink() {
        return animeLink;
    }
}
