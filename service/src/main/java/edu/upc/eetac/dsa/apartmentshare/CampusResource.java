package edu.upc.eetac.dsa.apartmentshare;

import edu.upc.eetac.dsa.apartmentshare.dao.CampusDAO;
import edu.upc.eetac.dsa.apartmentshare.dao.CampusDAOImpl;
import edu.upc.eetac.dsa.apartmentshare.entity.AuthToken;
import edu.upc.eetac.dsa.apartmentshare.entity.CampusLocation;


import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

/**
 * Created by Jordi on 13/12/2015.
 */
@Path("campus")
public class CampusResource {
    @Context
    private SecurityContext securityContext;
    @POST
    @RolesAllowed("administrator")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(ApartmentshareMediaType.APARTMENTSHARE_AUTH_TOKEN)
    public Response createCampus(@FormParam("campusname") String campusname, @FormParam("address") String address,@FormParam("longitud") float longitud,@FormParam("latitud") float latitud, @Context UriInfo uriInfo) throws URISyntaxException {
        if(campusname==null || address == null)
            throw new BadRequestException("campusname and address are mandatory");
        CampusDAO campusDAO = new CampusDAOImpl();
        CampusLocation campuslocation = null;
        AuthToken authenticationToken = null;
        try {
            campuslocation = campusDAO.createCampus(securityContext.getUserPrincipal().getName(),campusname, address,longitud, latitud);
        } catch (SQLException e) {
            throw new InternalServerErrorException();
        }
        URI uri = new URI(uriInfo.getAbsolutePath().toString() + "/" + campuslocation.getId());
        return Response.created(uri).type(ApartmentshareMediaType.APARTMENTSHARE_CAMPUS).entity(campuslocation).build();
    }
}
