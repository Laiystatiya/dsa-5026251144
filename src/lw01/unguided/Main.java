package lw01.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/lw01/unguided/washes.txt");
        Scanner scanner = new Scanner(file);

        int total = scanner.nextInt();
        WashService[] washes = new WashService[total];
        int[] unitsArray = new int[total];

        for (int i = 0; i < total; i++) {
            String type = scanner.next();
            String id = scanner.next();
            int days = scanner.nextInt();
            int units = scanner.nextInt();
            unitsArray[i] = units;

            if (type.equals("MOTORCYCLE")) {
                washes[i] = new MotorcycleWash(id, days);
            } else if (type.equals("CAR")) {
                washes[i] = new CarWash(id, days);
            }
        }
        scanner.close();

        for (int i = 0; i < washes.length; i++) {
            WashService wash = washes[i];
            int units = unitsArray[i];
            System.out.println(wash.getId() + " | " + wash.label() + " | " + wash.calculateCharge(units));
        }
    }
}