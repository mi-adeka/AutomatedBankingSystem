/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automated.banking.system.dao;

/**
 *
 * @author Adams
 */
public interface IAuthenticationDao {
    public void createEmployee(String firstname, String secondname, String othername, String address, int phoneNo, String username, String password, String email);
    public void createAdmin(String username, String password);
    public void delete(String username);
    public void update();
    public void select(String username);
    public int authenticateEmployee(String username, String password);
    public int authenticateAdmin(String username, String Password);
}
