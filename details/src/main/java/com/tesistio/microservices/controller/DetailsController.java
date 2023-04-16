package com.tesistio.microservices.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tesistio.microservices.configs.HttpHeadersMapGetter;
import com.tesistio.microservices.exceptions.DetailsNotFoundException;
import com.tesistio.microservices.model.Details;
import com.tesistio.microservices.service.DetailsServiceImpl;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;
import io.opentelemetry.context.propagation.ContextPropagators;

@RestController
@RequestMapping("/details")
public class DetailsController {
    @Autowired
    private DetailsServiceImpl service;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private Tracer tracer;
    @Autowired
    private ContextPropagators contextPropagators;
    @Autowired
    private HttpHeadersMapGetter headersMapGetter;
    @Value("${reviews.url}")
    private String urlReviews;

    @GetMapping("/{id}")
    public ResponseEntity<Details> retrieveDetailsByFilmId(@RequestHeader HttpHeaders httpHeaders,
                                                           @PathVariable long id) {
        // Extract and store the propagated span's SpanContext and other available concerns
        // in the specified Context.
        Context extractedContext = contextPropagators.getTextMapPropagator()
                .extract(Context.current(), httpHeaders, headersMapGetter);
        try (Scope scope = extractedContext.makeCurrent()){
            Span span = tracer.spanBuilder("Reviews").startSpan();
            try  {
                Optional<Details> details = Optional.ofNullable(service.findByFilm_id(id));
                if(details.isEmpty())
                    throw new DetailsNotFoundException("details id -> " + id);

                return new ResponseEntity<>(details.get(), HttpStatus.OK);
            } finally {
                span.end();
            }
        }
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<Object[]> retrieveReview(@RequestHeader HttpHeaders httpHeaders,
                                                   @PathVariable long id) {
        // Extract and store the propagated span's SpanContext and other available concerns
        // in the specified Context.
        Context extractedContext = contextPropagators.getTextMapPropagator()
            .extract(Context.current(), httpHeaders, headersMapGetter);

        try (Scope ignored = extractedContext.makeCurrent()) {
            //automatically use extracted context as parent
            Span span = tracer.spanBuilder("Details").startSpan();
            try {
                HttpHeaders headers = new HttpHeaders();
                //inhect context data in map
                contextPropagators.getTextMapPropagator()
                        .inject(Context.current(), headers, (headers1, s, s1) -> headers1.set(s, s1));
                HttpEntity<?> request = new HttpEntity<>(null, headers);

                // Handle request and send response back.
                Object[] reviews = restTemplate.exchange(urlReviews + "/details/{id}",
                        HttpMethod.GET, request,
                        Object[].class,
                        id).getBody();
                return new ResponseEntity<>(reviews, HttpStatus.OK);
            } finally {
                span.end();
            }
        }
    }

    @PostMapping("/{id}/create")
    public ResponseEntity<Void> createDetails(@PathVariable long id, @Valid @RequestBody Details details) {
        details.setFilmId(id);
        service.save(details);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}