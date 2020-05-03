package util;

import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

// this class if for input filter puspose especially for number
public class Input {

    private static Scanner sc = new Scanner(System.in);
    
    public static long inputLong() {
        while (true) {
            try {
                long input;
                do {
                    input = sc.nextLong();
                    if (input < 0) {
                        System.out.println("Invalid number!");
                        System.out.print("Please try again: ");
                    }
                } while (input < 0);
                sc.nextLine(); // clear line separator
                return input;
            } catch (InputMismatchException e) {
                System.out.print("Please enter a number: ");
                sc.next(); // clear scanner wrong input
                continue;
            }
        }
    }

    public static int inputInt() {
        while (true) {
            try {
                int input = sc.nextInt();
                sc.nextLine(); // clear line separator
                return input;
            } catch (InputMismatchException e) {
                System.out.print("Please enter a number: ");
                sc.next(); // clear scanner wrong input
                continue;
            }
        }
    }

    public static int inputYear() {
        int year;
        do {
            System.out.print("Enter years (yyyy) : ");
            year = inputInt();
            if (year > LocalDateTime.now().getYear() || year < 0) {
                System.out.println("Invalid year!");
            }
        } while (year > LocalDateTime.now().getYear() || year < 0);
        return year;
    }

    public static int inputMonth() {
        int month;
        do {
            System.out.print("Enter months (mm) : ");
            month = inputInt();
            if (month > 12 || month < 0) {
                System.out.println("Invalid month!");
            }
        } while (month > 12 || month < 0);
        return month;
    }

    public static int inputDay(int year, int month) {
        int day;
        boolean invalidDate = true;
        do {
            System.out.print("Enter days (dd) : ");
            day = inputInt();
            // [ set numDay of month ]
            int numDay;
            switch (month) {
                case 2: // case: Febuary
                    if (year % 4 == 0) {
                        numDay = 29;
                    } else {
                        numDay = 28;
                    }
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    numDay = 30;
                    break;
                default: // case: 1,3,5,7,8,10,12
                    numDay = 31;
                    break;
            }
            if (day > numDay || day < 1) {
                System.out.println("Invalid date!");
                continue;
            } else {
                invalidDate = false;
            }
        } while (invalidDate);
        return day;
    }
}