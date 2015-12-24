package edu.upc.eetac.dsa.apartmentshare.client.entity;

import java.util.List;

import javax.ws.rs.core.*;

/**
 * Created by mazp on 20/12/15.
 */
public class Flat {

   private List<Link> links;
    private String  id;

    private String userid;
    private String campusid;
    private int  numpartner;
    private  int smoker;
    private int pets;
    private int  girlorboy;
    private  int sqm;
    private int furnished;
    private int numrooms;
    private  int numbathrooms;
    private int  elevator;
    private int plantnum;
    private  int internet;
    private  int fianza;
    private int  estancia ;
    private  String  address;
    private String description;
    private long creationTimestamp;
    private long lastModified;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCampusid() {
        return campusid;
    }

    public void setCampusid(String campusid) {
        this.campusid = campusid;
    }

    public int getNumpartner() {
        return numpartner;
    }

    public void setNumpartner(int numpartner) {
        this.numpartner = numpartner;
    }

    public int getSmoker() {
        return smoker;
    }

    public void setSmoker(int smoker) {
        this.smoker = smoker;
    }

    public int getPets() {
        return pets;
    }

    public void setPets(int pets) {
        this.pets = pets;
    }

    public int getGirlorboy() {
        return girlorboy;
    }

    public void setGirlorboy(int girlorboy) {
        this.girlorboy = girlorboy;
    }

    public int getSqm() {
        return sqm;
    }

    public void setSqm(int sqm) {
        this.sqm = sqm;
    }

    public int getFurnished() {
        return furnished;
    }

    public void setFurnished(int furnished) {
        this.furnished = furnished;
    }

    public int getNumrooms() {
        return numrooms;
    }

    public void setNumrooms(int numrooms) {
        this.numrooms = numrooms;
    }

    public int getElevator() {
        return elevator;
    }

    public void setElevator(int elevator) {
        this.elevator = elevator;
    }

    public int getPlantnum() {
        return plantnum;
    }

    public void setPlantnum(int plantnum) {
        this.plantnum = plantnum;
    }

    public int getInternet() {
        return internet;
    }

    public void setInternet(int internet) {
        this.internet = internet;
    }

    public int getFianza() {
        return fianza;
    }

    public void setFianza(int fianza) {
        this.fianza = fianza;
    }

    public int getEstancia() {
        return estancia;
    }

    public void setEstancia(int estancia) {
        this.estancia = estancia;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(long creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }

    public int getNumbathrooms() {
        return numbathrooms;
    }

    public void setNumbathrooms(int numbathrooms) {
        this.numbathrooms = numbathrooms;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}
