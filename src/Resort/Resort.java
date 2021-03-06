/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resort;

import java.util.logging.Logger;

import Person.Administrator;
import Person.Customer;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private Administrator admin;
    private Room rooms[];
    private int totalRoom;

    public Resort(String resortName, Administrator admin, int maximumRoom) {
        this.resortName = resortName;
        this.rooms = new Room[maximumRoom];
        this.admin = admin;
    }

    public boolean buildRoom(RoomType rt) {
        if (totalRoom < rooms.length) {
            rooms[totalRoom] = new Room(++totalRoom, rt);
            return true;
        }
        return false;
    }

    @Override
    public boolean checkIn(Customer c, int roomNumber) {
        int roomIndex = findForSpecifiedRoom(roomNumber); // set index for rooms[]
        // if no room available OR Customer == null
        if (roomIndex == -1 || c == null || rooms[roomIndex].getCustomer() != null) {
            return false;
        }
        rooms[roomIndex].checkIn(c);
        logHistory(rooms[roomIndex]); // to log history
        return true;
    }

    @Override
    public boolean checkout(Customer c, int roomNumber) {
        int roomIndex = findForSpecifiedRoom(roomNumber); // set index for rooms[]
        // if roomNumber not found OR Customer == null OR Customer doesn't match with this rooom.
        if (roomIndex == -1 || c == null || !rooms[roomIndex].getCustomer().equals(c) || rooms[roomIndex].getCustomer() == null) {
            return false;
        }
        rooms[roomIndex].checkout();
        logHistory(rooms[roomIndex]); // to log history
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

    public Room[] getRooms() {
        return rooms;
    }

    public Room getSpecifiedRoom(int i) {
        return rooms[i];
    }

    String format = "\n%1$-12s %2$-10s %3$-15s %4$-15s %5$-15s %6$-15s %7$-28s %8$-28s %9$-15s";

    public void logHistory(Room r) {
        //format filename
        String filename = String.format("log_history/%s.log", LocalDate.now().format(
                DateTimeFormatter.ofPattern("yyyy_MM_dd")
        ));
        //////////////////////////////////////////////////////////////////////////////
        //format logger
        System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s");
        //////////////////////////////////////////////////////////////////////////////
        long id = r.getCustomer().getIdCard();
        String name_customer = r.getCustomer().getName();
        long phone = r.getCustomer().getPhoneNumber();

        int roomNumber = r.getRoomNumber();
        RoomType roomType = r.getRoomType();
        RoomStatus roomStatus = r.getRoomStatus();
        LocalDateTime checkIn = r.getCheckIn();
        LocalDateTime checkOut = r.getCheckout();
        double price = r.getPrice();
        //format datetime
        String checkInStr = checkIn.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a"));
        String checkOutStr;
        if (checkOut == null) {
            checkOutStr = "-";
        } else {
            checkOutStr = checkOut.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a"));
        }

        //////////////////////////////////////////////////////////////////////////////
        FileHandler fh;
        try {
            fh = new FileHandler(filename, true);
            Logger logger = Logger.getLogger(filename);
            logger.addHandler(fh);
            logger.setUseParentHandlers(false);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            if (!checkHeader(filename)) {
                logger.info(getHeader());
            }

            //log here
            logger.info(String.format(format,
                    roomNumber, roomType, name_customer, id, phone, roomStatus, checkInStr, checkOutStr, price));

        } catch (IOException | SecurityException ex) { }

    }

    //Header table
    public static String getHeader() {
        return "Record history"
                + "\n----------------------------------------------------------------------------------------------------------------------------------------------------------------------"
                + String.format("\n%1$-12s %2$-10s %3$-15s %4$-15s %5$-15s %6$-15s %7$-28s %8$-28s %9$-15s",
                        "Room_Number", "Room_Type", "Customer_Name", "IDCard", "Phone", "Status", "CheckIn", "CheckOut", "Price")
                + "\n----------------------------------------------------------------------------------------------------------------------------------------------------------------------";
    }

    public static boolean checkHeader(String fileName) {
        String firstLine = null;
        try {
            firstLine = Files.lines(Paths.get(fileName)).findFirst().get();
        } catch (NoSuchElementException | IOException e) {

        }
        return "Record history".equals(firstLine);
    }

    public  void readHistory(String file) {
        try {
            FileInputStream fstream = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                System.out.println(strLine);
            }
            fstream.close();
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        }
    }
}
