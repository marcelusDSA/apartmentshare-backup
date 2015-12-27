package edu.upc.eetac.dsa.apartmentshare.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import edu.upc.eetac.dsa.apartmentshare.ApartmentshareRootAPIResource;
import edu.upc.eetac.dsa.apartmentshare.CampusResource;
import edu.upc.eetac.dsa.apartmentshare.LoginResource;
import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;

import javax.ws.rs.core.Link;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Jordi on 13/12/2015.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CampusLocationCollection {
    @InjectLinks({
            @InjectLink(resource = ApartmentshareRootAPIResource.class, style = InjectLink.Style.ABSOLUTE, rel = "home", title = "Apartmentshare Root API"),
            @InjectLink(resource = CampusResource.class, style = InjectLink.Style.ABSOLUTE, rel = "current-campus", title = "Current campus"),
            @InjectLink(resource = CampusResource.class, style = InjectLink.Style.ABSOLUTE, rel = "current-campus", title = "Current campus"),
            @InjectLink(resource = CampusResource.class, method = "getCampus", style = InjectLink.Style.ABSOLUTE, rel = "next", title = "Newer campus", bindings = {@Binding(name = "timestamp", value = "${instance.newestTimestamp}"), @Binding(name = "before", value = "false")}),
            @InjectLink(resource =CampusResource.class, method = "getCampus", style = InjectLink.Style.ABSOLUTE, rel = "previous", title = "Older campus", bindings = {@Binding(name = "timestamp", value = "${instance.oldestTimestamp}"), @Binding(name = "before", value = "true")}),
            @InjectLink(resource = LoginResource.class, style = InjectLink.Style.ABSOLUTE, rel = "logout", title = "Logout")
    })
    private List<Link> links;
    private List<CampusLocation> campus = new ArrayList<>();

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public List<CampusLocation> getCampus() {
        return campus;
    }

    public void setCampus(List<CampusLocation> campus) {
        this.campus = campus;
    }

}
