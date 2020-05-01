/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author pooripro
 */
public class createTable {
    
    public static void main(String[] args) {
        insertTable();
    }
    
    public static void createTable(){ 
        /*
        need to change data type to a proper type
        by looking at the Room atributes
        or DBLogger.insert set methods.

        Maybe change the table name?
        */
        try(Connection conn = DBConnection.getConnection()){
            Statement stm = conn.createStatement();
            stm.executeUpdate("DROP TABLE RESORT"); // table name
            stm.executeUpdate("CREATE TABLE RESORT ("
                    + "Room_Number INT,"
                    + "Room_Type VARCHAR(20),"
                    + "Customer_Name VARCHAR(40),"
                    + "IDCard INT,"
                    + "Phone INT,"
                    + "Status VARCHAR(20),"
                    + "CheckIn VARCHAR(20),"
                    + "ChecOut VARCHAR(20),"
                    + "Price INT"
                    + ")");
        }catch(SQLException ex){
            System.err.println(ex);
        }
    }
    
    public static void insertTable(){
        try(Connection conn = DBConnection.getConnection()){
            PreparedStatement stm = conn.prepareStatement("INSERT INTO RESORT VALUES(?,?,?,?,?,?,?,?,?)");
            stm.setInt(1, 100);
            stm.setString(2, "SINGLE_ROOM");
            stm.setString(3, "Puwa Termnuphan");
            stm.setInt(4, 0001);
            stm.setInt(5, 2193);
            stm.setString(6, "Status");
            stm.setString(7, "CheckIn");
            stm.setString(8, "CheckOut");
            stm.setInt(9, 101);
            stm.executeUpdate();
        }catch(SQLException ex){
            System.err.println(ex);
        }
    }
}
