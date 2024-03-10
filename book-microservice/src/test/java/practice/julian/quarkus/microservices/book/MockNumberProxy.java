package practice.julian.quarkus.microservices.book;

import io.quarkus.test.Mock;
import org.eclipse.microprofile.rest.client.inject.RestClient;

/*
This test is used to have positive build results if the number microservice is not up and running
 */
@Mock
@RestClient
public class MockNumberProxy implements NumberProxy {
  @Override
  public IsbnTen generateISBNNumbers() {
    IsbnTen isbnTen = new IsbnTen();
    isbnTen.isbn10 = "10-mock";
    return isbnTen;
  }
}

