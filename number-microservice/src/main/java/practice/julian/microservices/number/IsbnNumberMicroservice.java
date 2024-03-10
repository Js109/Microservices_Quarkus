package practice.julian.microservices.number;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/")
@OpenAPIDefinition(
  info = @Info(title = "Number Microservice for demonstration purposes",
    description = "This microservice generates ISBN book number",
    version = "1.0"),
  tags = {
    @Tag(name = "api", description = "Public API that can be used by anybody"),
    @Tag(name = "number", description = "Public API for random ISBN generation")
  }
)
public class IsbnNumberMicroservice extends Application {
}
