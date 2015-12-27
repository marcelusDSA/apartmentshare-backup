package edu.upc.eetac.dsa.apartmentshare.dao;

import edu.upc.eetac.dsa.apartmentshare.entity.User;

import java.sql.SQLException;

/**
 * Created by mazp on 30/11/15.
 */
public interface UserDAO {

    public User createUser(String loginid, String password, String fullname, String email, String phone)  throws SQLException, UserAlreadyExistsException;

    public User updateProfile(String id, String email, String fullname, String phone) throws SQLException;

    public User getUserById(String id) throws SQLException;

    public User getUserByLoginid(String loginid) throws SQLException;

    public boolean deleteUser(String id) throws SQLException;

    public boolean checkPassword(String id, String password) throws SQLException;


}
