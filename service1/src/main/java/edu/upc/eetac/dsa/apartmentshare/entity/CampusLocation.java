package edu.upc.eetac.dsa.apartmentshare.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import edu.upc.eetac.dsa.apartmentshare.ApartmentshareRootAPIResource;
import edu.upc.eetac.dsa.apartmentshare.CampusResource;
import edu.upc.eetac.dsa.apartmentshare.LoginResource;
import edu.upc.eetac.dsa.apartmentshare.UserResource;
import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;

import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by mazp on 28/11/15.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CampusLocation {

    @InjectLinks(
            {
                    @InjectLink(resource = ApartmentshareRootAPIResource.class, style = InjectLink.Style.ABSOLUTE, rel = "home", title = "Apartmentsahre Root API"),
                    @InjectLink(resource = CampusResource.class, style = InjectLink.Style.ABSOLUTE, rel = "current-campus", title = "Current campus"),
                    @InjectLink(resource = CampusResource.class, style = InjectLink.Style.ABSOLUTE, rel = "create-campus", title = "Create campus", type = MediaType.APPLICATION_FORM_URLENCODED),
                    @InjectLink(resource = CampusResource.class, method = "getCampus", style = InjectLink.Style.ABSOLUTE, rel = "self campus", title = "Campus", bindings = @Binding(name = "id", value = "${instance.id}")),
                    @InjectLink(resource = LoginResource.class, style = InjectLink.Style.ABSOLUTE, rel = "logout", title = "Logout"),
                    @InjectLink(resource = UserResource.class, method = "getUser", style = InjectLink.Style.ABSOLUTE, rel = "user-profile", title = "User profile", bindings = @Binding(name = "id", value = "${instance.userid}")),
                    @InjectLink(resource = CampusResource.class, method = "getCampus", style = InjectLink.Style.ABSOLUTE, rel = "next", title = "Newer flats", bindings = {@Binding(name = "timestamp", value = "${instance.creationTimestamp}"), @Binding(name = "before", value = "false")}),
                    @InjectLink(resource = CampusResource.class, method = "getCampus", style = InjectLink.Style.ABSOLUTE, rel = "previous", title = "Older flats", bindings = {@Binding(name = "timestamp", value = "${instance.creationTimestamp}"), @Binding(name = "before", value = "true")}),
            }
    )
    private List<Link> links;
    private String id;
    private String campusname;
    private String address;
    private float longitud;
    private float latitud;




    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCampusname() {
        return campusname;
    }

    public void setCampusname(String campusname) {
        this.campusname = campusname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getLongitud() {
        return longitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }

    public float getLatitud() {
        return latitud;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }
}
