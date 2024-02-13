package org.hprtech.resource;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.hprtech.dto.Account;
import org.hprtech.service.AccountService;
import org.jboss.logging.Logger;

@Path("/")
public class Resource {
    public static final Logger LOG = Logger.getLogger(Resource.class);

    @Inject
    AccountService accountService;

    @POST
    @Path("open_account")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response openAccount(@RequestBody Account account){
        LOG.info("Entering in Response::openAccount()");
        LOG.debug("Response::openAccount() Account "+account);

        boolean alreadyExist = accountService.isAccountAlreadyExist(account);
        LOG.debug("Response::openAccount() alreadyExist "+alreadyExist);

        if(alreadyExist){
            LOG.info("Returning from Response::openAccount()");
            return Response.ok("Oops! Account already Exist").build();
        }
        else{
            LOG.info("Returning from Response::openAccount()");
            return Response.ok("Thanks for opening account...").build();
        }
    }
}
