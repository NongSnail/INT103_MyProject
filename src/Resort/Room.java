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
        this.roomStatus = r.roomStatus;
        this.customer = r.customer;
        this.checkIn = r.checkIn;
        this.checkout = r.checkout;
        this.price = r.price;
    }

    public void checkIn(Customer c) {
        customer = c;
        roomStatus = RoomStatus.NOT_AVAILABLE;
        checkIn = LocalDateTime.now();
    }

    public void checkout() {
        roomStatus = RoomStatus.AVAILABLE;
        checkout = LocalDateTime.now();
        calculatePrice();
    }

    private double calculatePrice() {
        if (roomType == RoomType.SINGLE) {
            return price = ((checkIn.until(checkout, ChronoUnit.DAYS)) * SINGLE_ROOM_PRICE) * (1 + VAT);
        }
        return price = ((checkIn.until(checkout, ChronoUnit.DAYS)) * DOUBLE_ROOM_PRICE) * (1 + VAT);
    }

    public void resetRoom() {
        roomStatus = RoomStatus.AVAILABLE;
        customer = null;
        checkIn = null;
        checkout = null;
        price = 0;
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

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public LocalDateTime getCheckout() {
        return checkout;
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
