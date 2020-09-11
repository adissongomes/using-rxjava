package com.adissongomes.services;

import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableEmitter;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

public abstract class Service<T> {

    private static final Logger LOGGER = Logger.getLogger("Service");

    private final Flowable<T> flowable;
    private FlowableEmitter<T> emitter;
    private AtomicBoolean subscribed = new AtomicBoolean(false);

    protected Service() {
        this(BackpressureStrategy.BUFFER);
    }

    protected Service(BackpressureStrategy strategy) {
        flowable = Flowable.create(this::setEmitter, strategy)
                .publish()
                .refCount()
                .observeOn(Schedulers.io())
                .doOnSubscribe(this::startGen);

    }

    private void setEmitter(FlowableEmitter<T> e) {
        this.emitter = e;
    }

    private void startGen(Subscription s) {
        if (!subscribed.get()) {
            subscribed.set(true);
            generationThread();
        }
    }

    public Publisher<T> stream() {
        return flowable;
    }

    private void generationThread() {
        new Thread(() -> {
            while (true) {
                if (emitter != null && emitter.isCancelled()) break;
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
