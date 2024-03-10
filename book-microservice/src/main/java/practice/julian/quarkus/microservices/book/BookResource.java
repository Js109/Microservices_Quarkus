package practice.julian.quarkus.microservices.book;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Instant;

@Path("/api/books")
@Tag(name = "Book REST Endpoint")
@Slf4j
public class BookResource {

  @RestClient
  NumberProxy proxy;

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Operation(summary = "Creates a book", description = "Creates a Book with an ISBN number")
  @Retry(maxRetries = 4, delay = 4000)
  @Fallback(fallbackMethod = "fallingbackOnCreatingABook")
  public Response createABook(@RequestBody Book book) {

    try {
      book.setIsbn10(proxy.generateISBNNumbers().isbn10);
      book.setCreationDate(Instant.now());
      log.info("Book created : " + book);
      return Response.status(201).entity(book).build();
    } catch (WebApplicationException ex) {
      log.debug(ex.getMessage());
      return Response.status(Response.Status.BAD_REQUEST).entity("Failed to create book").build();
    } catch (Exception ex) {
      log.error(ex.getMessage());
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to create book").build();
    }
  }

  public Response fallingbackOnCreatingABook(Book book) throws FileNotFoundException {

    book.setIsbn10("Will be set later");
    book.setCreationDate(Instant.now());
    saveBookOnDisk(book);
    log.warn("Book saved on disk : " + book);
    return Response.status(206).entity(book).build();
  }

  private void saveBookOnDisk(Book book) throws FileNotFoundException {
    String bookJson = JsonbBuilder.create().toJson(book);
    try (PrintWriter out = new PrintWriter("book-" + Instant.now().toEpochMilli() + ".json")) {
      out.println(bookJson);
    }
  }
}

