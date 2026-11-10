package lw04.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        // baca input dari parcels.txt
        Scanner sc = new Scanner(new File("src/lw04/unguided/parcels.txt"));

        int n = sc.nextInt();
        int[] parcels = new int[n];
        for (int i = 0; i < n; i++) {
            parcels[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        int[] targets = new int[m];
        for (int i = 0; i < m; i++) {
            targets[i] = sc.nextInt();
        }
        sc.close();

        // bubble sort descending, total n-1 pass
        int[] afterTwo = new int[n];

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (parcels[j] < parcels[j + 1]) {
                    int temp = parcels[j];
                    parcels[j] = parcels[j + 1];
                    parcels[j + 1] = temp;
                }
            }

            // salin rak tepat setelah pass ke-2 selesai (baterai habis)
            if (i == 1) {
                for (int k = 0; k < n; k++) {
                    afterTwo[k] = parcels[k];
                }
            }
        }

        System.out.println("==== After 2 Passes ====");
        printArray(afterTwo);

        System.out.println("==== Fully Sorted ====");
        printArray(parcels);

        // binary search untuk setiap target
        System.out.println("==== Search Results ====");
        for (int t = 0; t < m; t++) {
            int target = targets[t];
            int low = 0;
            int high = n - 1;
            int steps = 0;
            int foundIndex = -1;

            while (low <= high) {
                int mid = (low + high) / 2;
                steps++;

                if (parcels[mid] == target) {
                    foundIndex = mid;
                    break;
                } else if (parcels[mid] < target) {
                    // urutan menurun, target lebih besar ada di kiri
                    high = mid - 1;
                } else {
                    // target lebih kecil ada di kanan
                    low = mid + 1;
                }
            }

            if (foundIndex != -1) {
                System.out.println(target + ": FOUND at index " + foundIndex + " (steps: " + steps + ")");
            } else {
                System.out.println(target + ": NOT FOUND (steps: " + steps + ")");
            }
        }
    }

    static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(arr[i]);
        }
        System.out.println();
    }
}