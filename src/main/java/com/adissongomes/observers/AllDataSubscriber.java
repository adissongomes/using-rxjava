package com.adissongomes.observers;

import com.adissongomes.services.dto.Data;
import io.reactivex.rxjava3.annotations.NonNull;
import org.reactivestreams.Publisher;

import java.util.logging.Logger;

public class AllDataSubscriber extends BaseSubscriber<Data> {
    protected static final Logger LOGGER = Logger.getLogger("AllData");

    public AllDataSubscriber(Publisher<Data> publisher) {
        super(publisher);
    }

    @Override
    public void onNext(@NonNull Data data) {
        LOGGER.info(data.toString());
    }

    @Override
    public void onComplete() {
        LOGGER.fine("Completed");
    }

}
