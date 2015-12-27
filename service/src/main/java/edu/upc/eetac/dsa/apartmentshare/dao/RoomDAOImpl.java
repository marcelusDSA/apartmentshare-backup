package edu.upc.eetac.dsa.apartmentshare.dao;

import edu.upc.eetac.dsa.apartmentshare.entity.Room;
import edu.upc.eetac.dsa.apartmentshare.entity.RoomCollection;

import java.sql.*;

/**
 * Created by Jordi on 21/12/2015.
 */
public class RoomDAOImpl implements RoomDAO {
    @Override
    public Room createRoom(String userid, String flatid, String description,int girlorboy, int sqm, int furnished, int status, int price) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        String id = null;
        try {
            connection = Database.getConnection();

            stmt = connection.prepareStatement(RoomDAOQuery.UUID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                id = rs.getString(1);
            else
                throw new SQLException();

            stmt = connection.prepareStatement(RoomDAOQuery.CREATE_ROOM);
            stmt.setString(1, id);
            stmt.setString(2, userid);
            stmt.setString(3, flatid);
            stmt.setString(4, description);
            stmt.setInt(5,girlorboy);
            stmt.setInt(6,sqm);
            stmt.setInt(7,furnished);
            stmt.setInt(8,status);
            stmt.setInt(9, price);
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
        return getRoomById(id);
    }

    @Override
    public Room getRoomById(String id) throws SQLException {
        Room room = null;

        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = Database.getConnection();

            stmt = connection.prepareStatement(RoomDAOQuery.GET_ROOM_BY_ID);
            stmt.setString(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                room = new Room();
                room.setId(rs.getString("id"));
                room.setUserid(rs.getString("userid"));
                room.setFlatid(rs.getString("flatid"));
                room.setDescription(rs.getString("description"));
                room.setGirlorboy(rs.getInt("girlorboy"));
                room.setSqm(rs.getInt("sqm"));
                room.setFurnished(rs.getInt("furnished"));
                room.setStatus(rs.getInt("status"));
                room.setPrice(rs.getInt("price"));
                room.setCreationTimestamp(rs.getTimestamp("creation_timestamp").getTime());
                room.setLastModified(rs.getTimestamp("last_modified").getTime());
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
        return room;
    }

    @Override
    public RoomCollection getRooms(String flatid, String userid,long timestamp, boolean before) throws SQLException {
        RoomCollection flatCollection = new RoomCollection();

        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = Database.getConnection();

            if(before)
                stmt = connection.prepareStatement(RoomDAOQuery.GET_ROOMS);
            else
                stmt = connection.prepareStatement(RoomDAOQuery.GET_ROOMS_AFTER);
            stmt.setTimestamp(1, new Timestamp(timestamp));
            stmt.setString(2, userid);
            stmt.setString(3, flatid);

            ResultSet rs = stmt.executeQuery();
            boolean first = true;
            while (rs.next()) {
                Room room = new Room();
                room.setId(rs.getString("id"));
                room.setUserid(rs.getString("userid"));
                room.setFlatid(rs.getString("flatid"));
                room.setDescription(rs.getString("description"));
                room.setGirlorboy(rs.getInt("girlorboy"));
                room.setSqm(rs.getInt("sqm"));
                room.setFurnished(rs.getInt("furnished"));
                room.setStatus(rs.getInt("status"));
                room.setPrice(rs.getInt("price"));
                room.setCreationTimestamp(rs.getTimestamp("creation_timestamp").getTime());
                room.setLastModified(rs.getTimestamp("last_modified").getTime());
                if (first) {
                    flatCollection.setNewestTimestamp(room.getLastModified());
                    first = false;
                }
                flatCollection.setOldestTimestamp(room.getLastModified());
                flatCollection.getRooms().add(room);
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
    public Room updateRoom(String id, String flatid,String description,int girlorboy, int sqm, int furnished, int status, int price) throws SQLException {
        Room room = null;

        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = Database.getConnection();

            stmt = connection.prepareStatement(RoomDAOQuery.UPDATE_ROOM);
            stmt.setString(1, flatid);
            stmt.setString(2, description);
            stmt.setInt(3,girlorboy);
            stmt.setInt(4,sqm);
            stmt.setInt(5,furnished);
            stmt.setInt(6,status);
            stmt.setInt(7, price);
            stmt.setString(8, id);

            int rows = stmt.executeUpdate();
            if (rows == 1)
                room = getRoomById(id);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }

        return room;
    }

    @Override
    public boolean deleteRoom(String id) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = Database.getConnection();

            stmt = connection.prepareStatement(RoomDAOQuery.DELETE_ROOM);
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
