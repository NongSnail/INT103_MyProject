
import Person.Administrator;
import Person.Customer;
import Resort.Resort;
import Resort.Room;
import Resort.RoomType;

import java.io.Console;
import java.util.Scanner;

import javax.sound.sampled.SourceDataLine;

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
        System.out.println("《2》CheckOut");
        System.out.println("《3》List Room");
        System.out.println("-----------------------------------------------------");
        System.out.println("《4》reset password");
        System.out.println("《5》exit");
        System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
        System.out.print("Enter Your Menu [1‐4]: ");
        selectedChoice = Integer.parseInt(sc.nextLine());

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
                    resetPassword();
                    break;
                case 5:
                    exit(0);
                    break;
                default:
                    System.out.println("-------------------------------------------------------------");
                    System.out.println("Please type again..  QwQ ");
            }
        } while (selectedChoice != 5);
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
        int roomNumber = Integer.parseInt(sc.nextLine());
        int number = resort.findForSpecifiedRoom(roomNumber);
        if (number != -1) {
            System.out.println("---[ CheckIn Room Number " + roomNumber +" ]--");
            System.out.print("Customer Name : ");
            String cName = sc.nextLine();
            System.out.print("Customer ID card : ");
            long idCard = Long.parseLong(sc.nextLine());
            System.out.print("Phone number : ");
            long phoneNumber = Long.parseLong(sc.nextLine());
            System.out.println("---[ Confirm information ]---");
            System.out.println("Room Number : "+roomNumber);
            System.out.println("{Customer}");
            System.out.println("  Name : "+ cName + "  ID card : "+idCard+"  Phone : "+ phoneNumber);
//            String case
//                switch(){
//                case y :
//                break;
//                case n :
//                break;
//                default:
//                }
            Customer c = new Customer(idCard, cName, phoneNumber);
            //RoomType t = resort.getSpecifiedRoom(number).getRoomType();
            resort.checkIn(c, roomNumber);
        } else {
            System.out.println("no data.");
        }

    }

    public void checkout() {
        System.out.println("-----------------------------------------------------");
        System.out.println("{ Checkout }");
        System.out.print("Enter room number : ");
        int roomNumber = Integer.parseInt(sc.nextLine());
        int number = resort.findForSpecifiedRoom(roomNumber);
        long idCard = resort.getSpecifiedRoom(number).getCustomer().getIdCard();
        String cName = resort.getSpecifiedRoom(number).getCustomer().getName();
        long phoneNumber = resort.getSpecifiedRoom(number).getCustomer().getPhoneNumber();
        
        System.out.println("---[ Confirm information ]---");
        System.out.println("Room Number : "+roomNumber);
        System.out.println("{Customer}");
        System.out.println("ID card : "+idCard+" Name : "+ cName +"Phone : "+ phoneNumber);
        System.out.println("Need to CheckOut? (Y/N)");
        Customer c = resort.getSpecifiedRoom(number).getCustomer();
        resort.checkOut(c, roomNumber);
 

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
        
        do {
            // currentpwd = console.readPassword("%s", "Enter your currsent password : ");
            System.out.println("Enter your current password : ");
            currentpwd = sc.nextLine();
        } while (currentpwd.equals(""));

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
            // newpwd2 = console.readPassword("%s", "Enter your new password (again) : ");
            System.out.println("Enter your new password (again) : ");
            newpwd2 = sc.nextLine();
        } while (newpwd2.equals(""));
        //condition !Arrays.equals(currentpwd, pwdResort)
        if (currentpwd.equals(pwdResort)) {
            System.out.println("--------------------------------------");
            System.out.println("The your current password is incorrect.");
            System.out.println("--------------------------------------");
            return false;
        }
        // condition !Arrays.equals(newpwd, newpwd2)
        if (newpwd.equals(newpwd2)) {
            System.out.println("--------------------------------------");
            System.out.println("Please enter same value again");
            System.out.println("--------------------------------------");
            return false;
        }
        // if condition is true then we can reset passsword.
        System.out.println("--------------------------------------");
        System.out.println("UPDATE PASSWORD");
        System.out.println("--------------------------------------");
        pwdResort = newpwd2;
        return true;
    }
    
//    
//    public void backToMainMenu(){
//        System.out.println("Return to the menu page");
//    }
    
}
