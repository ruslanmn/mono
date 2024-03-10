package ru.kmeansfan.printer.learn;

import org.assertj.core.api.Assertions;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import ru.kmeansfan.printer.learn.dto.User;

import java.time.Duration;
import java.util.Arrays;
import java.util.function.Supplier;

public class Part03StepVerifier {

//========================================================================================

    // TODO Use StepVerifier to check that the flux parameter emits "foo" and "bar" elements then completes successfully.
    void expectFooBarComplete(Flux<String> flux) {
        StepVerifier.create(flux)
            .expectNextSequence(Arrays.asList("foo", "bar"))
            .verifyComplete();
    }

//========================================================================================

    // TODO Use StepVerifier to check that the flux parameter emits "foo" and "bar" elements then a RuntimeException error.
    void expectFooBarError(Flux<String> flux) {
        StepVerifier.create(flux)
            .expectNext("foo")
            .expectNext("bar")
            .verifyError(RuntimeException.class);
    }

//========================================================================================

    // TODO Use StepVerifier to check that the flux parameter emits a User with "swhite"username
    // and another one with "jpinkman" then completes successfully.
    void expectSkylerJesseComplete(Flux<User> flux) {
        StepVerifier.create(flux)
            .assertNext(user -> Assertions.assertThat(user)
                .extracting(User::getUsername)
                .isEqualTo("swhite"))
            .expectNextMatches(user -> "jpinkman".equals(user.getUsername()))
            .expectComplete()
            .verify();
    }

//========================================================================================

    // TODO Expect 10 elements then complete and notice how long the test takes.
    void expect10Elements(Flux<Long> flux) {
        var time = StepVerifier.create(flux)
            .expectNextCount(10)
            .verifyComplete();
        System.out.println(time);
    }

//========================================================================================

    // TODO Expect 3600 elements at intervals of 1 second, and verify quicker than 3600s
    // by manipulating virtual time thanks to StepVerifier#withVirtualTime, notice how long the test takes
    void expect3600Elements(Supplier<Flux<Long>> supplier) {
        var time = StepVerifier.withVirtualTime(supplier)
            .thenAwait(Duration.ofSeconds(5000))
            .expectNextCount(3600)
            .verifyComplete();
        System.out.println(time);
        if (time.compareTo(Duration.ofSeconds(3600)) > 0) {
            fail();
        }
    }

    private void fail() {
        throw new AssertionError("workshop not implemented");
    }

    public static void main(String[] args) {
        new Part03StepVerifier().expect3600Elements(() ->
            Flux.interval(Duration.ofSeconds(1))
                .take(3600)
        );
    }
}