/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resort;

import java.util.logging.Logger;

import Person.Administrator;
import Person.Customer;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author User
 */
public class Resort implements ServiceableResort {

    private String resortName;
    private Customer customers[];
    private Administrator admin;
    private Room rooms[];
    private Logger resortLogger;
    
    
    public Resort(){};
    public Resort(String resortName, Administrator admin, int maximumRoom) {
        
    }

    @Override
    public boolean checkIn(Customer c, RoomType t) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean checkOut(Customer c, int roomNumber) {
        // TODO Auto-generated method stub
        return false;
    }

    public int findForAvailableRoom(RoomType t) {
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i].getRoomType() == t) {
                if (rooms[i].getCustomer() == null) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int findForSpecifiedRoom(int roomNumber) {
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i].getRoomNumber() == roomNumber) {
                return i;
            }
        }
        return -1;
    }

    public void logHistory() throws IOException {
        String filename = String.format("log_history/%s.log", LocalDate.now().format(
                DateTimeFormatter.ofPattern("yyyy_MM_dd")
        ));
        //////////////////////////////////////////////////////////////////////////////
        System.setProperty("java.util.logging.SimpleFormatter.format","%5$s");
        //////////////////////////////////////////////////////////////////////////////
       
        String Test = "\nไอ้เป็ด";
        
        FileHandler fh = new FileHandler(filename ,true);
        Logger logger = Logger.getLogger(filename);
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter(); 
        fh.setFormatter(formatter);
        
        logger.info(Test);
        
    }
   
    
}
