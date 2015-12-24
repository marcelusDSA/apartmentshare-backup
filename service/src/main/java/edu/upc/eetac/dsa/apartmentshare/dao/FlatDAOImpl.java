package edu.upc.eetac.dsa.apartmentshare.dao;

import edu.upc.eetac.dsa.apartmentshare.entity.Flat;
import edu.upc.eetac.dsa.apartmentshare.entity.FlatCollection;

import java.sql.*;

/**
 * Created by Jordi on 09/12/2015.
 */
public class FlatDAOImpl implements FlatDAO {
    @Override
    public Flat createFlat(String userid, String campusid, String address,String description,int numpartner, int smoker, int pets, int girlorboy, int sqm, int furnished, int numrooms, int numbathrooms, int elevator, int plantnum, int internet, int fianza, int estancia) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        String id = null;
        try {
            connection = Database.getConnection();

            stmt = connection.prepareStatement(FlatDAOQuery.UUID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                id = rs.getString(1);
            else
                throw new SQLException();

            stmt = connection.prepareStatement(FlatDAOQuery.CREATE_FLAT);
            stmt.setString(1, id);
            stmt.setString(2, userid);
            stmt.setString(3, campusid);
            stmt.setString(4, address);
            stmt.setString(5, description);
            stmt.setInt(6, numpartner);
            stmt.setInt(7,smoker);
            stmt.setInt(8,pets);
            stmt.setInt(9,girlorboy);
            stmt.setInt(10,sqm);
            stmt.setInt(11,furnished);
            stmt.setInt(12,numrooms);
            stmt.setInt(13, numbathrooms);
            stmt.setInt(14,elevator);
            stmt.setInt(15,plantnum);
            stmt.setInt(16,internet);
            stmt.setInt(17,fianza);
            stmt.setInt(18,estancia);
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
        return getFlatById(id);
    }

    @Override
    public Flat getFlatById(String id) throws SQLException {
        Flat flat = null;

        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = Database.getConnection();

            stmt = connection.prepareStatement(FlatDAOQuery.GET_FLAT_BY_ID);
            stmt.setString(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                flat = new Flat();
                flat.setId(rs.getString("id"));
                flat.setUserid(rs.getString("userid"));
                flat.setCampusid(rs.getString("campusid"));
                flat.setAddress(rs.getString("address"));
                flat.setDescription(rs.getString("description"));
                flat.setNumpartner(rs.getInt("numpartner"));
                flat.setSmoker(rs.getInt("smoker"));
                flat.setPets(rs.getInt("pets"));
                flat.setGirlorboy(rs.getInt("girlorboy"));
                flat.setSqm(rs.getInt("sqm"));
                flat.setFurnished(rs.getInt("furnished"));
                flat.setNumrooms(rs.getInt("numrooms"));
                flat.setNumbathrooms(rs.getInt("numbathrooms"));
                flat.setElevator(rs.getInt("elevator"));
                flat.setPlantnum(rs.getInt("plantnum"));
                flat.setInternet(rs.getInt("internet"));
                flat.setFianza(rs.getInt("fianza"));
                flat.setEstancia(rs.getInt("estancia"));
                flat.setCreationTimestamp(rs.getTimestamp("creation_timestamp").getTime());
                flat.setLastModified(rs.getTimestamp("last_modified").getTime());
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
        return flat;
    }

    @Override
    public FlatCollection getFlats(long timestamp, boolean before) throws SQLException {
        FlatCollection flatCollection = new FlatCollection();

        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = Database.getConnection();

            if(before)
                stmt = connection.prepareStatement(FlatDAOQuery.GET_FLATS);
            else
                stmt = connection.prepareStatement(FlatDAOQuery.GET_FLATS_AFTER);
            stmt.setTimestamp(1, new Timestamp(timestamp));

            ResultSet rs = stmt.executeQuery();
            boolean first = true;
            while (rs.next()) {
                Flat flat = new Flat();
                flat.setId(rs.getString("id"));
                flat.setUserid(rs.getString("userid"));
                flat.setCampusid(rs.getString("campusid"));
                flat.setAddress(rs.getString("address"));
                flat.setDescription(rs.getString("description"));
                flat.setNumpartner(rs.getInt("numpartner"));
                flat.setSmoker(rs.getInt("smoker"));
                flat.setPets(rs.getInt("pets"));
                flat.setGirlorboy(rs.getInt("girlorboy"));
                flat.setSqm(rs.getInt("sqm"));
//                flat.setFurnished(rs.getInt("furnished"));
//                flat.setNumrooms(rs.getInt("numrooms"));
//                flat.setNumbathrooms(rs.getInt("numbathrooms"));
//                flat.setElevator(rs.getInt("elevator"));
//                flat.setPlantnum(rs.getInt("plantnum"));
//                flat.setInternet(rs.getInt("internet"));
//                flat.setFianza(rs.getInt("fianza"));
//                flat.setEstancia(rs.getInt("estancia"));
                flat.setCreationTimestamp(rs.getTimestamp("creation_timestamp").getTime());
                flat.setLastModified(rs.getTimestamp("last_modified").getTime());
                if (first) {
                    flatCollection.setNewestTimestamp(flat.getLastModified());
                    first = false;
                }
                flatCollection.setOldestTimestamp(flat.getLastModified());
                flatCollection.getFlats().add(flat);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
        return flatCollection;
    }

    @Override
    public Flat updateFlat(String id, String campusid, String address,String description,int numpartner, int smoker, int pets, int girlorboy, int sqm, int furnished, int numrooms, int numbathrooms, int elevator, int plantnum, int internet, int fianza, int estancia) throws SQLException {
        Flat flat = null;

        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = Database.getConnection();

            stmt = connection.prepareStatement(FlatDAOQuery.UPDATE_FLAT);
            stmt.setString(1, campusid);
            stmt.setString(2, address);
            stmt.setString(3, description);
            stmt.setInt(4, numpartner);
            stmt.setInt(5,smoker);
            stmt.setInt(6,pets);
            stmt.setInt(7,girlorboy);
            stmt.setInt(8,sqm);
            stmt.setInt(9,furnished);
            stmt.setInt(10,numrooms);
            stmt.setInt(11, numbathrooms);
            stmt.setInt(12,elevator);
            stmt.setInt(13,plantnum);
            stmt.setInt(13,internet);
            stmt.setInt(14,fianza);
            stmt.setInt(15, estancia);
            stmt.setString(16, id);

            int rows = stmt.executeUpdate();
            if (rows == 1)
                flat = getFlatById(id);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }

        return flat;
    }

    @Override
    public boolean deleteFlat(String id) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = Database.getConnection();

            stmt = connection.prepareStatement(FlatDAOQuery.DELETE_FLAT);
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

    @Override
    public FlatCollection getFlatsByUserid(String userid) throws SQLException {
        FlatCollection flatCollection = new FlatCollection();

        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = Database.getConnection();


            stmt = connection.prepareStatement(FlatDAOQuery.GET_FLATS_BY_ID);
            stmt.setString(1, userid);

            ResultSet rs = stmt.executeQuery();
            boolean first = true;
            while (rs.next()) {
                Flat flat = new Flat();
                flat.setId(rs.getString("id"));
                flat.setUserid(rs.getString("userid"));
                flat.setCampusid(rs.getString("campusid"));
                flat.setAddress(rs.getString("address"));
                flat.setDescription(rs.getString("description"));
                flat.setNumpartner(rs.getInt("numpartner"));
                flat.setSmoker(rs.getInt("smoker"));
                flat.setPets(rs.getInt("pets"));
                flat.setGirlorboy(rs.getInt("girlorboy"));
                flat.setSqm(rs.getInt("sqm"));
//                flat.setFurnished(rs.getInt("furnished"));
//                flat.setNumrooms(rs.getInt("numrooms"));
//                flat.setNumbathrooms(rs.getInt("numbathrooms"));
//                flat.setElevator(rs.getInt("elevator"));
//                flat.setPlantnum(rs.getInt("plantnum"));
//                flat.setInternet(rs.getInt("internet"));
//                flat.setFianza(rs.getInt("fianza"));
//                flat.setEstancia(rs.getInt("estancia"));
                flat.setCreationTimestamp(rs.getTimestamp("creation_timestamp").getTime());
                flat.setLastModified(rs.getTimestamp("last_modified").getTime());
                if (first) {
                    flatCollection.setNewestTimestamp(flat.getLastModified());
                    first = false;
                }
                flatCollection.setOldestTimestamp(flat.getLastModified());
                flatCollection.getFlats().add(flat);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
        return flatCollection;
    }
}
