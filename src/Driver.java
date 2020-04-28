
import Person.Administrator;
import Resort.Resort;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class Driver {

    public static void main(String[] args) {
        Administrator admin1 = new Administrator("1112","LuLu");
        Resort resort = new Resort("LuLu the cat",admin1,5);

        MainMenu m1 = new MainMenu();
            m1.systemLogin();
            m1.go();
        
    }
}
