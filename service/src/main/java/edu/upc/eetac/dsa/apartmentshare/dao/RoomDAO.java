package edu.upc.eetac.dsa.apartmentshare.dao;

import edu.upc.eetac.dsa.apartmentshare.entity.Room;
import edu.upc.eetac.dsa.apartmentshare.entity.RoomCollection;

import java.sql.SQLException;

/**
 * Created by Jordi on 21/12/2015.
 */
public interface RoomDAO {

    public Room createRoom(String userid, String flatid, String description, int girlorboy, int sqm, int furnished, int status, int price) throws SQLException;
    public Room getRoomById(String id) throws SQLException;
    public RoomCollection getRooms(String flatid,String userid,long timestamp, boolean before) throws SQLException;
    public Room updateRoom(String id,  String flatid, String description, int girlorboy, int sqm, int furnished, int status, int price) throws SQLException;
    public boolean deleteRoom(String id) throws SQLException;

}
