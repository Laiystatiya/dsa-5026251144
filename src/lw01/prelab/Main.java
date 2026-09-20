package lw01.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<PrintJob> jobs = new ArrayList<>();

        File file = new File("src/lw01/prelab/jobs.txt");
        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()) {
            String type = scanner.next();
            String id = scanner.next();
            int pages = scanner.nextInt();

            if (type.equals("MONO")) {
                jobs.add(new MonoPrint(id, pages));
            } else if (type.equals("COLOUR")) {
                jobs.add(new ColourPrint(id, pages));
            }
        }
        scanner.close();

        for (PrintJob job : jobs) {
            System.out.println(job.summary());
        }
    }
}