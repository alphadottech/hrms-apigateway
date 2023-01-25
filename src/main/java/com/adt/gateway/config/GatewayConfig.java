package com.adt.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.adt.gateway.routers.AuthenticationFilter;

@Configuration
public class GatewayConfig {
	
	@Value("${employe.service.url}")
	private String employeeUrl;
	
	@Value("${auth.service.url}")
	private String authUrl;
	
    @Autowired
    AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()	
                .route("auth-service", r -> r.path("/api/auth/**")
                        .uri(authUrl)) 
                
                .route("auth-service", r -> r.path("/api/user/**")
                        .filters(f -> f.filter(filter))
                        .uri(authUrl))
                
                .route("position_Module", r -> r.path("/employee/**")
                        .filters(f -> f.filter(filter))
                        .uri(employeeUrl))
                .build();
    }

}
