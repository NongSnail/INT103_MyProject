
import Person.Administrator;
import Person.Customer;
import Resort.Resort;
import Resort.Room;
import Resort.RoomType;

import java.io.Console;
import java.util.InputMismatchException;
import java.util.Scanner;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
public class MainMenu {

    private int selectedChoice;
    private String idResort = "admin";
    // private char[] pwdResort = { '1', '2', '3' };
    private String pwdResort = "123";
    private Console console;

    private Scanner sc;
    private Resort resort;
    private Room room;

    private final String resortName = "LuLu the cat";
    private final int max = 10;
    private final int NumberOfRoom_SINGLE = 5;
    private final int NumberOfRoom_DOUBLE = 5;

    public MainMenu() {
        this.console = System.console();
        this.sc = new Scanner(System.in);
        Administrator admin1 = new Administrator("1112", "LuLu");
        resort = new Resort(resortName, admin1, max);

        for (int i = 1; i <= NumberOfRoom_SINGLE; i++) {
            resort.buildRoom(RoomType.SINGLE);
        }

        for (int i = 1; i <= NumberOfRoom_DOUBLE; i++) {
            resort.buildRoom(RoomType.DOUBLE);
        }

    }

    public void systemLogin() {
        while (loginScreen() == false) {
            System.out.println("-----------------------------------------------------");
            System.out.println("The Id or password you entered is incorrect.");
            System.out.println("-----------------------------------------------------");
        }
    }

    private boolean loginScreen() {
        System.out.println("▒▒▒▒▒▒▒╬ 【Authentication】 ╬▒▒▒▒▒▒▒▒");
        System.out.print("Username : ");
        String id = sc.nextLine();
        // char[] pwd = console.readPassword("%s", "Password : ");
        System.out.print("Password : ");
        String pwd = sc.nextLine();
        // condition : Arrays.equals(pwd, pwdResort
        if (id.equals(idResort) && pwd.equals(pwdResort)) {
            return true;
        }
        return false;
    }

    public void display() {
        System.out.println("▒▒▒▒▒▒▒╬ 【Resort Manager System】 ╬▒▒▒▒▒▒▒▒");
        System.out.println("   【 MainMenu 】");
        System.out.println("《1》CheckIn");
        System.out.println("《2》Checkout");
        System.out.println("《3》List Room");
        System.out.println("《4》History");
        System.out.println("-----------------------------------------------------");
        System.out.println("《5》reset password");
        System.out.println("《6》exit");
        System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
        System.out.print("Enter Your Menu [1‐5]: ");
        selectedChoice = inputInt();

    }

    public void go() {
        do {
            display();
            switch (selectedChoice) {
                case 1:
                    checkIn();
                    break;
                case 2:
                    checkout();
                    break;
                case 3:
                    listRoom();
                    break;
                case 4:
                    history();
                    break;
                case 5:
                    resetPassword();
                    break;
                case 6:
                    exit(0);
                default:
                    System.out.println("-------------------------------------------------------------");
                    System.out.println("Please type again..  QwQ ");
            }
        } while (selectedChoice != 6);
    }

    public int getSelectedChoice() {
        return selectedChoice;
    }

    public void exit(int exitedCode) {
        System.out.println("");
        sc.close();
        System.exit(exitedCode);
    }

    public void checkIn() {
        System.out.println("-----------------------------------------------------");
        System.out.println("{ CheckIn }");
        System.out.print("Enter room number : ");
        int roomNumber = inputInt();
        int number = resort.findForSpecifiedRoom(roomNumber);
        if (number != -1) {
            System.out.println("-----------[ CheckIn At Room Number " + roomNumber + " ]-----------");
            System.out.print("Customer Name : ");
            String cName = sc.nextLine();
            System.out.print("Customer ID card : ");
            long idCard = inputLong();
            System.out.print("Phone number : ");
            long phoneNumber = inputLong();

            while (true) {
                System.out.println("");
                System.out.println("---------------------[ Confirm information ]---------------------");
                System.out.println("Room Number : " + roomNumber);
                System.out.println("{ Customer }");
                System.out.println("Name : " + cName + "  ID card : " + idCard + "  Phone : " + phoneNumber);
                System.out.println("");
                System.out.println("Yes or No? [ press Y/N ]");
                String s = sc.nextLine();
                if (s.equals("y")) {
                    Customer c = new Customer(idCard, cName, phoneNumber);
                    //RoomType t = resort.getSpecifiedRoom(number).getRoomType();
                    resort.checkIn(c, roomNumber);
                    System.out.println(" [ CheckIn Completed ]");
                } else if (s.equals("n")) {
                    break;
                } else {
                    System.out.println("-------------------------------------------------------------");
                    System.out.println("Please type again..  QwQ ");
                }
            }

        }
    }

    public void checkout() {
        System.out.println("-----------------------------------------------------");
        System.out.println("{ Checkout }");
        System.out.print("Enter room number : ");
        int roomNumber = inputInt();
        int number = resort.findForSpecifiedRoom(roomNumber);
        long idCard = resort.getSpecifiedRoom(number).getCustomer().getIdCard();
        String cName = resort.getSpecifiedRoom(number).getCustomer().getName();
        long phoneNumber = resort.getSpecifiedRoom(number).getCustomer().getPhoneNumber();
        while (true) {
            System.out.println("-----------[ Confirm information ]-----------");
            System.out.println("Room Number : " + roomNumber);
            System.out.println("{ Customer }");
            System.out.println("ID card : " + idCard + " Name : " + cName + "Phone : " + phoneNumber);
            System.out.println("Need to Checkout? Yes or No? (Y/N)");
            String s = sc.nextLine();
            if (s.equals("y")) {
                Customer c = resort.getSpecifiedRoom(number).getCustomer();
                resort.checkout(c, roomNumber);
                System.out.println(" [ Checkout Completed ]");
            } else if (s.equals("n")) {
                break;
            } else {
                System.out.println("-------------------------------------------------------------");
                System.out.println("Please type again..  QwQ ");
            }

        }

    }

    public void listRoom() {
        System.out.println("-----------------------------------------------------");
        System.out.println("{ List Room }");
        Room[] rs = resort.getRooms();
        System.out.println(String.format("%5s %10s %10s", "Room Number", "Room Type", "Status"));
        for (int i = 0; i < rs.length; i++) {
            if (rs[i] == null) {
                break;
            }
            System.out.println(String.format("%5s %15s %15s", rs[i].getRoomNumber(), rs[i].getRoomType().toString(),
                    rs[i].getRoomStatus()));

        }
        System.out.println("-----------------------------------------------------");
        System.out.println("");
        System.out.println("");
    }

    private boolean resetPassword() {

        String currentpwd;
        // char[] currentpwd;

        // [ current password checker ]
        int currentPasswordInputCounter = 0;
        do {
            // currentpwd = console.readPassword("%s", "Enter your currsent password : ");
            System.out.println("Enter your current password : ");
            currentpwd = sc.nextLine();
            //condition !Arrays.equals(currentpwd, pwdResort)
            if (!currentpwd.equals(pwdResort)) {
                System.out.println("--------------------------------------");
                System.out.println(" Your current password is incorrect.");
                System.out.println("--------------------------------------");
                currentPasswordInputCounter++;
                if (currentPasswordInputCounter > 2) {
                    System.out.println("!!! You have entered incorrect password too much. (more than 3 times) !!!");
                    return false;
                }
            }
        } while (!currentpwd.equals(pwdResort));

        String newpwd;
        // char[] newpwd;
        do {
            // newpwd = console.readPassword("%s", "Enter your new password : ");
            System.out.println("Enter your new password : ");
            newpwd = sc.nextLine();
        } while (newpwd.equals(""));

        String newpwd2;
        // char[] newpwd2;
        do {
            do {
                // newpwd2 = console.readPassword("%s", "Enter your new password (again) : ");
                System.out.println("Enter your new password (again) : ");
                newpwd2 = sc.nextLine();
            } while (newpwd2.equals(""));
            
            // condition !Arrays.equals(newpwd, newpwd2)
            if (!newpwd.equals(newpwd2)) {
                System.out.println("--------------------------------------");
                System.out.println("  Please enter the same value again");
                System.out.println("--------------------------------------");
                return false;
            }
        } while (!newpwd.equals(newpwd2));
        
        // if condition is true then we can reset passsword.
        System.out.println("--------------------------------------");
        System.out.println("     PASSWORD HAS BEEN UPDATED");
        System.out.println("--------------------------------------");
        pwdResort = newpwd2;
        return true;
    }

    public void history() {
        // to show History of each day
    }

    public long inputLong() {
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

    public int inputInt() {
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

}
