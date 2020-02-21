package com.company;


import java.util.*;

public class SortAlgos {

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; ++i) {
            int minIndex =i;
            for (int j = i; j < arr.length; ++j) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            swap(arr, minIndex, i);
        }
    }

    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; ++i) {
            int j = i;
            while (j > 0 && arr[j-1] > arr[j]) {
                swap(arr, j -1, j);
                j--;
            }
        }
    }

    public static void bubbleSort(int[] arr) {
        boolean swapped = true;
        int i = -1;
        while (swapped) {
            i++;
            swapped = false;
            for (int j = 0; j < arr.length - i - 1; ++j) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    swapped = true;
                }
            }
        }
    }

    //merge two arrays:
    // middle = (left + right) / 2
    //O(n)
    private static void merge(int[] arr, int[] tmp, int start, int middle, int end) {
        int i = start, j = middle + 1;
        int index = start;

        while ( i <= middle && j <= end) {
            if (arr[i] < arr[j]) {
                tmp[index++] = arr[i++];
            } else {
                tmp[index++] = arr[j++];
            }
        }

        while (i <= middle){
            tmp[index++] = arr[i++];
        }
        while (j <= end){
            tmp[index++] = arr[j++];
        }

        for (index = start; index <= end; ++index) {
            arr[index] = tmp[index];
        }
    }

    // merge2:
    private static void merge2(int[] arr, int[] tmp, int left, int middle, int right) {
        int i = left, j = middle + 1;
        for (int k = 0; k < right - left + 1; ++k) {
            if(i <= middle && (j > right || arr[i] < arr[j])) {
                tmp[k] = arr[i++];
            }else{
                tmp[k] = arr[j++];
            }
        }
        for (int k = 0; k < right-left + 1; ++k){
            arr[left + k] = tmp[k];
        }
    }

    // merge sort:
    public static void mergeSort(int[] arr) {
        int[] tmp = new int[arr.length];
        mergeSortHelper(arr, tmp, 0, arr.length - 1);

    }
    //recursion:
    private static void mergeSortHelper(int[] arr, int[] tmp, int start, int end) {
        if(start >= end){   ///base case -- end case
            return;
        }
//        int middle = (right + left)/2;   //// overflow
        int middle =  start + (end - start) / 2;
        mergeSortHelper(arr, tmp, start, middle);
        mergeSortHelper(arr, tmp, middle + 1, end);
        merge(arr, tmp, start, middle, end);
    }



    public static void printArray(int[] arr){
        for (int num : arr){
            System.out.print(num +" ");
        }
        System.out.println();
    }

    public static void quickSort(int[] arr) {
        quickSortHelper(arr, 0, arr.length - 1);
    }

    // four (<=)   --- tip
    //partition
    public static void quickSortHelper(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int i = start, j = end;
        int pivot = arr[start + (end - start) / 2];
        while (i <= j) {
            while (i <= j && arr[i] < pivot) {  ///cannot be "<="  //StackOverflowError
                i++;
            }
            while (i <= j && arr[j] > pivot) {
                j--;
            }
            if (i <= j) {
                swap(arr, i++, j--);
            }
        }
        quickSortHelper(arr, start, j);
        quickSortHelper(arr, i, end);
    }


    public static void main(String[] args) {
        int[] arr ={6,4,5,7,2,4,3,4,7,8};
        SortAlgos.mergeSort(arr);
//        SortAlgos.quickSort(arr);
        printArray(arr);
//        mergeSort(arr);
//        SortAlgos.printArray(arr);

//        SortAlgos s1 = new SortAlgos();
//        s1.selectionSort(arr);
//        s1.printArray(arr);

        testSort();

        javaSortExample();





    }

    public static void javaSortExample() {
        Student[] students = new Student[5];
        students[0] = new Student("Jack", 100);
        students[1] = new Student("Tom", 80);
        students[2] = new Student("Pony", 90);
        students[3] = new Student("Andy", 98);
        students[4] = new Student("Jony", 56);

        Arrays.sort(students, new StudentNameComparator());
//        Arrays.sort(students, new StudentScoreComparator());

//        for (Student student: students){
//            System.out.println(student.name + ":"+ student.score);
//        }

        List<Student> studentList = Arrays.asList(students);
        Collections.sort(studentList, new StudentNameComparator());
        System.out.println(studentList);

    }

    //test:
    public static void testSort() {
        int[] arr = {6,4,5,7,2,4,3,4,7,8};
        //不重不漏：
        Map<Integer, Integer> origin = testMap(arr);
        SortAlgos.quickSort(arr);
        Map<Integer,Integer> after = testMap(arr);
        if (!origin.equals(after)) {
            System.err.println("error");
            return;
        }
        //non-descending:
        for (int i =0; i<arr.length-1;++i) {
            if (arr[i] > arr[i+1]){
                System.err.println("not sorted");
                return;
            }
        }
        System.out.println("success");

    }
    
    private static Map<Integer, Integer> testMap(int[] arr) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : arr){
            if(count.containsKey(num)) {
                count.put(num, count.get(num)+1);
            }else{
                count.put(num,1);
            }
        }
        return count;
    }

}
class Student{
    public String name;
    public int score;
    public Student(String name, int score){
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return name + ":" + score;
    }
}
class StudentNameComparator implements Comparator<Student> {

    //when student o1 should be placed before o2, return -1
    @Override
    public int compare(Student o1, Student o2) {
        return o1.name.compareTo(o2.name);  // -1  0  1
    }
}
class StudentScoreComparator implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        return o1.score > o2.score? -1:1;
    }
}