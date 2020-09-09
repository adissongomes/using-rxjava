package com.adissongomes.observers;

import com.adissongomes.services.dto.Data;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import org.reactivestreams.Publisher;

import java.util.logging.Logger;

public class DataIdUntil100Subscriber extends BaseSubscriber<Data> {

    protected static final Logger LOGGER = Logger.getLogger("DataIdUntil100");

    public DataIdUntil100Subscriber(Publisher<Data> publisher) {
        super(Flowable.fromPublisher(publisher)
                .filter(d -> d.getId() <= 100));
    }

    @Override
    public void onNext(@NonNull Data data) {
        LOGGER.info(data.toString());
    }

}
