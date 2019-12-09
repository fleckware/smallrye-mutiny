package io.smallrye.mutiny.groups;

import static io.smallrye.mutiny.helpers.ParameterValidation.nonNull;

import java.util.function.Function;

import org.reactivestreams.Publisher;

import io.smallrye.mutiny.Multi;

public class MultiConvert<T> {

    private final Multi<T> upstream;

    public MultiConvert(Multi<T> upstream) {
        this.upstream = nonNull(upstream, "upstream");
    }

    /**
     * Transforms this {@link Multi} into a type using the provided converter.
     *
     * @param converter the converter function
     * @param <R> the type produced by the converter
     * @return an instance of R
     * @throws RuntimeException if the conversion fails.
     */
    public <R> R with(Function<Multi<T>, R> converter) {
        nonNull(converter, "converter");
        return converter.apply(upstream);
    }

    /**
     * Converts the {@link Multi} into a {@link Publisher}.
     * <p>
     * Basically, this method returns the {@link Multi} as it is, as {@link Multi} implements {@link Publisher}.
     *
     * @return the publisher
     */
    public Publisher<T> toPublisher() {
        return upstream;
    }
}
