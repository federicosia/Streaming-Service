package com.tesistio.microservices.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tesistio.microservices.configs.HttpHeadersMapGetter;
import com.tesistio.microservices.data.Details;
import com.tesistio.microservices.exceptions.FilmNotFoundException;
import com.tesistio.microservices.model.Film;
import com.tesistio.microservices.services.FilmServiceImpl;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;
import io.opentelemetry.context.propagation.ContextPropagators;

@RestController
@RequestMapping("/homepage/film")
public class FilmController {
    @Autowired
    private FilmServiceImpl service;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private Tracer tracer;
    @Autowired
    private ContextPropagators contextPropagators;
    @Autowired
    private HttpHeadersMapGetter headersMapGetter;
    @Value("${details.url}")
    private String urlDetails;

    //post used to load a film in the database
    @Transactional
    @PostMapping("/")
    public ResponseEntity<Void> createFilm(@Valid @RequestBody Film film){
        //get the id and increment, transactional to be sure that the operation in sequential
        Long id = service.getId().block();
        film.setId(id + 1);
        //no checks because film with the same data can exist
        Film saved = service.createFilm(film).block();
        URI location = ServletUriComponentsBuilder.fromHttpUrl(urlDetails + "/{id}/create")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{text}")
    public ResponseEntity<List<Film>> retrieveFilmByName(@PathVariable String text) {
        List<Film> films = service.retrieveFilmsByName(text).collectList().block();

        return new ResponseEntity<>(films, HttpStatus.OK);
    }

    @GetMapping("/{text}/details")
    public ResponseEntity<Details> retrieveDetailsByFilmName(@RequestHeader HttpHeaders httpHeaders,
                                                             @PathVariable String text) {
        Context extractedContext = contextPropagators.getTextMapPropagator()
                .extract(Context.current(), httpHeaders, headersMapGetter);

        try(Scope ignore = extractedContext.makeCurrent()) {
            //automatically use extracted context as parent
            Span span = tracer.spanBuilder("Films").startSpan();
            try {
                Film film = service.retrieveFilmByName(text).block();
                if (film == null)
                    throw new FilmNotFoundException("Film with this name: " + text + "doesn't exists.");

                HttpHeaders headers = new HttpHeaders();
                //inhect context data in map
                contextPropagators.getTextMapPropagator()
                        .inject(Context.current(), headers, (headers1, s, s1) -> headers1.set(s, s1));
                HttpEntity<?> request = new HttpEntity<>(null, headers);

                Details details = restTemplate.exchange(urlDetails + "/{id}",
                        HttpMethod.GET, request,
                        Details.class,
                        film.getId()).getBody();
                return new ResponseEntity<>(details, HttpStatus.OK);
            } finally {
                span.end();
            }
        }
    }

    @GetMapping("/{text}/reviews")
    public ResponseEntity<Object[]> retrieveReviewsByFilmName(@RequestHeader HttpHeaders httpHeaders,
                                                              @PathVariable String text) {
        Context extractedContext = contextPropagators.getTextMapPropagator()
                .extract(Context.current(), httpHeaders, headersMapGetter);

        try(Scope ignore = extractedContext.makeCurrent()) {
            //automatically use extracted context as parent
            Span span = tracer.spanBuilder("Films").startSpan();
            try {
                Film film = service.retrieveFilmByName(text).block();
                if (film == null)
                    throw new FilmNotFoundException("Film with this name: " + text + "doesn't exists.");

                HttpHeaders headers = new HttpHeaders();
                //inhect context data in map
                contextPropagators.getTextMapPropagator()
                        .inject(Context.current(), headers, (headers1, s, s1) -> headers1.set(s, s1));
                HttpEntity<?> request = new HttpEntity<>(null, headers);

                Object[] reviews = restTemplate.exchange(urlDetails + "/{id}/reviews",
                        HttpMethod.GET, request,
                        Object[].class,
                        film.getId()).getBody();
                return new ResponseEntity<>(reviews, HttpStatus.OK);
            } finally {
                span.end();
            }
        }
    }
}
