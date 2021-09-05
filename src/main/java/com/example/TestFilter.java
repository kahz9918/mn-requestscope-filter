package com.example;

import io.micronaut.http.HttpMethod;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.OncePerRequestHttpServerFilter;
import io.micronaut.http.filter.ServerFilterChain;
import io.micronaut.http.filter.ServerFilterPhase;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import javax.inject.Inject;

@Filter(value = "/test/**", methods = {HttpMethod.GET})
public class TestFilter extends OncePerRequestHttpServerFilter {
    @Inject
    private IMyRequestScopedBean myRequestScopedBean;

    public  Publisher<MutableHttpResponse<?>> doFilterOnce(HttpRequest<?> request, ServerFilterChain chain) {
        return Mono.just(request).flatMap(r -> {
            var p = r.getParameters();
            myRequestScopedBean.setRandomAttrib(p.get("random"));
            return Mono.from(chain.proceed(r));
        });
    }

    @Override
    public int getOrder() {
        return ServerFilterPhase.LAST.after();
    }
}
