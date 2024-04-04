package org.example.util;

import java.util.Scanner;
import java.util.UUID;


//Terminal'den input almak icin ve essiz bir id olusturmak icin Util sinifi olusturdum.
// Util class'indan obje olusturulmasinin onune gecmek icin de abstract class olarak tanimladim.
public abstract class Util {
    public static Scanner scanner = new Scanner(System.in);
    public static String stringScanner(String input){
        System.out.print(input);
        return scanner.nextLine();
    }

    public static int intScanner(String input){
        System.out.print(input);
        int inp = scanner.nextInt();
        scanner.nextLine();
        return inp;
    }

    public static Long longScanner(String input){
        System.out.print(input);
        Long inp = scanner.nextLong();
        scanner.nextLine();
        return inp;
    }

    public static double doubleScanner(String input){
        System.out.print(input);
        double inp = scanner.nextDouble();
        scanner.nextLine();
        return inp;
    }

    public static String UUIDGenerator(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

}
