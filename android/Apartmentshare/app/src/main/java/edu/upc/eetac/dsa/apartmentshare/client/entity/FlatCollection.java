package edu.upc.eetac.dsa.apartmentshare.client.entity;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.*;

/**
 * Created by mazp on 20/12/15.
 */
public class FlatCollection {

    private List<Link> links;
    private long newestTimestamp;
    private long oldestTimestamp;
    private List<Flat> flats = new ArrayList<>();



    public long getNewestTimestamp() {
        return newestTimestamp;
    }

    public void setNewestTimestamp(long newestTimestamp) {
        this.newestTimestamp = newestTimestamp;
    }

    public long getOldestTimestamp() {
        return oldestTimestamp;
    }

    public void setOldestTimestamp(long oldestTimestamp) {
        this.oldestTimestamp = oldestTimestamp;
    }

    public List<Flat> getFlats() {
        return flats;
    }

    public void setFlats(List<Flat> flats) {
        this.flats = flats;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}

