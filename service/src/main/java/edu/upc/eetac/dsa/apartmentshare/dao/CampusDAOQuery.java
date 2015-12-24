package edu.upc.eetac.dsa.apartmentshare.dao;

/**
 * Created by Jordi on 13/12/2015.
 */
public interface CampusDAOQuery {

    public final static String UUID = "select REPLACE(UUID(),'-','')";
    public final static String CREATE_CAMPUS = "insert into campus_upc (id, campusname,  address,  longitud,latitud) values (UNHEX(?), ?, ?,?,?)";
    public final static String GET_CAMPUS_BY_ID = "select hex(id) as id, campusname, address, longitud, latitud from campus_upc where id=unhex(?)";
    public final static String GET_CAMPUS = "select hex(id) as id, campusname, address, longitud, latitud from campus_upc";
    public final static String DELETE_CAMPUS = "delete from campus_upc where id=unhex(?)";
}
