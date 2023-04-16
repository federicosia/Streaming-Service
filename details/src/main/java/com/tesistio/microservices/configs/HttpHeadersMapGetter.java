package com.tesistio.microservices.configs;

import javax.annotation.Nullable;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import io.opentelemetry.context.propagation.TextMapGetter;

@Configuration
public class HttpHeadersMapGetter implements TextMapGetter<HttpHeaders> {

    @Override
    public Iterable<String> keys(HttpHeaders httpHeaders) {
        return httpHeaders.keySet();
    }

    @Nullable
    @Override
    public String get(@Nullable HttpHeaders httpHeaders, String s) {
        if(httpHeaders.containsKey(s))
            return httpHeaders.get(s).get(0);
        return null;
    }
}
