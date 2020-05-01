package dataaccess;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;

import Resort.Room;

public class DBLogger {
    // insert System
    public void insert(Room r) {
        String sql = "INSERT INTO RESORT VALUES(?,?,?,?,?,?,?,?,?)"; // table name
        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, r.getRoomNumber());
            ps.setString(2, r.getRoomType().name());
            ps.setString(3, r.getCustomer().getName());
            ps.setInt(4, (int) r.getCustomer().getIdCard());
            ps.setInt(5, (int) r.getCustomer().getPhoneNumber());
            ps.setString(6, r.getRoomStatus().name());
            ps.setDate(7, Date.valueOf(r.getCheckIn().toLocalDate()));
            ps.setDate(8, Date.valueOf(r.getCheckout().toLocalDate()));
            ps.setDouble(9, r.getPrice());
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectFromSpecifiedDate(int year, int month, int day) {
        String sql = "SELECT * FROM RESORT WHERE CHECKIN=?-?-? OR CHECKOUT=?-?-?"; // table name
        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, year);
            ps.setInt(2, month);
            ps.setInt(3, day);
            ps.setInt(4, year);
            ps.setInt(5, month);
            ps.setInt(6, day);
            ps.executeUpdate();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectAll() {
        String sql = "SELECT * FROM RESORT"; // table name
        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}