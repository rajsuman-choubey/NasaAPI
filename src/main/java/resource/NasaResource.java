package resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.ws.rs.core.Response.Status;
import services.MessageService;
import services.Myapp;

@Path("/api")
public class NasaResource {

  private static final Logger logger
      = (Logger) LoggerFactory.getLogger(Myapp.class);

  @Inject
  private MessageService msgService;

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String hello() {
    //Logs every request with SLF4J.
    logger.info(
        "Example log from " + NasaResource.class.getSimpleName() + " class, get method: hello()");
    return "hello";
  }

  @Path("/healthz")
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String getHelloHk2() {
    //Logs request with SLF4J.
    logger.info("Example log from " + NasaResource.class.getSimpleName()
        + " class, get method : gethelloHk2()");
    return msgService.getHello();
  }

  // Earth date to Curiosity sol conversion end point
  @Path("/sol")
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public Response convertCuriositySol(@QueryParam("date") String date) {
    if (date == null) {
      // assign current date
      date = msgService.getTodayDate();
    }
    logger.info("Example log from " + NasaResource.class.getSimpleName()
        + " class, get method : convertCuriositySol()");
    try {
      return Response.
          status(Response.Status.OK)
          .entity(msgService.convertCuriositySol(date))
          .build();
    } catch (Exception e) {
      return Response
          .status(Status.BAD_REQUEST)
          .entity(e.getMessage())
          .build();
    }
  }
}

