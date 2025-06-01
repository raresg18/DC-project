import java.util.*;

public class SortingBenchmark {

    public static void main(String[] args) {
        Sort s = new Sort();
        s.run();
    }
}


class Sort {

    private static final int SIZE = 1_000_000;
    private final int[] originalArray = new int[SIZE];
    private final StringBuilder results = new StringBuilder();

    public String run() {
        generateRandomArray();

        results.setLength(0); 
        results.append(benchmark("Java Arrays.sort", SortingAlgorithms::javaSort));
        results.append(benchmark("Custom QuickSort", SortingAlgorithms::quickSort));
        results.append(benchmark("Custom MergeSort", SortingAlgorithms::mergeSort));

        return results.toString();
    }

    private void generateRandomArray() {
        Random r = new Random(42);
        for (int i = 0; i < SIZE; i++) {
            originalArray[i] = r.nextInt();
        }
    }

    private String benchmark(String name, Consumer<int[]> sortMethod) {
        int[] copy = Arrays.copyOf(originalArray, SIZE);
        long start = System.nanoTime();
        sortMethod.accept(copy);
        long end = System.nanoTime();
        double ms = (end - start) / 1e6;
        String result = String.format("%s: %.2f ms%n", name, ms);
        results.append(result);
        return result;
    }

    interface Consumer<T> {
        void accept(T t);
    }
}



class SortingAlgorithms {

    public static void javaSort(int[] array) {
        Arrays.sort(array);
    }

    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    public static void mergeSort(int[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    private static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        int[] L = new int[n1];
        int[] R = new int[n2];
        System.arraycopy(arr, l, L, 0, n1);
        System.arraycopy(arr, m + 1, R, 0, n2);
        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            arr[k++] = (L[i] <= R[j]) ? L[i++] : R[j++];
        }
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
