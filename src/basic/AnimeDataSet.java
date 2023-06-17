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

    

}
