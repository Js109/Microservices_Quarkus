package practice.julian.quarkus.microservices.book;
// @formatter:off

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.emptyOrNullString;import static org.hamcrest.Matchers.not;import static org.hamcrest.Matchers.startsWith;
import static org.hamcrest.core.Is.is;

@QuarkusTest
public class BookResourceTest {

  @Test
  public void shouldCreateABook() {
    given()
      .contentType(MediaType.APPLICATION_JSON)
      .body("{\"author\": \"Julian\", \"genre\": \"Java Book about Quarkus\", \"title\": \"Quarkus Microservices\", \"yearOfPublication\": 2024}")
      .when()
      .post("/api/books")
      .then()
      .statusCode(201)
      .body("isbn_10", not(emptyOrNullString()))
      .body("title", is("Quarkus Microservices"))
      .body("author", is("Julian"))
      .body("yearOfPublication", is(2024))
      .body("genre", is("Java Book about Quarkus"))
      .body("creation_date", startsWith("20"));
  }
}
