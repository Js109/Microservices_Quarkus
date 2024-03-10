package practice.julian.microservices.number;
// @formatter:off

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.not;

@QuarkusTest
public class IsbnNumberResourceTest {

  @Test
  public void shouldGenerateIsbnNumber() {
    given().
    when()
      .get("/api/number").
    then()
      .statusCode(200)
      .body("isbn_10", not(emptyOrNullString()))
      // test if there is no key called generationDate
      .body(not(hasKey("generationDate")));
  }
}
