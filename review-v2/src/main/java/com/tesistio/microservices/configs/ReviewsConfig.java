package com.tesistio.microservices.configs;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.propagation.ContextPropagators;
import io.opentelemetry.extension.trace.propagation.B3Propagator;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.OpenTelemetrySdkBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ReviewsConfig {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public OpenTelemetry openTelemetry() {
        OpenTelemetrySdkBuilder builder = OpenTelemetrySdk.builder();
        builder.setPropagators(ContextPropagators.create(B3Propagator.injectingMultiHeaders()));
        builder.buildAndRegisterGlobal();
        return GlobalOpenTelemetry.get();
    }

    @Bean
    public Tracer getTracer(OpenTelemetry openTelemetry) {
        return openTelemetry.getTracer(ReviewsConfig.class.getName());
    }

    @Bean
    public ContextPropagators getPropagation(OpenTelemetry openTelemetry) {
        return openTelemetry.getPropagators();
    }
}
