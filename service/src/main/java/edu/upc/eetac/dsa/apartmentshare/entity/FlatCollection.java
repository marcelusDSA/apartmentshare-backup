package edu.upc.eetac.dsa.apartmentshare.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import edu.upc.eetac.dsa.apartmentshare.ApartmentshareRootAPIResource;
import edu.upc.eetac.dsa.apartmentshare.LoginResource;
import edu.upc.eetac.dsa.apartmentshare.FlatResource;
import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;

import javax.ws.rs.core.Link;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mazp on 28/11/15.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlatCollection {
    @InjectLinks({
            @InjectLink(resource = ApartmentshareRootAPIResource.class, style = InjectLink.Style.ABSOLUTE, rel = "home", title = "Apartmentshare Root API"),
            @InjectLink(resource = FlatResource.class, style = InjectLink.Style.ABSOLUTE, rel = "current-flat", title = "Current flat"),
            @InjectLink(resource = FlatResource.class, method = "getFlats", style = InjectLink.Style.ABSOLUTE, rel = "next", title = "Newer Flats", bindings = {@Binding(name = "timestamp", value = "${instance.newestTimestamp}"), @Binding(name = "before", value = "false")}),
            @InjectLink(resource =FlatResource.class, method = "getFlats", style = InjectLink.Style.ABSOLUTE, rel = "previous", title = "Older Flats", bindings = {@Binding(name = "timestamp", value = "${instance.oldestTimestamp}"), @Binding(name = "before", value = "true")}),
            @InjectLink(resource = LoginResource.class, style = InjectLink.Style.ABSOLUTE, rel = "logout", title = "Logout")
    })
    private List<Link> links;
    private long newestTimestamp;
    private long oldestTimestamp;
    private List<Flat> flats = new ArrayList<>();

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

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
}
