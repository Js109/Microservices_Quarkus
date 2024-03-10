package practice.julian.quarkus.microservices.book;

import javax.json.bind.annotation.JsonbProperty;

public class IsbnTen {

  @JsonbProperty("isbn_10")
  public String isbn10;
}
