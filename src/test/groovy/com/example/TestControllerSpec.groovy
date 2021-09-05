package com.example

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.uri.UriBuilder
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest
class TestControllerSpec extends Specification {
    @Shared
    @AutoCleanup
    @Inject
    @Client("/")
    RxHttpClient client

    def "test controller"() {
        given:
        final requestURI = UriBuilder.of("/test")
                .queryParam("random", "Test").build()

        when: "Test with an authorized application and with content"
        HttpResponse<String> response = client.toBlocking()
                .exchange(HttpRequest.GET(requestURI), List)

        then:
        response.status == HttpStatus.OK
        response.body.isPresent()
        response.body()[0] == "Hello Test"
    }
}
