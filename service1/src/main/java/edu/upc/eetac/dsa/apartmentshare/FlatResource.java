package edu.upc.eetac.dsa.apartmentshare;

import edu.upc.eetac.dsa.apartmentshare.dao.FlatDAO;
import edu.upc.eetac.dsa.apartmentshare.dao.FlatDAOImpl;
import edu.upc.eetac.dsa.apartmentshare.entity.AuthToken;
import edu.upc.eetac.dsa.apartmentshare.entity.Flat;
import edu.upc.eetac.dsa.apartmentshare.entity.FlatCollection;

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
        Flat flat = null;
        AuthToken authenticationToken = null;
        try {
            flat = flatDAO.createFlat(securityContext.getUserPrincipal().getName(), campusid, address,description, numpartner,  smoker,  pets,  girlorboy,  sqm,  furnished,  numrooms,  numbathrooms,  elevator,  plantnum,  internet,  fianza,  estancia);
        } catch (SQLException e) {
            throw new InternalServerErrorException();
        }
        URI uri = new URI(uriInfo.getAbsolutePath().toString() + "/" + flat.getId());
        return Response.created(uri).type(ApartmentshareMediaType.APARTMENTSHARE_FLAT).entity(flat).build();
    }

    @GET
    @Produces(ApartmentshareMediaType.APARTMENTSHARE_FLAT_COLLECTION)
    public FlatCollection getFlats(@QueryParam("timestamp") long timestamp, @DefaultValue("true") @QueryParam("before") boolean before) {
        FlatCollection flatCollection = null;
        FlatDAO flatDAO = new FlatDAOImpl();
        try {
            if (before && timestamp == 0) timestamp = System.currentTimeMillis();
            flatCollection = flatDAO.getFlats(timestamp, before);
        } catch (SQLException e) {
            throw new InternalServerErrorException();
        }
        return flatCollection;
      }
    @Path("/{id}")
    @GET
    @Produces(ApartmentshareMediaType.APARTMENTSHARE_FLAT_COLLECTION)
    public FlatCollection getFlatsByuserid(@PathParam("id") String userid, @Context Request request) {
        FlatCollection flatCollection = null;
        FlatDAO flatDAO = new FlatDAOImpl();
        try {

            flatCollection = flatDAO.getFlatsByUserid(userid);
        } catch (SQLException e) {
            throw new InternalServerErrorException();
        }
        return flatCollection;
    }

//    @Path("/{id}")
//    @GET
//    @Produces(ApartmentshareMediaType.APARTMENTSHARE_FLAT)
//    public Response getFlat(@PathParam("id") String id, @Context Request request) {
//        // Create cache-control
//        CacheControl cacheControl = new CacheControl();
//        Flat flat = null;
//        FlatDAO flatDAO = new FlatDAOImpl();
//        try {
//            flat = flatDAO.getFlatById(id);
//            if (flat == null)
//                throw new NotFoundException("Sting with id = " + id + " doesn't exist");
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



}
