package com.example.lab3gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Lab3GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lab3GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("users", r -> r.host("localhost:8080")
                        .and()
                        .path("/api/users/{login}", "/api/users")
                        .uri("http://localhost:8081"))
                .route("characters", r -> r.host("localhost:8080")
                        .and()
                        .path("/api/cars", "/api/cars/**", "/api/users/{login}/cars", "/api/users/{login}/cars/**")
                        .uri("http://localhost:8082"))
                .build();
    }

}
