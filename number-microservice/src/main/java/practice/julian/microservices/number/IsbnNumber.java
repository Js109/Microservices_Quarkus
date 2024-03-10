package practice.julian.microservices.number;

import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import java.time.Instant;
import java.util.Random;

@Schema(description = "ISBN10 number for books")
@Data
public class IsbnNumber {

  @Schema(required = true)
  @JsonbProperty("isbn_10")
  private String isbn10;

  @JsonbTransient
  private Instant generationDate;

  /*
    The given algorithm randomly generates nine digits and then calculates the check digit
    by multiplying the products of the digits by the weights 10, 9, 8, ..., 2, 1 and dividing the sum of these products by 11.
    The check digit is then calculated as the difference between 11 and the remainder.
    The generated digits are then converted into the ISBN-10 format "XXX-X-XXXXX-XX-X" by inserting hyphens in the appropriate places.
     */
  public String generateISBN10() {
    Random random = new Random();
    int[] digits = new int[10];
    digits[0] = random.nextInt(2); // First digit is 0 or 1
    digits[1] = random.nextInt(10);
    for (int i = 2; i < 8; i++) {
      digits[i] = random.nextInt(10);
    }
    int sum = 0;
    for (int i = 0; i < 9; i++) {
      sum += digits[i] * (10 - i);
    }
    int checkDigit = (11 - (sum % 11)) % 11;
    digits[9] = checkDigit;
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < digits.length; i++) {
      if (i == 1 || i == 4 || i == 6) {
        sb.append("-");
      }
      sb.append(digits[i]);
    }
    return sb.toString();
  }
}
