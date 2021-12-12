package io.github.shirohoo.gateway.config.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class GlobalCustomFilter extends AbstractGatewayFilterFactory<GlobalCustomFilter.Config> {

    public GlobalCustomFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("--> ✅ Global filter - baseMessage={}", config.baseMessage);
            if (config.isPreLogger()) {
                log.info("--> ✅ Global pre filter - request id={}", request.getId());
            }

            return chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {
                    if (config.isPostLogger()) {
                        log.info("--> ✅ Global post filter - response code={}", response.getStatusCode());
                    }
                }));
        };
    }

    @Data
    public static class Config {

        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;

    }

}
