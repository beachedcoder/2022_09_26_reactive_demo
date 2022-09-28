package com.roi.demos.reactive_demo.handlers;

import com.roi.demos.reactive_demo.domain.Author;
import com.roi.demos.reactive_demo.domain.SearchDto;
import com.roi.demos.reactive_demo.repository.AuthorRepository;
import com.roi.demos.reactive_demo.svc.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.*;

@Component
public class AuthorHandler {

    private final AuthorRepository svc;

    public AuthorHandler(AuthorRepository authorRef) {
        this.svc = authorRef;
    }

    public Mono<ServerResponse> welcome(ServerRequest serverRequest) {
        return ok().body(BodyInserters.fromPublisher(Mono.just("welcome authors"),String.class));
    }
    public Mono<ServerResponse> getCurrentAuthors(ServerRequest request){
        return accepted().contentType(MediaType.TEXT_EVENT_STREAM)
                .body(svc.findAll(), Author.class);
    }

//    public Mono<ServerResponse> getAuthorByLastName(ServerRequest serverRequest) {
//        Mono<SearchDto> searchDto = serverRequest.bodyToMono(SearchDto.class);
//        return ServerResponse.status(HttpStatus.FOUND)
//                .body(svc.findAllByLastName(searchDto),Author.class);
//    }
//
//    public Mono<ServerResponse> getAuthorByEmail(ServerRequest serverRequest) {
//        Mono<SearchDto> searchDto = serverRequest.bodyToMono(SearchDto.class);
//        return ok().body(svc.getAuthorByEmailAddress(searchDto), Author.class);
//    }
}
