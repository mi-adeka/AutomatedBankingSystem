/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automated.banking.system.dao;

import automated.banking.system.Transaction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sun.util.logging.PlatformLogger;


public class IAuthenticationDaoImpl implements IAuthenticationDao {
    
    MyDBConnection connection;
    Connection c;
    
    public IAuthenticationDaoImpl()
    {
        connection = new MyDBConnection();
        connection.createConnection("jdbc:mysql://localhost:3306/AutomatedBankingSystem", "root", "root");
        c = connection.getConnection();
    
    }
    @Override
    public void createEmployee(String firstname, String secondname, String othername, String address, int phoneNo, String username, String password, String email) {
        try {
            PreparedStatement statement = c.prepareStatement("insert into Employee(firstname,secondname,othername,address,phoneNo,username,password,email) values(?,?,?,?,?,?,?,?)");
            statement.setString(1, firstname);
            statement.setString(2, secondname);
            statement.setString(3, othername);
            statement.setString(4, address);
            statement.setInt(5, phoneNo);
            statement.setString(6, username);
            statement.setString(7, password);
            statement.setString(8, email);
            statement.executeQuery();
        } catch (SQLException e) {
            Logger.getLogger(IAuthenticationDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void delete(String username) {
        
    }

    @Override
    public void update() {
        
    }

    @Override
    public void select(String username) {
        try {
            PreparedStatement statement = c.prepareStatement("select * from Employee where Username = ?");
            statement.setString(1, username);
            ResultSet result = statement.executeQuery();
            
            while(result.next()) {
            
            }
        } catch (SQLException e) {
            Logger.getLogger(IAuthenticationDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public int authenticateEmployee(String username, String password) {
        try {
            PreparedStatement statement = c.prepareStatement("SELECT FirstName,SecondName,OtherName,Address,PhoneNumber,Username,Password,Email_Address from Employee where Username = ?");
            statement.setString(1, username);
            ResultSet result = statement.executeQuery();
            
            while(result.next()){
                String passwrd = result.getString(6);
                if(password.equals(passwrd)){
                   return 1;
                }else{
                    return 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int authenticateAdmin(String username, String Password) {
         try {
            PreparedStatement statement = c.prepareStatement("SELECT Username,Password FROM Admin WHERE Username = ?");
            statement.setString(1, username);
            ResultSet result = statement.executeQuery();
            
            while(result.next()){
                String passwrd = result.getString(1);
                if(Password.equals(passwrd)){
                   return 1;
                }else{
                    return 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void createAdmin(String username, String password) {
       
    }
    
    public static void main(String[] args) {
        System.out.println(new IAuthenticationDaoImpl().authenticateAdmin("Manager", "admin"));
    }
    
    //    public static void main(String[] args) { 
//        IAuthenticationDaoImpl Impl = new IAuthenticationDaoImpl();
//        Impl.authenticate("Manager", "Admin");
//    }
}
