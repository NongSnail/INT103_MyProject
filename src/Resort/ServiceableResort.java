/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resort;

import Person.Customer;

/**
 *
 * @author User
 */
public interface ServiceableResort {
    public boolean checkIn(Customer c, RoomType t);
    public boolean checkOut(Customer c, int roomNumber);
}
