package py.com.celeste.banco.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Banco Celeste API",
                version = "v1",
                description = "Documentación OpenAPI para los endpoints de Banco Celeste",
                contact = @Contact(name = "Equipo Banco Celeste"),
                license = @License(name = "Uso interno")
        )
)
public class OpenApiConfig {
}
