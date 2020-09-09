package com.adissongomes.observers;

import com.adissongomes.services.dto.Info;
import io.reactivex.rxjava3.annotations.NonNull;
import org.reactivestreams.Publisher;

import java.util.logging.Logger;

public class AllInfoSubscriber  extends BaseSubscriber<Info> {
    protected static final Logger LOGGER = Logger.getLogger("AllInfo");

    public AllInfoSubscriber(Publisher<Info> publisher) {
        super(publisher);
    }

    @Override
    public void onNext(@NonNull Info info) {
        LOGGER.info(info.toString());
    }

}
