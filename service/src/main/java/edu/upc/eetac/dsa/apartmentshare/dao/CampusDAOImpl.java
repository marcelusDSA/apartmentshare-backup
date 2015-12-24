package edu.upc.eetac.dsa.apartmentshare.dao;

import edu.upc.eetac.dsa.apartmentshare.entity.CampusLocation;
import edu.upc.eetac.dsa.apartmentshare.entity.CampusLocationCollection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Jordi on 13/12/2015.
 */
public class CampusDAOImpl implements CampusDAO{


    @Override
    public CampusLocation createCampus(String userid, String campusname, String address, float longitud, float latitud) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        String id = null;
        try {
            connection = Database.getConnection();

            stmt = connection.prepareStatement(CampusDAOQuery.UUID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                id = rs.getString(1);
            else
                throw new SQLException();

            stmt = connection.prepareStatement(CampusDAOQuery.CREATE_CAMPUS);
            stmt.setString(1, id);
            stmt.setString(2, campusname);
            stmt.setString(3, address);
            stmt.setFloat(4, longitud);
            stmt.setFloat(5, latitud);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }
        return getCampusById(id);
    }

    @Override
    public CampusLocation getCampusById(String id) throws SQLException {
        CampusLocation campuslocation = null;

        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = Database.getConnection();

            stmt = connection.prepareStatement(CampusDAOQuery.GET_CAMPUS_BY_ID);
            stmt.setString(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                campuslocation = new CampusLocation();
                campuslocation.setId(rs.getString("id"));
                campuslocation.setCampusname(rs.getString("campusname"));
                campuslocation.setAddress(rs.getString("address"));
                campuslocation.setLongitud(rs.getFloat("longitud"));
                campuslocation.setLatitud(rs.getFloat("latitud"));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
        return campuslocation;
    }

    @Override
    public CampusLocationCollection getCampus(long timestamp, boolean before) throws SQLException {
        return null;
    }

    @Override
    public boolean deleteCampus(String id) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = Database.getConnection();

            stmt = connection.prepareStatement(CampusDAOQuery.DELETE_CAMPUS);
            stmt.setString(1, id);

            int rows = stmt.executeUpdate();
            return (rows == 1);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
    }
}
