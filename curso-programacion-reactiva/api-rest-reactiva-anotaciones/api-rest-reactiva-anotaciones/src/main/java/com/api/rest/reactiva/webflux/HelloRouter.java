package com.api.rest.reactiva.webflux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class HelloRouter {

    @Bean
    public RouterFunction<ServerResponse> functionalRoutes(HelloHandler helloHandler){
        return RouterFunctions
                .route(RequestPredicates.GET("/functional/mono"), helloHandler::mostrarMensajeMono)
                .andRoute(RequestPredicates.GET("/functional/flux"), helloHandler::mostrarMensajeFlux);
    }
}
