package basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AnimeDataSet {

    private static ArrayList<AnimeData> data;

    public AnimeDataSet() {
        this.data = new ArrayList<>();
    }

    public void addAnimeData(AnimeData animeData) {
        data.add(animeData);
    }

}
