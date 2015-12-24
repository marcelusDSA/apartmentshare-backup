package edu.upc.eetac.dsa.apartmentshare.entity;

import edu.upc.eetac.dsa.apartmentshare.*;
import edu.upc.eetac.dsa.apartmentshare.ApartmentshareMediaType;
import edu.upc.eetac.dsa.apartmentshare.ApartmentshareRootAPIResource;
import edu.upc.eetac.dsa.apartmentshare.LoginResource;
import edu.upc.eetac.dsa.apartmentshare.UserResource;
import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;

import javax.ws.rs.core.Link;
import java.util.List;

/**
 * Created by mazp on 1/12/15.
 */
public class ApartmentshareRootAPI {
    @InjectLinks({
            @InjectLink(resource = ApartmentshareRootAPIResource.class, style = InjectLink.Style.ABSOLUTE, rel = "self bookmark home", title = "Apartmentsahre Root API"),
            @InjectLink(resource = LoginResource.class, style = InjectLink.Style.ABSOLUTE, rel = "login", title = "Login", type = ApartmentshareMediaType.APARTMENTSHARE_AUTH_TOKEN),
            @InjectLink(resource = UserResource.class, style = InjectLink.Style.ABSOLUTE, rel = "create-user", title = "Register", type = ApartmentshareMediaType.APARTMENTSHARE_AUTH_TOKEN),
            @InjectLink(resource = LoginResource.class, style = InjectLink.Style.ABSOLUTE, rel = "logout", title = "Logout", condition = "${!empty resource.userid}"),
            @InjectLink(resource = UserResource.class, method = "getUser", style = InjectLink.Style.ABSOLUTE, rel = "user-profile", title = "User profile", condition = "${!empty resource.userid}", type = ApartmentshareMediaType.APARTMENTSHARE_USER, bindings = @Binding(name = "id", value = "${resource.userid}")),
            @InjectLink(resource = FlatResource.class, style = InjectLink.Style.ABSOLUTE, rel = "create-flat", title = "Create flat", condition="${!empty resource.userid}", type=ApartmentshareMediaType.APARTMENTSHARE_FLAT),
            @InjectLink(resource = FlatResource.class, style = InjectLink.Style.ABSOLUTE, rel = "current-flat", title = "Current flat", type= ApartmentshareMediaType.APARTMENTSHARE_FLAT_COLLECTION),
            @InjectLink(resource = CampusResource.class, style = InjectLink.Style.ABSOLUTE, rel = "create-campus", title = "Create campus", condition="${!empty resource.userid}", type=ApartmentshareMediaType.APARTMENTSHARE_CAMPUS),
            @InjectLink(resource = CampusResource.class, style = InjectLink.Style.ABSOLUTE, rel = "current-campus", title = "Current campus", type= ApartmentshareMediaType.APARTMENTSHARE_CAMPUS_COLLECTION),


    })


    private List<Link> links;

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}
