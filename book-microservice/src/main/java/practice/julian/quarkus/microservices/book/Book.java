package practice.julian.quarkus.microservices.book;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbProperty;
import java.time.Instant;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.NotNull;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "This is a book")
public class Book {

  @JsonbProperty("isbn_10")
  @NotNull
  @Schema(required = true)
  private String isbn10;

  @Schema(required = true)
  private String title;

  private String author;

  private int yearOfPublication;

  private String genre;

  @JsonbDateFormat("yyyy-MM-dd")
  @JsonbProperty("creation_date")
  @Schema(implementation = String.class)
  private Instant creationDate;
}
