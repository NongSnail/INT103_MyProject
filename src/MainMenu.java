
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
    private String pwdResort = "123";
    private Scanner sc;

    public MainMenu() {
        this.sc = new Scanner(System.in);
    }

    public void systemLogin() {
        while (loginScreen() == false) {
            unableLogIn();
        }
    }

    private boolean loginScreen() {
        System.out.println("▒▒▒▒▒▒▒╬ 【Authentication】 ╬▒▒▒▒▒▒▒▒");
        System.out.print("Username : ");
        String id = sc.nextLine();
        System.out.print("Password : ");
        String pwd = sc.nextLine();
        if (id.equals(idResort) && pwd.equals(pwdResort)) {
            return true;
        }
        return false;
    }

    private void unableLogIn() {
        System.out.println("-----------------------------------------------------");
        System.out.println("The Id or password you entered is incorrect.");
        System.out.println("-----------------------------------------------------");
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
        System.out.println("Enter Your Menu [1‐4]: ");
        selectedChoice = Integer.parseInt(sc.nextLine());
    }

    public void go() {
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
        System.out.println("Enter room number : ");
        int roomNumber = sc.nextInt();
        //หาห้อง
        System.out.println("Customer name : ");
        String cName = sc.nextLine();
        System.out.println("Customer ID card : ");
        long idCard = sc.nextLong();
        System.out.println("Phone number : ");
        long phoneNumber = sc.nextLong();
        //add Customer //addroom 

    }

    public void checkout() {
        System.out.println("-----------------------------------------------------");
        System.out.println("{ Checkout }");
        System.out.println("Enter room number : ");
        int roomNumber = sc.nextInt();
        //show data
        //agree?
    }

    public void listRoom() {
        System.out.println("-----------------------------------------------------");
        System.out.println("{ List Room }");
        // format roomNumber  roomtype  [status]
    }

    private boolean resetPassword() {
        System.out.println("Enter your current password : ");
        String currentpwd = sc.nextLine();
        System.out.println("Enter your new password : ");
        String newpwd = sc.nextLine();
        System.out.println("Enter your new password(again) : ");
        String newpwd2 = sc.nextLine();

        if (currentpwd.equals(pwdResort) == false) {
            System.out.println("The your current password is incorrect.");
            return false;
        }
        if (newpwd.equals(newpwd2) == false) {
            System.out.println("Please enter same value again");
            return false;
        }
//        if condition is true then we can reset passsword.
        pwdResort = newpwd2;
        return true;
    }

}
