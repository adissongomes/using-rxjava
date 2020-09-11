package com.adissongomes.observers;

import com.adissongomes.services.dto.Info;
import io.reactivex.rxjava3.annotations.NonNull;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import java.util.logging.Logger;

public class AllInfoSubscriber extends BaseSubscriber<Info> {
    protected static final Logger LOGGER = Logger.getLogger("AllInfo");

    public AllInfoSubscriber(Publisher<Info> publisher) {
        super(publisher);
    }

    @Override
    public void onSubscribe(Subscription s) {
        super.onSubscribe(s);
        requestInfinite();
    }

    @Override
    public void onNext(@NonNull Info info) {
        LOGGER.info("all " + info + " " + Thread.currentThread().getName());
    }

}
