/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resort;

import java.util.logging.Logger;

import Person.Administrator;
import Person.Customer;

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

    private void logHistory() {
        
    }
    
}
