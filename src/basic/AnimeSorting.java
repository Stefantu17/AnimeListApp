package basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class AnimeSorting {

    public static void mergeSort(ArrayList<AnimeData> list, int comparatorType) {
        if (list == null || list.size() <= 1) {
            return; // Base case: already sorted
        }

        int mid = list.size() / 2;
        ArrayList<AnimeData> left = new ArrayList<>(list.subList(0, mid));
        ArrayList<AnimeData> right = new ArrayList<>(list.subList(mid, list.size()));

        // Recursively sort the two halves
        mergeSort(left, comparatorType);
        mergeSort(right, comparatorType);

        // Merge the sorted halves
        merge(list, left, right, comparatorType);
    }

    

    private static void merge(ArrayList<AnimeData> list, ArrayList<AnimeData> left, ArrayList<AnimeData> right, int comparatorType) {
        int leftIndex = 0;
        int rightIndex = 0;
        int listIndex = 0;

        // Compare first data types of objects from left and right lists
        while (leftIndex < left.size() && rightIndex < right.size()) {
            AnimeData leftAnime = left.get(leftIndex);
            AnimeData rightAnime = right.get(rightIndex);

            switch (comparatorType){
                case 0:
                    if (leftAnime.titleCompareTo(rightAnime) <= 0) {
                        list.set(listIndex, leftAnime);
                        leftIndex++;
                    } 
                    
                    else {
                        list.set(listIndex, rightAnime);
                        rightIndex++;
                    }

                    break;

                case 1:
                    if (leftAnime.scoreCompareTo(rightAnime) > 0) {
                            list.set(listIndex, leftAnime);
                            leftIndex++;
                        } 
                    
                    else {
                        list.set(listIndex, rightAnime);
                        rightIndex++;
                    }

                    break;
                
                case 2:
                    if (leftAnime.popularityCompareTo(rightAnime) <= 0) {
                            list.set(listIndex, leftAnime);
                            leftIndex++;
                        } 
                    
                    else {
                        list.set(listIndex, rightAnime);
                        rightIndex++;
                    }

                    break;

                case 3:
                    if (leftAnime.membersCompareTo(rightAnime) > 0) {
                            list.set(listIndex, leftAnime);
                            leftIndex++;
                        } 
                    
                    else {
                        list.set(listIndex, rightAnime);
                        rightIndex++;
                    }

                    break;

                case 4:
                    if (leftAnime.episodesCompareTo(rightAnime) <= 0) {
                            list.set(listIndex, leftAnime);
                            leftIndex++;
                        } 
                    
                    else {
                        list.set(listIndex, rightAnime);
                        rightIndex++;
                    }

                    break;

                case 5:
                    if (leftAnime.rankCompareTo(rightAnime) <= 0) {
                            list.set(listIndex, leftAnime);
                            leftIndex++;
                        } 
                    
                    else {
                        list.set(listIndex, rightAnime);
                        rightIndex++;
                    }

                    break;
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
}
