package com.adissongomes.observers;

import com.adissongomes.services.dto.Info;
import io.reactivex.rxjava3.annotations.NonNull;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class SlowInfoSubscriber extends BaseSubscriber<Info> {
    protected static final Logger LOGGER = Logger.getLogger("SlowInfo");

    public SlowInfoSubscriber(Publisher<Info> publisher) {
        super(publisher);
    }

    @Override
    public void onSubscribe(Subscription s) {
        super.onSubscribe(s);
        request(1);
    }

    @Override
    public void onNext(@NonNull Info info) {
        LOGGER.info("--> slow " + info + " " + Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(4);
            request(1);
        } catch (InterruptedException e) {
            LOGGER.warning(e.getMessage());
        }
    }



}
