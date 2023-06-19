package basic;
import java.util.ArrayList;

public class AnimeData {
    
    private int UID;
    private String title;
    private String synopsis;
    private ArrayList<String> genres;
    private String aired;
    private double episodes;
    private double members; 
    private double popularity;
    private double rank;
    private double score;
    private String imageLink;
    private String animeLink;

    public AnimeData(int UID, String title, String synopsis, ArrayList<String> genres, String aired, double episodes, double members, double popularity, double rank, double score, String imageLink, String animeLink) {
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

    public double getEpisodes() {
        return episodes;
    }

    public void setEpisodes(double episodes) {
        this.episodes = episodes;
    }

    public double getMembers() {
        return members;
    }

    public void setMembers(double members) {
        this.members = members;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public double getRank() {
        return rank;
    }

    public void setRank(double rank) {
        this.rank = rank;
    }

    public double getScore() {
        return score;
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

    public double popularityCompareTo(AnimeData animeTwo) {
        return this.getPopularity() - animeTwo.getPopularity();
    }

    public double membersCompareTo(AnimeData animeTwo) {
        return this.getMembers() - animeTwo.getMembers();
    }

    public double episodesCompareTo(AnimeData animeTwo) {
        return this.getEpisodes() - animeTwo.getEpisodes();
    }

    public double rankCompareTo(AnimeData animeTwo) {
        return this.getRank() - animeTwo.getRank();
    }

}
