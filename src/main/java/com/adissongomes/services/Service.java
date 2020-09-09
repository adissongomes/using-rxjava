package com.adissongomes.services;

import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableEmitter;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public abstract class Service<T> {

    private static final Logger LOGGER = Logger.getLogger("Service");

    private final Random random = new Random();
    private final Flowable<T> flowable;
    private FlowableEmitter<T> emitter;

    protected Service() {
        flowable = Flowable.create(this::setEmitter, BackpressureStrategy.DROP)
            .publish().autoConnect();
        generateData();
    }

    private void setEmitter(FlowableEmitter<T> e) {
        this.emitter = e;
    }

    public Publisher<T> stream() {
        return flowable;
    }

    private void generateData() {
        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(getSleepSecondsTimeout());
                    emitter.onNext(get());
                } catch (InterruptedException e) {
                    LOGGER.severe(e.getMessage());
                    emitter.onError(e);
                    break;
                }
            }
        }).start();

    }

    public void stop() {
        emitter.onComplete();
    }

    protected abstract int getSleepSecondsTimeout();
    protected abstract T get();

}
