package lw03.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        problem1();
        problem2();
        problem3();
    }

    static void problem1() throws FileNotFoundException {
        List<String> playlist = new ArrayList<>();
        Scanner sc = new Scanner(new File("src/lw03/prelab/playlist.txt"));

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split(" ", 2);   // [0] = perintah, [1] = sisanya
            String type = parts[0];

            if (type.equals("ADD")) {
                playlist.add(parts[1]);
            } else if (type.equals("INSERT")) {
                String[] p = line.split(" ", 3);   // [0]=INSERT, [1]=index, [2]=lagu
                int index = Integer.parseInt(p[1]);
                playlist.add(index, p[2]);
            } else if (type.equals("REMOVE")) {
                playlist.remove(parts[1]);
            }
        }
        sc.close();

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }
    }

    static void problem2() throws FileNotFoundException {
        Set<String> participants = new LinkedHashSet<>();
        int duplicates = 0;
        Scanner sc = new Scanner(new File("src/lw03/prelab/participants.txt"));

        while (sc.hasNextLine()) {
            String name = sc.nextLine().trim();
            if (name.isEmpty()) continue;

            if (!participants.add(name)) {
                duplicates++;
            }
        }
        sc.close();

        System.out.println();
        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + participants.size());
        int no = 1;
        for (String name : participants) {
            System.out.println(no + ". " + name);
            no++;
        }
        System.out.println("Duplicate registrations: " + duplicates);
    }

    static void problem3() throws FileNotFoundException {
        Map<String, Integer> stock = new LinkedHashMap<>();
        int failedSales = 0;
        Scanner sc = new Scanner(new File("src/lw03/prelab/inventory.txt"));

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split(" ");   // [0]=tipe, [1]=produk, [2]=jumlah
            String type = parts[0];
            String product = parts[1];
            int qty = Integer.parseInt(parts[2]);

            if (type.equals("ADD")) {
                if (stock.containsKey(product)) {
                    stock.put(product, stock.get(product) + qty);
                } else {
                    stock.put(product, qty);
                }
            } else if (type.equals("SELL")) {
                if (stock.containsKey(product) && stock.get(product) >= qty) {
                    stock.put(product, stock.get(product) - qty);
                } else {
                    failedSales++;
                }
            }
        }
        sc.close();

        System.out.println();
        System.out.println("===== Problem 3 =====");
        for (Map.Entry<String, Integer> e : stock.entrySet()) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }
        System.out.println("Failed sales: " + failedSales);
    }
}