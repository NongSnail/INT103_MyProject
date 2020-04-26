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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author User
 */
public class Resort implements ServiceableResort {

    private String resortName;
    private Customer customers[]; // This attribute hasn't been used OR Apply to Customer who checkIn? IF yes -> need more finder methods.
    private Administrator admin;
    private Room rooms[];
    private Logger resortLogger;
    
    public Resort(String resortName, Administrator admin, int maximumRoom) {
        this.resortName = resortName;
        this.rooms = new Room[maximumRoom];
        this.customers = new Customer[maximumRoom];
        this.admin = admin;
    }

    @Override
    public boolean checkIn(Customer c, RoomType t) {
        int roomIndex = findForAvailableRoom(t); // set index for rooms[]
        // if no room available OR Customer == null
        if (roomIndex == -1 || c == null) {
            return false;
        }
        rooms[roomIndex].checkIn(c);
        logHistory(); // need to handle IOException // to log history
        return true;
    }

    @Override
    public boolean checkOut(Customer c, int roomNumber) {
        int roomIndex = findForSpecifiedRoom(roomNumber); // set index for rooms[]
        // if roomNumber not found OR Customer == null OR Customer doesn't match with this rooom.
        if (roomIndex == -1 || c == null || !rooms[roomIndex].getCustomer().equals(c)) {
            return false;
        }
        rooms[roomIndex].checkout();
        logHistory(); // need to handle IOException // to losg history
        rooms[roomIndex].resetRoom();
        return true;
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
        
        if(!checkHeader(filename)){
            logger.info(getHeader());
        }
        
        logger.info(Test);
        
    }
    
    public static String getHeader(){
        LocalDate day = LocalDate.now();
        return "Record history : "+day+
                //Test
        "\n----------------------------------------------------------------------------------------------------------------------------" +
        String.format("\n%5s %10s %10s %15s %15s %15s %15s %15s %15s %10s %10s", 
                "Room","Room_Type","Customer_Name","IDCard","phone","status","date","Many_Nigth","Room_rate","Total","check") +
        "\n----------------------------------------------------------------------------------------------------------------------------";
    }
    
    public static boolean checkHeader(String fileName){
        String firstLine = null;
        try {
            firstLine = Files.lines(Paths.get(fileName)).findFirst().get(); 
        } catch (NoSuchElementException e) { } catch (IOException e) { }
        return "-----------------------------------------------------------------------------------------------".equals(firstLine);
    }
    
   
    
}
