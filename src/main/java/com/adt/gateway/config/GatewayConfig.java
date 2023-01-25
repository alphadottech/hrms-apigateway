package com.adt.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.adt.gateway.routers.AuthenticationFilter;

@Configuration
public class GatewayConfig {
	
    @Autowired
    AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()	
                .route("auth-service", r -> r.path("/api/auth/**")
                        .uri("http://192.168.1.20:9004/")) 
                
                .route("auth-service", r -> r.path("/api/user/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://192.168.1.20:9004/"))
                
                .route("position_Module", r -> r.path("/employee/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://192.168.1.23:9090/"))
                .build();
    }

}
