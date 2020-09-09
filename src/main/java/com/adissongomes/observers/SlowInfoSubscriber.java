package com.adissongomes.observers;

import com.adissongomes.services.dto.Info;
import io.reactivex.rxjava3.annotations.NonNull;
import org.reactivestreams.Publisher;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class SlowInfoSubscriber extends BaseSubscriber<Info> {
    protected static final Logger LOGGER = Logger.getLogger("AllInfo");

    public SlowInfoSubscriber(Publisher<Info> publisher) {
        super(publisher);
    }

    @Override
    public void onNext(@NonNull Info info) {
        try {
            TimeUnit.SECONDS.sleep(6);
            LOGGER.info(info.toString());
        } catch (InterruptedException e) {
            LOGGER.warning(e.getMessage());
        }
    }

}
