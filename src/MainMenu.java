
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
    private Scanner sc;
    
    public MainMenu(){
        this.sc = new Scanner(System.in);
    }
    
    public void display(){
        System.out.println("--------------╬ 【Resort Manager System】 ╬--------------");
        System.out.println("   【 MainMenu 】");
        System.out.println("《1》CheckIn");
        System.out.println("《2》CheckOut");
        System.out.println("《3》List Room");
        System.out.println("《4》");
        System.out.println("---------------------------------------------------------");
        System.out.println("Enter Your Menu [1‐4]: ");
        selectedChoice = Integer.parseInt(sc.nextLine());       
    }
    
    public void go(){
        switch(selectedChoice) {
            case 1:   
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                exit(0);
                break;
            default:    
                System.out.println("-------------------------------------------------------------");
                System.out.println("Please type again..");
        }
                
    }
    public int getSelectedChoice(){
        return selectedChoice;
    }
    
    public void exit(int exitedCode){
        System.out.println("");
        sc.close();
        System.exit(exitedCode);
    }
    
    public void checkIn(){}
    public void checkout(){}
    
}
