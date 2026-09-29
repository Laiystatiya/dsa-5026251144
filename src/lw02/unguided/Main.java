package lw02.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        LinkedList<String[]> orders = new LinkedList<>();

        LinkedList<String[]> foodStock = new LinkedList<>();
        foodStock.add(new String[]{"Bakso", "2"});
        foodStock.add(new String[]{"Sate", "1"});
        foodStock.add(new String[]{"Soto", "2"});

        LinkedList<String[]> drinkStock = new LinkedList<>();
        drinkStock.add(new String[]{"EsTeh", "4"});
        drinkStock.add(new String[]{"EsJeruk", "2"});

        LinkedList<String[]> successfulOrders = new LinkedList<>();

        File file = new File("src/lw02/unguided/orders.txt");
        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()) {
            String name = scanner.next();
            String food = scanner.next();
            String drink = scanner.next();
            String table = scanner.next();
            orders.add(new String[]{name, food, drink, table});
        }
        scanner.close();

        Queue<String[]> queue = new LinkedList<>();
        queue.addAll(orders);

        Stack<String[]> failedOrders = new Stack<>();

        while (!queue.isEmpty()) {
            String[] order = queue.poll();
            String name = order[0];
            String food = order[1];
            String drink = order[2];
            String table = order[3];

            boolean available = true;

            String[] foodItem = null;
            if (!food.equals("-")) {
                for (String[] f : foodStock) {
                    if (f[0].equals(food)) {
                        foodItem = f;
                        break;
                    }
                }
                if (Integer.parseInt(foodItem[1]) <= 0) {
                    available = false;
                }
            }

            String[] drinkItem = null;
            if (!drink.equals("-")) {
                for (String[] d : drinkStock) {
                    if (d[0].equals(drink)) {
                        drinkItem = d;
                        break;
                    }
                }
                if (Integer.parseInt(drinkItem[1]) <= 0) {
                    available = false;
                }
            }

            if (available) {
                if (foodItem != null) {
                    int stock = Integer.parseInt(foodItem[1]);
                    foodItem[1] = String.valueOf(stock - 1);
                }
                if (drinkItem != null) {
                    int stock = Integer.parseInt(drinkItem[1]);
                    drinkItem[1] = String.valueOf(stock - 1);
                }
                successfulOrders.add(order);
            } else {
                failedOrders.push(order);
            }
        }

        System.out.println("=== Successfully Processed Orders ===");
        for (String[] order : successfulOrders) {
            System.out.println(order[0] + " " + order[1] + " " + order[2] + " " + order[3]);
        }

        System.out.println();
        System.out.println("=== Remaining Food Stock ===");
        for (String[] f : foodStock) {
            System.out.println(f[0] + " : " + f[1]);
        }

        System.out.println();
        System.out.println("=== Remaining Drink Stock ===");
        for (String[] d : drinkStock) {
            System.out.println(d[0] + " : " + d[1]);
        }

        System.out.println();
        System.out.println("=== Failed Orders ===");
        while (!failedOrders.isEmpty()) {
            String[] order = failedOrders.pop();
            System.out.println(order[0] + " " + order[1] + " " + order[2] + " " + order[3]);
        }
    }
}