package com.adissongomes;

import com.adissongomes.observers.*;
import com.adissongomes.services.DataService;
import com.adissongomes.services.InfoService;
import com.adissongomes.services.dto.Info;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Reactive {


    public static void main(String[] args) throws Exception {
        LogManager.getLogManager().readConfiguration(Reactive.class.getResourceAsStream("/logging.properties"));
        new Reactive().execute();
    }

    private void execute() throws InterruptedException {
        Logger.getGlobal().info("executing...");
        DataService dataService = DataService.create();
        InfoService infoService = InfoService.create();

        AllInfoSubscriber allInfoSubscriber = new AllInfoSubscriber(infoService.stream());
        CombinationSubscriber combinationSubscriber = CombinationSubscriber.create(dataService.stream(), infoService.stream());
        SlowInfoSubscriber slowInfoSubscriber = new SlowInfoSubscriber(infoService.stream());

        TimeUnit.SECONDS.sleep(7);
        infoService.stop();

        AllDataSubscriber allDataSubscriber = new AllDataSubscriber(dataService.stream());
        DataIdUntil100Subscriber until100Subscriber = new DataIdUntil100Subscriber(dataService.stream());

        TimeUnit.SECONDS.sleep(10);

        allDataSubscriber.detach();
        allInfoSubscriber.detach();
        dataService.stop();
    }

}
