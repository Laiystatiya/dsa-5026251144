package lw03.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
    
        Set<String> registered = new LinkedHashSet<>();
        Scanner regScanner = new Scanner(new File("src/lw03/unguided/registrations.txt"));
        while (regScanner.hasNext()) {
            String id = regScanner.next();
            registered.add(id);
        }
        regScanner.close();

        
        Set<String> checkedIn = new LinkedHashSet<>();
        int rejected = 0;

        
        System.out.println("===== Event Check-In Results =====");
        Scanner checkScanner = new Scanner(new File("src/lw03/unguided/checkins.txt"));
        while (checkScanner.hasNext()) {
            String id = checkScanner.next();

            if (!registered.contains(id)) {
                // belum terdaftar
                System.out.println(id + ": Rejected (not registered)");
                rejected++;
            } else if (checkedIn.contains(id)) {
                // terdaftar tapi sudah pernah check-in
                System.out.println(id + ": Rejected (already checked in)");
                rejected++;
            } else {
                // terdaftar dan belum check-in > berhasil
                checkedIn.add(id);
                System.out.println(id + ": Checked in");
            }
        }
        checkScanner.close();

        
        int absent = registered.size() - checkedIn.size();

        System.out.println("===== Final Event Summary =====");
        System.out.println("Registered students: " + registered.size());
        System.out.println("Successful check-ins: " + checkedIn.size());
        System.out.println("Absent students: " + absent);
        System.out.println("Rejected attempts: " + rejected);
    }
}