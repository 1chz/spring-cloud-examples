package io.github.shirohoo.ecommerce.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class JwtAuthorizationFilter extends AbstractGatewayFilterFactory<JwtAuthorizationFilter.Config> {

    private final Environment environment;
    private final ObjectMapper objectMapper;

    public JwtAuthorizationFilter(Environment environment, ObjectMapper objectMapper) {
        super(Config.class);
        this.environment = environment;
        this.objectMapper = objectMapper;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, "no authorization header", HttpStatus.UNAUTHORIZED);
            }

            String authorizationHeader = Objects.requireNonNull(
                request.getHeaders()
                    .get(HttpHeaders.AUTHORIZATION)
            ).get(0);

            String jwt = authorizationHeader.replace("Bearer", "");

            if (!isValid(jwt)) {
                return onError(exchange, "JWT is not valid", HttpStatus.UNAUTHORIZED);
            }

            return chain.filter(exchange);
        };
    }

    private boolean isValid(String jwt) {
        String subject;
        try {
            subject = Jwts.parser()
                .setSigningKey(environment.getProperty("token.secret"))
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();
        } catch (Exception e) {
            return false;
        }

        if (!StringUtils.hasText(subject)) {
            // Check if the subject is a valid value by querying the DB (email)
            return false;
        }
        return true;
    }

    private Mono<Void> onError(ServerWebExchange exchange, String errorMessage, HttpStatus httpStatus) {
        log.error(errorMessage);
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        Response body = Response.of(httpStatus.value(), errorMessage);
        return response.writeWith(Flux.just(response.bufferFactory().wrap(getResponse(body))));
    }

    private byte[] getResponse(Response response) {
        return getJsonString(response)
            .getBytes(StandardCharsets.UTF_8);
    }

    private String getJsonString(Response response) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            return e.getLocalizedMessage();
        }
    }

    public static class Config {

    }

    @Value(staticConstructor = "of")
    private static class Response {

        int status;
        String message;

    }

}
