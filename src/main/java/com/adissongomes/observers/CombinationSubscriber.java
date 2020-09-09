package com.adissongomes.observers;

import com.adissongomes.services.dto.Data;
import com.adissongomes.services.dto.Info;
import io.reactivex.rxjava3.core.Flowable;
import org.reactivestreams.Publisher;

import java.util.logging.Logger;

public class CombinationSubscriber extends BaseSubscriber<String> {

    protected static final Logger LOGGER = Logger.getLogger("CombinationSubscriber");

    private CombinationSubscriber(Publisher<String> publisher) {
        super(publisher);
    }

    public static CombinationSubscriber create(Publisher<Data> dataPublisher, Publisher<Info> infoPublisher) {
        return new CombinationSubscriber(Flowable.zip(dataPublisher, infoPublisher, CombinationSubscriber::combine));
    }

    @Override
    public void onNext(String s) {
        LOGGER.info(s);
    }

    private static String combine(Data data, Info info) {
        return new StringBuilder("Combined [")
                .append(data.toString())
                .append(" -- ")
                .append(info.toString())
                .toString();
    }

}
