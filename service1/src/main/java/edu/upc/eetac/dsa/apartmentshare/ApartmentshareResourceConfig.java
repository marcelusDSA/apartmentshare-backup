package edu.upc.eetac.dsa.apartmentshare;

import org.glassfish.jersey.linking.DeclarativeLinkingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

/**
 * Created by mazp on 28/11/15.
 */
public class ApartmentshareResourceConfig extends ResourceConfig {

    public ApartmentshareResourceConfig() {
        register(RolesAllowedDynamicFeature.class);
        register(DeclarativeLinkingFeature.class);
        packages("edu.upc.eetac.dsa.apartmentshare");
        packages("edu.upc.eetac.dsa.apartmentshare.auth");
        packages("edu.upc.eetac.dsa.apartmentshare.cors");
    }
}
