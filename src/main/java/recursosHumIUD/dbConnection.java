/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package recursosHumIUD;
import java.sql.*;

/**
 *
 * @author Juan
 */
public class dbConnection {
    static String url = "jdbc:mysql://localhost:3306/recursoshumanosudi"; 
    static String user = "root";
    static String pass = "root";
    
    public static Connection conectar(){
        Connection conn = null;
        try {
            conn=DriverManager.getConnection(url, user, pass);
            System.out.println("conecion exitosa");
        } catch (Exception e) {
            e.getMessage();
        }
        return conn;
    }    
}
