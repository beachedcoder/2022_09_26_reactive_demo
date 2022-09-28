package com.roi.demos.reactive_demo.handlers;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class HelloFunctional {
    public HelloFunctional() {
    }

    public Mono<ServerResponse> welcome(ServerRequest serverRequest) {
        return ServerResponse.ok().body(BodyInserters.fromValue("welcome to functional"));
    }
}
