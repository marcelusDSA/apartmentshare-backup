package edu.upc.eetac.dsa.apartmentshare;

import edu.upc.eetac.dsa.apartmentshare.dao.*;
import edu.upc.eetac.dsa.apartmentshare.entity.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

/**
 * Created by Jordi on 09/12/2015.
 */
@Path("flat")
public class FlatResource {
    @Context
    private SecurityContext securityContext;
    @POST
    public Response createFlat(@FormParam("campusid") String campusid, @FormParam("address") String address,@FormParam("description") String description,@FormParam("numpartner") int numpartner,@FormParam("smoker") int smoker,@FormParam("pets") int pets,@FormParam("girlorboy") int girlorboy,@FormParam("sqm") int sqm,@FormParam("furnished") int furnished,@FormParam("numrooms") int numrooms,@FormParam("numbathrooms") int numbathrooms,@FormParam("elevator") int elevator,@FormParam("plantnum") int plantnum,@FormParam("internet") int internet,@FormParam("fianza") int fianza,@FormParam("estancia") int estancia, @Context UriInfo uriInfo) throws URISyntaxException {
        if(campusid==null || address == null)
            throw new BadRequestException("campusid and address are mandatory");
        FlatDAO flatDAO = new FlatDAOImpl();
        CampusDAO campusDAO = new CampusDAOImpl();
        CampusLocation campuslocation = null;
        Flat flat = null;
        AuthToken authenticationToken = null;

        try {
            campuslocation = campusDAO.getCampusById(campusid);
            if (campuslocation == null)
                throw new NotFoundException("Campus with id = " + campusid + " doesn't exist");
            flat = flatDAO.createFlat(securityContext.getUserPrincipal().getName(), campusid, address,description, numpartner,  smoker,  pets,  girlorboy,  sqm,  furnished,  numrooms,  numbathrooms,  elevator,  plantnum,  internet,  fianza,  estancia);
        } catch (SQLException e) {
            throw new InternalServerErrorException();
        }
        URI uri = new URI(uriInfo.getAbsolutePath().toString() + "/" + flat.getId());
        return Response.created(uri).type(ApartmentshareMediaType.APARTMENTSHARE_FLAT).entity(flat).build();
    }

    @Path("/{userid}/listflat")
    @GET
    @Produces(ApartmentshareMediaType.APARTMENTSHARE_FLAT_COLLECTION)
    public FlatCollection getFlatsByuserid(@PathParam("userid") String userid, @Context Request request) {
        FlatCollection flatCollection = null;
        FlatDAO flatDAO = new FlatDAOImpl();
        try {

            flatCollection = flatDAO.getFlatsByUserid(userid);
        } catch (SQLException e) {
            throw new InternalServerErrorException();
        }
        return flatCollection;
    }
//    @GET
//    @Produces(ApartmentshareMediaType.APARTMENTSHARE_FLAT_COLLECTION)
//    public FlatCollection getFlats(@QueryParam("timestamp") long timestamp, @DefaultValue("true") @QueryParam("before") boolean before) {
//
//
//        FlatCollection flatCollection = null;
//        FlatDAO flatDAO = new FlatDAOImpl();
//        try {
//            if (before && timestamp == 0) timestamp = System.currentTimeMillis();
//            flatCollection = flatDAO.getFlats(securityContext.getUserPrincipal().getName(),timestamp, before);
//        } catch (SQLException e) {
//            throw new InternalServerErrorException();
//        }
//        return flatCollection;
//      }

//    @Path("/{id}")
//    @GET
//    @Consumes(ApartmentshareMediaType.APARTMENTSHARE_FLAT)
//    @Produces(ApartmentshareMediaType.APARTMENTSHARE_FLAT)
//    public Response updateFlat(@PathParam("id") String id, @Context Request request) {
//        // Create cache-control
//
//        CacheControl cacheControl = new CacheControl();
//        Flat flat = null;
//        FlatDAO flatDAO = new FlatDAOImpl();
//
//        String userid = securityContext.getUserPrincipal().getName();
//
//        try {
//            flat = flatDAO.getFlatById(id);
//            if (flat == null)
//                throw new NotFoundException("Flat with id = " + id + " doesn't exist");
//            if(!userid.equals(flat.getUserid()))
//                throw new ForbiddenException("operation not allowed");
//
//            // Calculate the ETag on last modified date of user resource
//            EntityTag eTag = new EntityTag(Long.toString(flat.getLastModified()));
//
//            // Verify if it matched with etag available in http request
//            Response.ResponseBuilder rb = request.evaluatePreconditions(eTag);
//
//            // If ETag matches the rb will be non-null;
//            // Use the rb to return the response without any further processing
//            if (rb != null) {
//                return rb.cacheControl(cacheControl).tag(eTag).build();
//            }
//
//            // If rb is null then either it is first time request; or resource is
//            // modified
//            // Get the updated representation and return with Etag attached to it
//            rb = Response.ok(flat).cacheControl(cacheControl).tag(eTag);
//            return rb.build();
//        } catch (SQLException e) {
//            throw new InternalServerErrorException();
//        }
//    }
@Path("/{id}")
@GET
@Produces(ApartmentshareMediaType.APARTMENTSHARE_FLAT)
public Flat getFlat(@PathParam("id") String id) {
   Flat flat = null;
    try {
        flat = (new FlatDAOImpl()).getFlatById(id);
    } catch (SQLException e) {
        throw new InternalServerErrorException(e.getMessage());
    }
    if(flat == null)
        throw new NotFoundException("User with id = "+id+" doesn't exist");
    return flat;
}


    @Path("/{id}")
    @PUT
    @Consumes(ApartmentshareMediaType.APARTMENTSHARE_FLAT)
    @Produces(ApartmentshareMediaType.APARTMENTSHARE_FLAT)
    public Flat updateFlat(@PathParam("id") String id, Flat flat) {
        if(flat == null)
            throw new BadRequestException("entity is null");
        if(!id.equals(flat.getId()))
            throw new BadRequestException("path parameter id and entity parameter id doesn't match");

        String userid = securityContext.getUserPrincipal().getName();
        if(!userid.equals(flat.getUserid()))
            throw new ForbiddenException("operation not allowed");

        FlatDAO flatDAO = new FlatDAOImpl();
        try {
            flat = flatDAO.updateFlat(id, flat.getCampusid(), flat.getAddress(),flat.getDescription(),flat.getNumpartner(),flat.getSmoker(),flat.getPets(),flat.getGirlorboy(),flat.getSqm(),flat.getFurnished(),flat.getNumrooms(),flat.getNumbathrooms(),flat.getElevator(),flat.getPlantnum(),flat.getInternet(),flat.getFianza(),flat.getEstancia());
            if(flat == null)
                throw new NotFoundException("Flat with id = "+id+" doesn't exist");
        } catch (SQLException e) {
            throw new InternalServerErrorException();
        }
        return flat;
    }
    @Path("/{id}")
    @DELETE
    public void deleteFlat(@PathParam("id") String id) {
        String userid = securityContext.getUserPrincipal().getName();
        FlatDAO flatDAO = new FlatDAOImpl();
        try {
            String ownerid = flatDAO.getFlatById(id).getUserid();
            if(!userid.equals(ownerid))
                throw new ForbiddenException("operation not allowed");
            if(!flatDAO.deleteFlat(id))
                throw new NotFoundException("Flat with id = "+id+" doesn't exist");
        } catch (SQLException e) {
            throw new InternalServerErrorException();
        }
    }

}
