package edu.upc.eetac.dsa.apartmentshare;

import edu.upc.eetac.dsa.apartmentshare.entity.ApartmentshareRootAPI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;


/**
 * Created by Jordi on 09/12/2015.
 */

@Path("/")
public class ApartmentshareRootAPIResource {

    @Context
    private SecurityContext securityContext;

    private String userid;

    @GET
    @Produces(ApartmentshareMediaType.APARTMENTSHARE_ROOT)
    public ApartmentshareRootAPI getRootAPI() {
        if(securityContext.getUserPrincipal()!=null)
            userid = securityContext.getUserPrincipal().getName();
        ApartmentshareRootAPI apartmentshareRootAPI = new ApartmentshareRootAPI();

        return apartmentshareRootAPI;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
