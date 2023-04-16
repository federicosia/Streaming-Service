package com.tesistio.microservices.controller;

import com.tesistio.microservices.configs.HttpHeadersMapGetter;
import com.tesistio.microservices.exceptions.ReviewNotFoundException;
import com.tesistio.microservices.model.Review;
import com.tesistio.microservices.service.ReviewService;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;
import io.opentelemetry.context.propagation.ContextPropagators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService service;
    @Autowired
    private Tracer tracer;
    @Autowired
    private ContextPropagators contextPropagators;
    @Autowired
    private HttpHeadersMapGetter headersMapGetter;

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable long id) {
        Optional<Review> review = service.findById(id);

        if(review.isEmpty())
            throw new ReviewNotFoundException("No review with this id exists...");

        return new ResponseEntity<>(review.get(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Review> createReview(@Valid @RequestBody Review review) {
        Review saved = service.saveReview(review);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{email}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<Review[]> getReviewfromDetails(@RequestHeader HttpHeaders httpHeaders,
                                                         @PathVariable long id) {
        // Extract and store the propagated span's SpanContext and other available concerns
        // in the specified Context.
        Context extractedContext = contextPropagators.getTextMapPropagator()
                .extract(Context.current(), httpHeaders, headersMapGetter);
        try (Scope scope = extractedContext.makeCurrent()){
            Span span = tracer.spanBuilder("Reviews").startSpan();
            try  {
                return new ResponseEntity<>(service.findByDetails_id(id), HttpStatus.OK);
            } finally {
                span.end();
            }
        }
    }
}
