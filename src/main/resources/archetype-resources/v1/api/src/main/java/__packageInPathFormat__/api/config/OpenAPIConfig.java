package ${package}.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
            .addServersItem(new Server().url("http://localhost:8080/v1").description("Local DEV"))
            .info(new Info().title("Service API").version("1.0.0"));
    }

    @Bean
    public OperationCustomizer customizeOpenAPIOperations() {
        return (operation, handlerMethod) -> {
            // 1. Handle parameters
            Optional.ofNullable(operation.getParameters()).ifPresent(params -> {
                boolean removedRestParams = params.removeIf(this::shouldRemoveParam);
                if (removedRestParams) {
                    addRestParams(params);
                }
            });

            // 2. Handle content type
            Optional.ofNullable(operation.getResponses()).ifPresent(responses -> {
                responses.forEach((status, response) -> {
                    Content content = response.getContent();
                    if (content != null && content.containsKey("*/*")) {
                        MediaType mediaType = content.get("*/*");
                        content.put("application/json", mediaType);
                        content.remove("*/*");
                    }
                });
            });

            return operation;
        };
    }

    private boolean shouldRemoveParam(Parameter parameter) {
        if (parameter != null && parameter.getSchema() != null && parameter.getSchema().get$ref() != null) {
            return "#/components/schemas/RestParams".equals(parameter.getSchema().get$ref());
        }
        return false;
    }

    private void addRestParams(List<Parameter> parameters) {
        final String QUERY = "query";

        Schema<?> integerSchema = new Schema<>().type("integer");
        Schema<?> stringSchema = new Schema<>().type("string")
            .pattern("field1 ASC,field2 DESC,field3");

        parameters.add(new Parameter()
            .name("offset")
            .schema(integerSchema)
            .required(false)
            .in(QUERY)
            .description("Pagination parameter - offset (start of page)")
        );

        parameters.add(new Parameter()
            .name("limit")
            .schema(integerSchema)
            .required(false)
            .in(QUERY)
            .description("Pagination parameter - limit (page size)")
        );

        parameters.add(new Parameter()
            .name("order")
            .schema(stringSchema)
            .required(false)
            .in(QUERY)
            .description("Sort parameter")
        );
    }

}
