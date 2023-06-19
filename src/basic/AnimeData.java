package basic;

import java.util.ArrayList;

public class AnimeData {
    
    private int UID;
    private String title;
    private String synopsis;
    private ArrayList<String> genres;
    private String aired;
    private int episodes;
    private int members; 
    private int popularity;
    private int rank;
    private double score;
    private String imageLink;
    private String animeLink;

    public AnimeData(int UID, String title, String synopsis, ArrayList<String> genres, String aired, int episodes, int members, int popularity, int rank, double score, String imageLink, String animeLink) {
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

    public void setUID(int UID) {
        this.UID = UID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public ArrayList<String> getGenres() {
        return genres;
    } 

    public void setGenre(ArrayList<String> genres) {
        this.genres = genres;
    } 

    public String getGenresString() {
        String str = "";
        for (int i = 0; i < genres.size(); i++) {
            if (i == genres.size() - 1) {
                str += genres.get(i);
            }
            else {
                str += genres.get(i) + ", ";
            }
        }
        return str;
    }

    public String getAired() {
        return aired;
    }

    public void setAired(String aired) {
        this.aired = aired;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public int getMembers() {
        return members;
    }

    public void setMembers(int members) {
        this.members = members;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public double getScore() {
        return Math.round(score * 100.0) / 100.0;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getAnimeLink() {
        return animeLink;
    }

    public void setAnimeLink(String animeLink) {
        this.animeLink = animeLink;
    }

    public int titleCompareTo(AnimeData animeTwo) {
        return this.getTitle().compareTo(animeTwo.getTitle());
    }

    public double scoreCompareTo(AnimeData animeTwo) {
        return this.getScore() - animeTwo.getScore();
    }

    public int popularityCompareTo(AnimeData animeTwo) {
        return this.getPopularity() - animeTwo.getPopularity();
    }

    public int membersCompareTo(AnimeData animeTwo) {
        return this.getMembers() - animeTwo.getMembers();
    }

    public int episodesCompareTo(AnimeData animeTwo) {
        return this.getEpisodes() - animeTwo.getEpisodes();
    }

    public int rankCompareTo(AnimeData animeTwo) {
        return this.getRank() - animeTwo.getRank();
    }

}
