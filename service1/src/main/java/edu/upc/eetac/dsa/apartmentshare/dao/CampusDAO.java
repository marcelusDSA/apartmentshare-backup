package edu.upc.eetac.dsa.apartmentshare.dao;

import edu.upc.eetac.dsa.apartmentshare.entity.CampusLocation;
import edu.upc.eetac.dsa.apartmentshare.entity.CampusLocationCollection;

import java.sql.SQLException;

/**
 * Created by Jordi on 13/12/2015.
 */
public interface CampusDAO {

    public CampusLocation createCampus(String id,String campusname, String address,float longitud,float latitud) throws SQLException;
    public CampusLocation getCampusById(String id) throws SQLException;
    public CampusLocationCollection getCampus(long timestamp, boolean before) throws SQLException;
    public boolean deleteCampus(String id) throws SQLException;

}
