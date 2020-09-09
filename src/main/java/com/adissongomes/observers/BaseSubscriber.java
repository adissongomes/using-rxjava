package com.adissongomes.observers;

import io.reactivex.rxjava3.annotations.NonNull;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class BaseSubscriber<T> implements Subscriber<T> {
    private static final Logger LOGGER = Logger.getLogger("BaseSubscriber");

    private Publisher<T> publisher;
    private Subscription subscription;

    protected BaseSubscriber(Publisher<T> publisher) {
        this.publisher = publisher;
        this.publisher.subscribe(this);
    }

    @Override
    public void onSubscribe(Subscription s) {
        this.subscription = s;
        this.subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }

    @Override
    public void onComplete() {
        LOGGER.info("COMPLETED");
    }

    public void detach() {
        LOGGER.info("canceling subscription " + getClass().getSimpleName());
        subscription.cancel();
    }
}
