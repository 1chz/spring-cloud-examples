package io.github.shirohoo.openfeign.config;

import feign.Logger.Level;
import feign.RequestInterceptor;
import feign.Retryer;
import feign.Retryer.Default;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignFormatterRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;

@Configuration
@EnableFeignClients(basePackages = "io.github.shirohoo.openfeign.client")
public class OpenFeignConfig {
    @Bean
    public Level feignLoggerLevel() {
        return Level.FULL;
    }

    @Bean
    public Retryer retryer() {
        return new Default(1_000, 2_000, 3);
    }

    @Bean
    public FeignFormatterRegistrar localDateFeignFormatterRegister() {
        return registry -> {
            DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
            registrar.setUseIsoFormat(true);
            registrar.registerFormatters(registry);
        };
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("commonHeader", "shirohoo");
            requestTemplate.query("commonQueryParam", "shirohoo");
        };
    }
}
