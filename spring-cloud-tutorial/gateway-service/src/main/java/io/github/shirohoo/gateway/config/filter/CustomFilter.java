package io.github.shirohoo.gateway.config.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;


@Slf4j
@Component
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {

    public CustomFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();
            log.info("--> ✅ Custom pre filter - request id={}", request.getId());
            log.info("--> ✅ Custom pre filter - request uri={}", request.getURI());
            log.info("--> ✅ Custom pre filter - request remote addr={}", request.getRemoteAddress());
            log.info("--> ✅ Custom pre filter - request query params={}", request.getQueryParams());
            log.info("--> ✅ Custom pre filter - request body={}", request.getBody());
            return chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {
                    log.info("--> ✅ Custom post filter - response code={}", response.getStatusCode());
                }));
        };
    }

    public static class Config {
        // Put the configuration properties
    }

}
