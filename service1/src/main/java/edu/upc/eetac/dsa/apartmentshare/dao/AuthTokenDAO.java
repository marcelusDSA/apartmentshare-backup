package edu.upc.eetac.dsa.apartmentshare.dao;

import edu.upc.eetac.dsa.apartmentshare.auth.UserInfo;
import edu.upc.eetac.dsa.apartmentshare.entity.AuthToken;

import java.sql.SQLException;

/**
 * Created by mazp on 30/11/15.
 */
public interface AuthTokenDAO {
    public UserInfo getUserByAuthToken(String token) throws SQLException;
    public AuthToken createAuthToken(String userid) throws SQLException;
    public void deleteToken(String userid) throws SQLException;
}
