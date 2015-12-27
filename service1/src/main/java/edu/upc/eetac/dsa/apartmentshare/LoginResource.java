package edu.upc.eetac.dsa.apartmentshare;

import edu.upc.eetac.dsa.apartmentshare.dao.AuthTokenDAO;
import edu.upc.eetac.dsa.apartmentshare.dao.AuthTokenDAOImpl;
import edu.upc.eetac.dsa.apartmentshare.dao.UserDAO;
import edu.upc.eetac.dsa.apartmentshare.dao.UserDAOImpl;
import edu.upc.eetac.dsa.apartmentshare.entity.AuthToken;
import edu.upc.eetac.dsa.apartmentshare.entity.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.sql.SQLException;

/**
 * Created by mazp on 1/12/15.
 */

//En el paquete edu.upc.eetac.dsa.beeter crearemos una clase recurso raíz denominada LoginResource con URI relativa
// login y en la cual implementaremos las funciones de login y logout. Básicamente, un login consiste
// en la creación de un nuevo token de acceso al usuario que se identifica con su identificador de login
// y su contraseña. El logout será la eliminación del token que tiene asignado el usuario autenticado
// que realiza la petición.


@Path("login")
public class LoginResource {

        @Context
        SecurityContext securityContext;

        @POST
        @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
        @Produces(ApartmentshareMediaType.APARTMENTSHARE_AUTH_TOKEN)
        public AuthToken login(@FormParam("login") String loginid, @FormParam("password") String password) {
            if(loginid == null || password == null)
                throw new BadRequestException("all parameters are mandatory");

            User user = null;
            AuthToken authToken = null;
            try{
                UserDAO userDAO = new UserDAOImpl();
                user = userDAO.getUserByLoginid(loginid);
                if(user == null)
                    throw new BadRequestException("loginid " + loginid + " not found.");
                if(!userDAO.checkPassword(user.getId(), password))
                    throw new BadRequestException("incorrect password");

                AuthTokenDAO authTokenDAO = new AuthTokenDAOImpl();
                authTokenDAO.deleteToken(user.getId());
                authToken = authTokenDAO.createAuthToken(user.getId());
            }catch(SQLException e){
                throw new InternalServerErrorException();
            }
            return authToken;
        }

        @DELETE
        public void logout(){
            String userid = securityContext.getUserPrincipal().getName();
            AuthTokenDAO authTokenDAO = new AuthTokenDAOImpl();
            try {
                authTokenDAO.deleteToken(userid);
            } catch (SQLException e) {
                throw new InternalServerErrorException();
            }
        }
    }
