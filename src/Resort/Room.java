package Resort;

import Person.Customer;
import Policy.Price;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Room implements Price {
    private int roomNumber;
    private RoomType roomType;
    private RoomStatus roomStatus;
    private Customer customer;
    private LocalDateTime checkIn;
    private LocalDateTime checkout;
    private double price;



    private class dateComparator implements Comparable<LocalDateTime> {

        @Override
        public int compareTo(LocalDateTime o) {
            return this.compareTo(o);
        }

    }

    public Room(int number) {
        this.roomNumber = number;
        roomStatus = RoomStatus.AVAILABLE;
    }

    public Room(Room r) {
        this.roomNumber = r.roomNumber;
        this.roomType = r.roomType;
        // need to add more setter for logging purpose(to copy object)
    }

    public void checkIn(Customer c) {
        customer = c;
        roomStatus = RoomStatus.NOT_AVAILABLE;
        checkIn = LocalDateTime.now();
    }

    public void checkout() {
        roomStatus = RoomStatus.AVAILABLE;
        checkout = LocalDateTime.now();
        price = calculatePrice();
    }

    private double calculatePrice() {
        if (roomType == RoomType.SINGLE) {
            return price = ((checkIn.until(checkout, ChronoUnit.DAYS)) * SINGLE_ROOM_PRICE)*VAT;
        }
        return price = ((checkIn.until(checkout, ChronoUnit.DAYS)) * DOUBLE_ROOM_PRICE)*VAT ;
    }

    public void resetRoom() {
        roomStatus = RoomStatus.AVAILABLE;
        customer = null;
        checkIn = null;
        checkout = null;
        price = 0;
        // need more null setter to clean all data
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Room other = (Room) obj;
        if (this.roomNumber != other.roomNumber) {
            return false;
        }
        return true;
    }

}
