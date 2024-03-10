package practice.julian.microservices.number;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.Instant;

@Path("/api/number")
@Tag(name = "ISBN Number REST Endpoint")
@Slf4j
public class IsbnNumberResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Operation(summary = "Generates book number", description = "ISBN 10 number")
  public IsbnNumber generateISBNNumber() {
    IsbnNumber isbnNumber = new IsbnNumber();
    isbnNumber.setIsbn10(isbnNumber.generateISBN10());
    isbnNumber.setGenerationDate(Instant.now());
    log.info("IsbnNumber generated " + isbnNumber);
    return isbnNumber;
  }
}
