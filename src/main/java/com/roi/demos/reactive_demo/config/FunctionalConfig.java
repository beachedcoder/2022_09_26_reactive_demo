package com.roi.demos.reactive_demo.config;

import com.roi.demos.reactive_demo.handlers.AuthorHandler;
import com.roi.demos.reactive_demo.handlers.HelloFunctional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;


@Configuration
public class FunctionalConfig {

    @Bean("funcroutes")
    public RouterFunction<ServerResponse> functionalRouteConfig(HelloFunctional handleHello,
                                                                AuthorHandler authorHandle){
        return RouterFunctions.route()
                .path("api",rp ->
                    rp.GET("author",authorHandle::welcome)
                    .path("func", f ->
                        f.GET("hello",handleHello::welcome))).build();

    }
}
