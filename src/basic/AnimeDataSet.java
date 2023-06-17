package basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class AnimeDataSet {

    private ArrayList<AnimeData> data;

    public AnimeDataSet() {
        this.data = new ArrayList<>();
    }

     public static void processData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/basic/animes.csv"))) {
            String line = reader.readLine();
            for (int i = 0; i < 2; i++) {
                line = reader.readLine();
                int UID = Integer.parseInt(line.substring(0, line.indexOf(",")));
                line = line.substring(line.indexOf(",") + 1);
                String title = line.substring(0, line.indexOf(","));
                line = line.substring(line.indexOf(",") + 2);
                String synopsis = "";
                while (line.charAt(0) != '[') {
                    synopsis += line;
                    line = reader.readLine();
                }
                line = line.substring(line.indexOf(",") + 2);
                String strGenres = line.substring(0, line.indexOf("]"));
                line = line.substring(line.indexOf("]") + 4);
                String newGenre = strGenres;
                newGenre = newGenre.replace("[", "");
                newGenre = newGenre.replace("'", "");
                String[] newGenreList = newGenre.split(", ");
                ArrayList<String> genres = new ArrayList<>(Arrays.asList(newGenreList));
                String aired = line.substring(0, line.indexOf(","));
                line = line.substring(line.indexOf(",") + 1);
                line = line.substring(line.indexOf(",") + 1);
                line = line.substring(line.indexOf(",") + 1);
                double episodes = Double.parseDouble(line.substring(0, line.indexOf(",")));
                line = line.substring(line.indexOf(",") + 1);
                int members = Integer.parseInt(line.substring(0, line.indexOf(",")));
                line = line.substring(line.indexOf(",") + 1);
                int popularity = Integer.parseInt(line.substring(0, line.indexOf(",")));
                line = line.substring(line.indexOf(",") + 1);
                double rank = Double.parseDouble(line.substring(0, line.indexOf(",")));
                line = line.substring(line.indexOf(",") + 1);
                double score = Double.parseDouble(line.substring(0, line.indexOf(",")));
                line = line.substring(line.indexOf(",") + 1);
                String imageLink = line.substring(0, line.indexOf(","));
                line = line.substring(line.indexOf(",") + 1);
                String animeLink = line.substring(0, line.indexOf(","));
                line = line.substring(line.indexOf(",") + 1);
                AnimeData animeData = new AnimeData(UID, title, synopsis, genres, aired, episodes, members, popularity, rank, score, imageLink, animeLink);
                System.out.println(animeData.getTitle());
                System.out.println(animeData.getSynopsis());
                System.out.println(animeData.getGenreString());
                System.out.println(animeData.getAired());
                System.out.println(animeData.getEpisodes());
                System.out.println(animeData.getPopularity());
                System.out.println(animeData.getMembers());
                System.out.println(animeData.getRank());
                System.out.println(animeData.getScore());
            }
        } 
         
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addAnimeData(AnimeData animeData) {
        data.add(animeData);
    }

    public static void mergeSort(ArrayList<Object> list, Comparator<Object> comparator) {
        if (list == null || list.size() <= 1) {
            return; // Base case: already sorted
        }

        int mid = list.size() / 2;
        ArrayList<Object> left = new ArrayList<>(list.subList(0, mid));
        ArrayList<Object> right = new ArrayList<>(list.subList(mid, list.size()));

        // Recursively sort the two halves
        mergeSort(left, comparator);
        mergeSort(right, comparator);

        // Merge the sorted halves
        merge(list, left, right, comparator);
    }

    private static void merge(ArrayList<Object> list, ArrayList<Object> left, ArrayList<Object> right, Comparator<Object> comparator) {
        int leftIndex = 0;
        int rightIndex = 0;
        int listIndex = 0;

        // Compare first data types of objects from left and right lists
        while (leftIndex < left.size() && rightIndex < right.size()) {
            Object leftObject = left.get(leftIndex);
            Object rightObject = right.get(rightIndex);
            if (comparator.compare(leftObject, rightObject) <= 0) {
                list.set(listIndex, leftObject);
                leftIndex++;
            } else {
                list.set(listIndex, rightObject);
                rightIndex++;
            }
            listIndex++;
        }

        // Copy remaining objects from left list
        while (leftIndex < left.size()) {
            list.set(listIndex, left.get(leftIndex));
            leftIndex++;
            listIndex++;
        }

        // Copy remaining objects from right list
        while (rightIndex < right.size()) {
            list.set(listIndex, right.get(rightIndex));
            rightIndex++;
            listIndex++;
        }
    }

    public static int binarySearch(ArrayList<Integer> list, int target) {
        int left = 0;
        int right = list.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (list.get(mid) == target) {
                return mid; // Found the target element
            } else if (list.get(mid) < target) {
                left = mid + 1; // Target is in the right half
            } else {
                right = mid - 1; // Target is in the left half
            }
        }
        
        return -1;
    }

    public void add(AnimeData animeData) {
    }

    

}
